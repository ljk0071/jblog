package com.javaex.vo;

public class UserVo {
	
	private String id, pw, name, joinDate;
	private int userNo;
	
	// 기본 Vo
	public UserVo() {
	}
	// 로그인 시도 시 쓸 Vo
	public UserVo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	// 로그인 성공 후 쓸 Vo
	public UserVo(String id, String name, int userNo) {
		this.id = id;
		this.name = name;
		this.userNo = userNo;
	}
	// 회원가입 할 때 쓸 Vo
	public UserVo(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	public UserVo(String id, String pw, String name, String joinDate) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.joinDate = joinDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + ", name=" + name + ", joinDate=" + joinDate + ", userNo=" + userNo
				+ "]";
	}
	
}
