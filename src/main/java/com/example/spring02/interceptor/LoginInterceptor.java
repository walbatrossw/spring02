package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
		// 세션에 id가 null이면
		if(session.getAttribute("userId") == null) {
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath()+"/member/login.do?msg=nologin");
			// 컨트롤러를 실행하지 않는다.(요청페이지로 이동하지 않는다)
			return false;
		// null이 아니면
		} else {
			// 컨트롤러를 실행(요청페이지로 이동한다.)
			return true;
		}
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
