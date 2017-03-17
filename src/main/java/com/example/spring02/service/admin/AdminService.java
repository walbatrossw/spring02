package com.example.spring02.service.admin;

import com.example.spring02.model.member.dto.MemberVO;

public interface AdminService {
	// 관리자 로그인체크
	public String loginCheck(MemberVO vo);
}
