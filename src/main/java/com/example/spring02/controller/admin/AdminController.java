package com.example.spring02.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberVO;
import com.example.spring02.service.admin.AdminService;

@Controller
@RequestMapping("admin/*")
public class AdminController {
	
	@Inject
	AdminService adminService;
	
	// 1. 관리자  로그인페이지 매핑
	@RequestMapping("login.do")
	public String login(){
		return "admin/adminLogin";
	}
	// 2. 관리자  로그인 체크 
	@RequestMapping("loginCheck.do")
	public ModelAndView loginCheck(HttpSession session, MemberVO vo, ModelAndView mav){
		String name = adminService.loginCheck(vo);
		// 로그인 성공
		if(name != null) {
			session.setAttribute("adminId", vo.getUserId());
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("adminName", name);
			session.setAttribute("userName", name);
			mav.setViewName("admin/adminHome"); // 관리자 페이지 이동
			mav.addObject("msg", "success");
		// 로그인 실패
		} else { 
			mav.setViewName("admin/adminLogin"); // 로그인 페이지 이동
			mav.addObject("msg", "failure");
		}
		return mav;
	}
	
	// 3. 관리자 로그아웃
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session){
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/adminLogin");
		mav.addObject("msg", "logout");
		return mav;
	}
}
