package com.example.spring02.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.ReplyVO;
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
	
	// 댓글 입력
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		vo.setReplyer(userId);
		replyService.create(vo);
	}
	// 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno, ModelAndView mav){
		List<ReplyVO> list = replyService.list(bno);
		// 뷰이름 지정
		mav.setViewName("board/replyList");
		// 뷰에 전달할 데이터 지정
		mav.addObject("list", list);
		// replyList.jsp로 포워딩
		return mav;
	}
	// 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
	@RequestMapping("listJson.do")
	@ResponseBody // 리턴데이터를 json으로 변환(생략가능)
	public List<ReplyVO> listJson(@RequestParam int bno){
		List<ReplyVO> list = replyService.list(bno);
		return list;
	}
}
