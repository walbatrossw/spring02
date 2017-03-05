package com.example.spring02.model.message.dto;

public class UserVO {
	private String userid;	// 사용자ID
	private String userpw;	// 사용자PW
	private String uname;	// 사용자이름
	private int upoint;		// 사용자적립포인트
	// Getter/Setter
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getUpoint() {
		return upoint;
	}
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}
	// toString
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", userpw=" + userpw + ", uname=" + uname + ", upoint=" + upoint + "]";
	}
	
	
}
