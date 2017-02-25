package com.example.spring02.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.board.dto.BoardVO;

@RestController
@RequestMapping("/rest/*")
public class ControllerRest {
	
	// json객체 리턴
	@RequestMapping("/sendVO2")
	public BoardVO sendVO2(){
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setWriter("DoubleS");
		vo.setContent("게시글 내용입니다");
		vo.setRecnt(1);
		vo.setTitle("게시글 1");
		vo.setUserName("DoubleS");
		return vo;
	}
	
	// json 객체 배열 리턴
	@RequestMapping("/sendList")
	public List<BoardVO> sendList(){
		// ArrayList 객체 생성
		List<BoardVO> items = new ArrayList<>();
		for(int i=1; i <=10; i++){
			BoardVO vo = new BoardVO(); //vo 객체 생성
			vo.setBno(i);
			vo.setWriter("DoubleS"+i);
			vo.setContent("게시글 내용입니다"+i);
			vo.setRecnt(i);
			vo.setTitle("게시글"+i);
			vo.setUserName("DoubleS"+i);
			items.add(vo); // 리스트에 vo추가
		}
		return items; // 리스트를 리턴함
	}
	
	// json객체를 map에 저장
	@RequestMapping("/sendMap")
	public Map<Integer, BoardVO> sendMap(){
		Map<Integer, BoardVO> map = new HashMap<Integer, BoardVO>();
		for(int i=1; i <=10; i++){
			BoardVO vo = new BoardVO(); //vo 객체 생성
			vo.setBno(i);
			vo.setWriter("DoubleS"+i);
			vo.setContent("Content....."+i);
			vo.setRecnt(i);
			vo.setTitle("Hello"+i);
			vo.setUserName("DoubleS"+i);
			map.put(i, vo); // 맵에 vo추가
		}
		return map;
	}
	
	@RequestMapping("/sendMap2")
	public ResponseEntity<Map<Integer, BoardVO>> sendMap2(){
		Map<Integer, BoardVO> map = new HashMap<Integer, BoardVO>();
		for(int i=1; i <=10; i++){
			BoardVO vo = null; //vo 객체 생성
			vo.setBno(i);
			vo.setWriter("DoubleS"+i);
			vo.setContent("Content....."+i);
			vo.setRecnt(i);
			vo.setTitle("Hello"+i);
			vo.setUserName("DoubleS"+i);
			map.put(i, vo); // 맵에 vo추가
		}
		//return map;
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
