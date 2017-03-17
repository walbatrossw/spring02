package com.example.spring02.model.admin;

import com.example.spring02.model.member.dto.MemberVO;

public interface AdminDAO {
	// 관리자 로그인체크
	public String loginCheck(MemberVO vo);
}
