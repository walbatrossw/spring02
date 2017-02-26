package com.example.spring02.model.board.dao;

import java.util.List;

import com.example.spring02.model.board.dto.ReplyVO;

public interface ReplyDAO {
	// 1. 댓글 입력
	public void create(ReplyVO vo);
	// 2. 댓글 목록
	public List<ReplyVO> list(Integer bno, int start, int end);
	// 3. 댓글 상세보기
	public ReplyVO detail(Integer rno);
	// 4. 댓글 수정
	public void update(ReplyVO vo);
	// 5. 댓글 삭제
	public void delete(Integer rno);
	// 6. 댓글 갯수
	public int count(Integer bno);
}
