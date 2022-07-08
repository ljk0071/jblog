package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao cDao;
	
	public List<CategoryVo> getCateList(String id) {
		return cDao.SelectAll(id);
	}

	public int doDeleteCateList(int cateNo, String id) {
		return cDao.Delete(cateNo, id);
	}

	public int doAddCate(CategoryVo cVo) {
		return cDao.Insert(cVo);
	}

	public CategoryVo getCategory(String cateName, String id) {
		return cDao.Select(cateName, id);
	}

	public List<CategoryVo> getCateInfo(String id) {
		return cDao.Select(id);
	}

}
