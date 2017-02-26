package com.example.spring02.service.board;

public class ReplyPager {
	// 페이지당 게시물 수
	public static final int PAGE_SCALE = 5;
	// 화면당 페이지 수
	public static final int BLOCK_SCALE = 5;
	private int curPage; // 현재 페이수
	private int prevPage; // 이전 페이지
	private int nextPage; // 다음 페이지
	private int totPage; // 전체 페이지 갯수
	private int totBlock; // 전체 페이지 블록 갯수
	private int curBlock; // 현재 페이지 블록 
	private int prevBlock; // 이전 페이지 블록
	private int nextBlock; // 다음 페이지 블록
	// WHERE rn BETWEEN #{start} AND #{end}
	private int pageBegin; // #{start}
	private int pageEnd; // #{end}
	// [이전] blockBegin -> 41 42 43 44 45 46 47 48 49 50 [다음]
	private int blockBegin; // 현재 페이지 블록의 시작번호
	// [이전] 41 42 43 44 45 46 47 48 49 50 <- blockEnd [다음]
	private int blockEnd; // 현재 페이지 블록의 끝번호
	
	// 생성자
	// BoardPager(레코드 갯수, 현재 페이지 번호)
	public ReplyPager(int count, int curPage){
		curBlock = 1; // 현재 페이지 블록 번호
		this.curPage = curPage; // 현재 페이지 설정
		setTotPage(count); // 전체 페이지 갯수 계산
		setPageRange(); // 
		setTotBlock(); // 전체 페이지 블록 갯수 계산
		setBlockRange(); // 페이지 블록의 시작, 끝 번호 계산
	}
	
	public void setBlockRange(){
		// *현재 페이지가 몇번째 페이지 블록에 속하는지 계산
		// (현재페이지-1)/페이지 블록단위+1
		// 1페이지 => 1블록 (1-1)/10 + 1 => 1
		// 9페이지 => 	1블록 (9-1)/10 + 1 => 1
		// 11페이지 => 2블록 (11-1)/10 + 1 => 2
		// 57페이지 => 6블록 (57-1)/10 + 1 => 6 
		curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
		// *현재 페이지 블록의 시작, 끝 번호 계산
		// 페이지 블록의 시작번호
		// (현재블록-1)*블록단위+1
		// 1블록 => (1-1)*10 + 1 => 1
		// 2블록 => (2-1)*10 + 1 => 11
		// 6블록 => (6-1)*10 + 1 => 51
		blockBegin = (curBlock-1)*BLOCK_SCALE+1;
		// 페이지 블록의 끝번호
		// 블록시작번호+블록단위-1;
		// 1블록 => 1+10-1 => 10
		// 2블록 => 11+10-1 => 20
		// 6블록 => 51+10-1 => 60 	
		blockEnd = blockBegin+BLOCK_SCALE-1;
		// *마지막 블록이 범위를 초과하지 않도록 계산
		// [이전] 61 62 => 이러한 경우 70번까지 나오지 않도록하기 위해서
		if(blockEnd > totPage) blockEnd = totPage;
		// *이전을 눌렀을 때 이동할 페이지 번호
		prevPage = (curPage == 1)? 1:(curBlock-1)*BLOCK_SCALE;
		// *다음을 눌렀을 때 이동할 페이지 번호
		nextPage = curBlock > totBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
		// 마지막 페이지가 범위를 초과하지 않도록 처리
		if(nextPage >= totPage) nextPage = totPage;
	}
	
	public void setPageRange(){
	// WHERE rn BETWEEN #{start} AND #{end}
		// 시작번호 = (현재페이지-1)*페이지당 게시물수 +1
		pageBegin = (curPage-1)*PAGE_SCALE+1;
		// 끝번호 = 시작번호+페이지당 게시물수 -1
		pageEnd = pageBegin+PAGE_SCALE-1;
	}
	
	// Getter/Setter
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int count) {
		// 91개의 게시물을 10개씩 9페이지를 처리하고 남은 1개의 게시물도 페이지에 출력하기 위해서는
		// 항상 올림으로 처리해야한다.
		// Math.ceil(실수) 올림 처리
		// 모든 페이지는 올림처리
		totPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
	}
	public int getTotBlock() {
		return totBlock;
	}
	
	// 페이지 블록의 갯수 계산(총 100페이지라면 10개의 블록)
	public void setTotBlock() {
		// 전체 페이지 갯수 / 10
		// 91 / 10 => 9.1 => 10개
		totBlock = (int)Math.ceil(totPage / BLOCK_SCALE);
	}
	
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getPrevBlock() {
		return prevBlock;
	}
	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}
	public int getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockBegin() {
		return blockBegin;
	}
	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	
	
}
