package com.example.spring02.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.board.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Inject
	SqlSession sqlSession;
	
	// 1. 댓글 입력
		@Override
		public void create(ReplyVO vo) {
			sqlSession.insert("reply.insertReply", vo);
		}
	// 2. 댓글 목록
	@Override
	public List<ReplyVO> list(Integer bno,  int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("reply.listReply", map);
	}
	// 3. 댓글 상세보기
	@Override
	public ReplyVO detail(Integer rno) {
		return sqlSession.selectOne("reply.detailReply", rno);
	}
	// 4. 댓글 수정
	@Override
	public void update(ReplyVO vo) {
		sqlSession.update("reply.updateReply", vo);
	}
	// 5. 댓글 삭제
	@Override
	public void delete(Integer rno) {
		sqlSession.delete("reply.deleteReply", rno);
	}
	// 6. 댓글 갯수
	@Override
	public int count(Integer bno) {
		return sqlSession.selectOne("reply.countReply", bno);
	}

}
