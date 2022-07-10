package com.javaex.vo;


public class PostVo {
	
	private int postNo, cateNo;
	private String postTitle, postContent, regDate;
	
	public PostVo() {
	}
	// main에서 render하기 위한 vo
	public PostVo(int postNo, int cateNo, String postTitle, String postContent, String regDate) {
		this.postNo = postNo;
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.regDate = regDate;
	}
	// post작성시 사용할 vo
	public PostVo(int cateNo, String postTitle, String postContent) {
		this.cateNo = cateNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
	}
	public PostVo (int cateNo, String postTitle) {
		this.cateNo = cateNo;
		this.postTitle = postTitle;
	}
	public PostVo(int postNo) {
		this.postNo = postNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", cateNo=" + cateNo + ", postTitle=" + postTitle + ", postContent="
				+ postContent + ", regDate=" + regDate + "]";
	}
	

}
