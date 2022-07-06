package com.javaex.vo;

public class CategoryVo {
	private int cateNo, count;
	private String id, cateName, description, regDate;
	
	public CategoryVo() {
	}
	public CategoryVo(int cateNo, String id, String cateName, String description, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.regDate = regDate;
	}
	// 내 블로그 관리에서 category 항목 들어갈 때
	public CategoryVo(int cateNo, String cateName, int count, String description) {
		this.cateNo = cateNo;
		this.cateName = cateName;
		this.description = description;
	}
	public CategoryVo(String cateName, int count, String description) {
		this.cateName = cateName;
		this.description = description;
	}
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", count=" + count + ", id=" + id + ", cateName=" + cateName
				+ ", description=" + description + ", regDate=" + regDate + "]";
	}
	
	

}
