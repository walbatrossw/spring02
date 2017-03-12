package com.example.spring02.model.board.dao;

import java.util.List;

import com.example.spring02.model.board.dto.BoardVO;

public interface BoardDAO {
	// 01. 게시글 작성
	public void create(BoardVO vo) throws Exception;
	// 02. 게시글 상세보기
	public BoardVO read(int bno) throws Exception;
	// 03. 게시글 수정
	public void update(BoardVO vo) throws Exception;
	// 04. 게시글 삭제
	public void delete(int bno) throws Exception;
	// 05. 게시글 전체 목록 ==> 검색옵션, 키워드 매개변수 추가
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
	// 06. 게시글 조회 증가
	public void increaseViewcnt(int bno) throws Exception;
	// 07. 게시글 레코드 갯수 메서드 추가
	public int countArticle(String searchOption, String keyword) throws Exception;
	// 08. 게시물 첨부파일 추가
	public void addAttach(String fullName);
	// 09. 게시물 첨부파일 목록
	public List<String> getAttach(int bno);
	// 10. 게시글 첨부파일 삭제처리
	public void deleteFile(String fullname);
	// 11. 게시글 첨부파일 업데이트처리
	public void updateAttach(String fullName, int bno);
}