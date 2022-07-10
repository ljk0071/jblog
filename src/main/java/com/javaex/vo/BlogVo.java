package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class BlogVo {
	
	private String id, blogTitle, name, saveName, joinDate;
	private MultipartFile logoFile;
	
	public BlogVo() {
	}
	// 블로그 수정폼에서 사용
	public BlogVo(String blogTitle, MultipartFile logoFile) {
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}
	// 
	public BlogVo(String id, String blogTitle, String name, String saveName, String joinDate) {
		this.id = id;
		this.blogTitle = blogTitle;
		this.name = name;
		this.saveName = saveName;
		this.joinDate = joinDate;
	}
	// 방문자 있을 때 사용
	public BlogVo(String id, String blogTitle, String name, String saveName) {
		this.id = id;
		this.blogTitle = blogTitle;
		this.name = name;
		this.saveName = saveName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public MultipartFile getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", name=" + name + ", saveName=" + saveName
				+ ", joinDate=" + joinDate + ", logoFile=" + logoFile + "]";
	}
}
