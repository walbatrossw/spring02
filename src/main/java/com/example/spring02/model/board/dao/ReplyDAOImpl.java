package com.example.spring02.model.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.board.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Inject
	SqlSession sqlSession;
	
	// 댓글 목록
	@Override
	public List<ReplyVO> list(Integer bno) {
		return sqlSession.selectList("reply.listReply", bno);
	}
	// 댓글 작성
	@Override
	public void create(ReplyVO vo) {
		sqlSession.insert("reply.insertReply", vo);
	}
	// 댓글 수정
	@Override
	public void update(ReplyVO vo) {
		// TODO Auto-generated method stub

	}
	// 댓글 삭제
	@Override
	public void delete(Integer rno) {
		// TODO Auto-generated method stub

	}

}
