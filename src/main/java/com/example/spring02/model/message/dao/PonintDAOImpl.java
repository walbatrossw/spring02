package com.example.spring02.model.message.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PonintDAOImpl implements PointDAO {
	
	@Inject
	SqlSession sqlSession;
	
	// 회원 포인트 갱신(메시지 발신)
	@Override
	public void updatePoint(String userid, int upoint) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("upoint", upoint);
		sqlSession.update("point.updatePoint", map);
	}
}
