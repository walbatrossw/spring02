package com.example.spring02.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic/*")
public class BasicController {
	@RequestMapping("/hello")
	public String sayHello(){
		return "hello";
	}
}
