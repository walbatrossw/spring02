package com.example.spring02.controller.message;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.message.dto.MessageVO;
import com.example.spring02.service.message.MessageService;

@RestController
@RequestMapping("/messages/*")
public class MessageController {
	@Inject
	MessageService service;
	// ResponseEntity	: HTTP상태코드 + 데이터  전달
	// @RequestBody		: 클라이언트 => 서버 (json 데이터가 입력될 때)
	// @ResponsetBody	: 서버 => 클라이언트 (json) RestController에서는 생략가능
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
		ResponseEntity<String> entity = null;
		try {
			service.addMessage(vo);
			// new ResponseEntity<자료형>(리턴값, HTTP상태코드);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
