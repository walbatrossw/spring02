package com.example.spring02.model.admin;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.member.dto.MemberVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public String loginCheck(MemberVO vo) {
		return sqlSession.selectOne("admin.loginCheck", vo);
	}

}
