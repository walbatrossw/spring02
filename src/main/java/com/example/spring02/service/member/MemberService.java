package com.example.spring02.service.member;

import javax.servlet.http.HttpSession;

import com.example.spring02.model.member.dto.MemberVO;

public interface MemberService {
	// 01_01. 회원 로그인 체크
	public boolean loginCheck(MemberVO vo, HttpSession session);
	// 01_02. 회원 로그인 정보
	public MemberVO viewMember(MemberVO vo);
	// 02. 회원 로그아웃
	public void logout(HttpSession session);
}
