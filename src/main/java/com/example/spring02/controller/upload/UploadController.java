package com.example.spring02.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.util.UploadFileUtils;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	// xml에 설정된 리소스 참조
	// bean의 id가 uploadPath인 태그를 참조
	@Resource(name="uploadPath")
	String uploadPath;
	
	// 업로드 흐름 : 업로드 버튼클릭 => 임시디렉토리에 업로드=> 지정된 디렉토리에 저장 => 파일정보가 file에 저장
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.GET)
	public void uplodaForm(){
		// upload/uploadForm.jsp(업로드 페이지)로 포워딩
	}
	// 일반적인 업로드 방식
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.POST)
	public ModelAndView uplodaForm(MultipartFile file, ModelAndView mav) throws Exception{
		// 파일의 원본이름 저장
		String savedName = file.getOriginalFilename();
		
		logger.info("파일이름 :"+file.getOriginalFilename());
		logger.info("파일크기 : "+file.getSize());
		logger.info("컨텐트 타입 : "+file.getContentType());
		
		// 랜덤생성+파일이름 저장
		// 파일명 랜덤생성 메서드호출
		savedName = uploadFile(savedName, file.getBytes());
				
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName", savedName);
		
		return mav; // uploadResult.jsp(결과화면)로 포워딩
	}
	
	// 파일명 랜덤생성 메서드
	private String uploadFile(String originalName, byte[] fileData) throws Exception{
		// UUID 발급
		UUID uuid = UUID.randomUUID();
		// 파일명 = UUID + 원본이름
		String savedName = uuid.toString() + "_" + originalName;
		// 파일 경로, 파일명을 받아 파일 객체 생성
		File target = new File(uploadPath, savedName);
		// 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		// FileCopyUtils.copy(바이트배열, 파일객체)
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	
	// Ajax로 업로드하는 방식
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
	public void uploadAjax(){
		// uploadAjax.jsp로 포워딩
	}
	
	// 파일의 한글처리 : produces="text/plain;charset=utf-8"
	@ResponseBody // 뷰가 아닌 데이터를 리턴
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		logger.info("originalName : "+file.getOriginalFilename());
		logger.info("size : "+file.getSize());
		logger.info("contentType : "+file.getContentType());
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}
	
}
