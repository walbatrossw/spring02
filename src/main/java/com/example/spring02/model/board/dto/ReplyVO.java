package com.example.spring02.model.board.dto;

import java.util.Date;

public class ReplyVO {
	private Integer rno;		// 댓글 번호
	private Integer bno;		// 게시글 번호
	private String replytext;	// 댓글 내용
	private String replyer;		// 댓글 작성자
	private String userName;	// 댓글 작성자의 이름(회원의 이름)
	private Date regdate;		// 댓글 작성일자
	private Date updatedate;	// 댓글 수정일자
	
	// Getter/Setter
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	// toString()
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replytext=" + replytext + ", replyer=" + replyer
				+ ", userName=" + userName + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
	
}
