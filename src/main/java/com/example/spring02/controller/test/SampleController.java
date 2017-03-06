package com.example.spring02.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.board.dto.BoardVO;


// *@Controller - 뷰(화면)를 리턴하는 것이 주용도, 객체를 보내기위해서 @ResponseBody를 사용할수 있다.
// *@RestController - 데이터를 리턴하는 것이 주용도,  안드로이드 아이폰 pc등 이 기종에서 하이브리드로 작업을 할경우 파싱할 때 유용
// *@ResponseBody - 메서드선언부, 리턴타입 앞에 작성가능, RestController에서는 생략가능(이미기능을 가지고있기 때문에)
// { "변수명" : "값", "변수명" : "값" } json 
// [ {}, {}, {}, {} ] json 객체 배열

@RestController
//@Controller
@RequestMapping("/sample/*")
public class SampleController {
	@RequestMapping("/hello")
	public String sayHello(){
		return "Hello World";
	}
	
	// json 객체
	// 뷰가 아닌 객체를 리턴
	@RequestMapping("/sendVO")
	//@ResponseBody // 객체를 json으로 변환
	public @ResponseBody BoardVO sendVO(){
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setWriter("DoubleS");
		vo.setContent("Content.....");
		vo.setRecnt(1);
		vo.setTitle("Hello");
		vo.setUserName("DoubleS");
		return vo;
	}
	
	// json 객체 배열
	@RequestMapping("/sendList")
	public List<BoardVO> sendList(){
		// ArrayList 객체 생성
		List<BoardVO> items = new ArrayList<>();
		for(int i=1; i <=10; i++){
			BoardVO vo = new BoardVO(); //vo 객체 생성
			vo.setBno(i);
			vo.setWriter("DoubleS"+i);
			vo.setContent("Content....."+i);
			vo.setRecnt(i);
			vo.setTitle("Hello"+i);
			vo.setUserName("DoubleS"+i);
			items.add(vo); // 리스트에 vo추가
		}
		return items; // 리스트를 리턴함
	}
	// json객체를 map에 저장
	@RequestMapping("/sendMap")
	public Map<Integer, BoardVO> sendMap(){
		// Map<Key자료형, Value자료형>
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
	
	// ResponseEntity : 데이터 + http status code
	@RequestMapping("/sendMap2")
	public ResponseEntity<Map<Integer, BoardVO>> sendMap2(){
		// Map<Key자료형, Value자료형>
		//Map<Integer, BoardVO> map = new HashMap<Integer, BoardVO>();
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
		//return map;
		// 리턴시 map과 상태메시지를 함께 전송
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
