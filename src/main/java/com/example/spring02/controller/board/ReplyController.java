package com.example.spring02.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.ReplyVO;
import com.example.spring02.service.board.ReplyPager;
import com.example.spring02.service.board.ReplyService;

// REST : Representational State Transfer
// 하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념

// http://localhost/spring02/list?bno=1 ==> url+파라미터
// http://localhost/spring02/list/1 ==> url
// RestController은 스프링 4.0부터 지원
// @Controller, @RestController 차이점은 메서드가 종료되면 화면전환의 유무

//@Controller
@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Inject
	ReplyService replyService;
	
	// 1_1. 댓글 입력(@Controller방식으로 댓글 입력)
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session){
		// 세션에 저장된 회원아이디를 댓글작성자에 세팅
		String userId = (String) session.getAttribute("userId");
		vo.setReplyer(userId);
		// 댓글 입력 메서드 호출
		replyService.create(vo);
	}
	
	// 1_2. 댓글입력 (@RestController방식으로 json전달하여 댓글입력)
	// @ResponseEntity : 데이터 + http status code
	// @ResponseBody : 객체를 json으로 (json - String)
	// @RequestBody : json을 객체로
	@RequestMapping(value="insertRest.do", method=RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyVO vo, HttpSession session){
		ResponseEntity<String> entity = null;
		try {
			// 세션에 저장된 회원아이디를 댓글작성자에 세팅
			String userId = (String) session.getAttribute("userId");
			vo.setReplyer(userId);
			// 댓글입력 메서드 호출
			replyService.create(vo);
			// 댓글입력이 성공하면 성공메시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글입력이 실패하면 실패메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 입력 처리 HTTP 상태 메시지 리턴
		return entity;
	}
	
	// 2_1. 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno,	@RequestParam(defaultValue="1") int curPage, ModelAndView mav, HttpSession session){
		// 페이징 처리 
		int count = replyService.count(bno); // 댓글 갯수
		ReplyPager replyPager = new ReplyPager(count, curPage);
		// 현재 페이지의 페이징 시작 번호
		int start = replyPager.getPageBegin();
		// 현재 페이지의 페이징  끝 번호
		int end = replyPager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		// 뷰이름 지정
		mav.setViewName("board/replyList");
		// 뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		// replyList.jsp로 포워딩
		return mav;
	}
	
	// 2_2. 댓글 목록(@RestController방식 : Json으로 데이터를 리턴)
	@RequestMapping("listJson.do")
	@ResponseBody // 리턴데이터를 json으로 변환(RestController사용시 @ResponseBody생략가능)
	public List<ReplyVO> listJson(@RequestParam int bno, @RequestParam(defaultValue="1") int curPage, HttpSession session){
		// 페이징 처리
		int count = replyService.count(bno); // 댓글 갯수
		ReplyPager pager = new ReplyPager(count, curPage);
		// 현재 페이지의 페이징 시작 번호
		int start = pager.getPageBegin();
		// 현재 페이지의 페이징  끝 번호
		int end = pager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		// list를 리턴
		return list;
	}
	
	// ** Controller 추가 사항 - Rest방식으로 댓글 목록, 수정, 삭제 처리
	
	// 2_3. 댓글 목록(@RestController방식 :  json으로 전달하여 목록생성)
	// /reply/list/1 => 1번 게시물의 댓글 목록 리턴
	// /reply/list/2 => 2번 게시물의 댓글 목록 리턴
	// @PathVariable : url에 입력될 변수값 지정
	@RequestMapping(value="/list/{bno}/{curPage}", method=RequestMethod.GET)
	public ModelAndView replyList(@PathVariable("bno") int bno, @PathVariable int curPage, ModelAndView mav, HttpSession session){
		// 페이징 처리
		int count = replyService.count(bno); // 댓글 갯수
		ReplyPager replyPager = new ReplyPager(count, curPage);
		// 현재 페이지의 페이징 시작 번호
		int start = replyPager.getPageBegin();
		// 현재 페이지의 페이징  끝 번호
		int end = replyPager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		// 뷰이름 지정
		mav.setViewName("board/replyList");
		// 뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		// replyList.jsp로 포워딩
		return mav;
	}
	
	// 3. 댓글 상세 보기
	// /reply/detail/1 => 1번 댓글의 상세화면 리턴
	// /reply/detail/2 => 2번 댓글의 상세화면 리턴
	// @PathVariable : url에 입력될 변수값 지정
	@RequestMapping(value="/detail/{rno}", method=RequestMethod.GET)
	public ModelAndView replyDetail(@PathVariable("rno") Integer rno, ModelAndView mav){
		ReplyVO vo = replyService.detail(rno);
		// 뷰이름 지정
		mav.setViewName("board/replyDetail");
		// 뷰에 전달할 데이터 지정
		mav.addObject("vo", vo);
		// replyDetail.jsp로 포워딩
		return mav;
	}
	
	// 4. 댓글 수정 처리 - PUT:전체 수정, PATCH:일부수정
	// RequestMethod를 여러 방식으로 설정할 경우 {}안에 작성
	@RequestMapping(value="/update/{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno);
			replyService.update(vo);
			// 댓글 수정이 성공하면 성공 상태메시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글 수정이 실패하면 실패 상태메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 수정 처리 HTTP 상태 메시지 리턴
		return entity;
	}
	
	// 5. 댓글 삭제처리
	@RequestMapping(value="/delete/{rno}")
	public ResponseEntity<String> replyDelete(@PathVariable("rno") Integer rno){
		ResponseEntity<String> entity = null;
		try {
			replyService.delete(rno);
			// 댓글 삭제가 성공하면 성공 상태메시지 저장
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글 삭제가 실패하면 실패 상태메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 삭제 처리 HTTP 상태 메시지 리턴
		return entity;
	}
}
