package com.example.spring02.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring02.model.board.dto.BoardVO;

@Controller
@RequestMapping("/basic/*")
public class BasicController2 {
	@RequestMapping("/sendVO")
	//@ResponseBody <- 메서드 상단 또는 리턴타입 앞에 어노테이션 추가
	public @ResponseBody BoardVO sayHello(){
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setWriter("DoubleS");
		vo.setContent("게시글 내용입니다");
		vo.setRecnt(1);
		vo.setTitle("게시글 1");
		vo.setUserName("DoubleS");
		return vo;
	}
}