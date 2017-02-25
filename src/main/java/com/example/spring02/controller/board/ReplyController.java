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
import com.example.spring02.service.board.BoardPager;
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
	
	// 댓글 입력
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		vo.setReplyer(userId);
		replyService.create(vo);
	}
	
	// 댓글입력 (Rest방식으로 json전달하여 글쓰기)
	// @ResponseEntity : 데이터 + http status code
	// @ResponseBody : 객체를 json으로 (json - String)
	// @RequestBody : json을 객체로
	@RequestMapping(value="insertRest.do", method=RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyVO vo, HttpSession session){
		ResponseEntity<String> entity = null;
		try {
			String userId = (String) session.getAttribute("userId");
			vo.setReplyer(userId);
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
	
	
	// 댓글 목록(Rest방식 json전달하여 목록생성)
	// /reply/list/1 => 1번 게시물의 댓글 목록 리턴
	// /reply/list/2 => 2번 게시물의 댓글 목록 리턴
	// @PathVariable : url에 입력될 변수값 지정
	@RequestMapping(value="/list/{bno}/{curPage}", method=RequestMethod.GET)
	public ModelAndView replyList(@PathVariable("bno") int bno, 
								@PathVariable int curPage,
								ModelAndView mav, HttpSession session){
		// **페이징 처리 
		int count = replyService.count(bno); // 댓글 갯수
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();
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
	
	// 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno,
							@RequestParam(defaultValue="1") int curPage,
							ModelAndView mav,
							HttpSession session){
		// **페이징 처리 
		int count = replyService.count(bno); // 댓글 갯수
		ReplyPager replyPager = new ReplyPager(count, curPage);
		int start = replyPager.getPageBegin();
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
	
	// 댓글 목록(@RestController Json방식으로 처리 : 데이터를 리턴)
	/*@RequestMapping("listJson.do")
	@ResponseBody // 리턴데이터를 json으로 변환(생략가능)
	public List<ReplyVO> listJson(@RequestParam int bno, @RequestParam(defaultValue="1") int curPage){
		int count = 10;
		BoardPager pager = new BoardPager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end);
		return list;
	}*/
}
