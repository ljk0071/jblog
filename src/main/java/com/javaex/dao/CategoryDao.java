package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	private int cnt = -1;
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<CategoryVo> SelectAll(String id) {
		List<CategoryVo> cList = sqlSession.selectList("category.SelectAll", id);
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("id", id);
		for (int i=0;i<cList.size();i++) {
			cMap.put("cateNo", cList.get(i).getCateNo());
			cnt = sqlSession.selectOne("category.CntPost", cMap);
			cList.get(i).setCount(cnt);
			cMap.remove("cateNo");
		}
		return cList;
	}
	
	public CategoryVo Select(String cateName, String id) {
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("id", id);
		cMap.put("cateName",  cateName);
		CategoryVo cVo = sqlSession.selectOne("category.Select", cMap);
		cMap.put("cateNo", cVo.getCateNo());
		cVo.setCount(sqlSession.selectOne("category.CntPost", cMap));
		return cVo;
	}
	
	public List<CategoryVo> Select(String id) {
		return sqlSession.selectList("category.CateInfo", id);
	}
	
	public int Delete(int cateNo, String id) {
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("id", id);
		cMap.put("cateNo",  cateNo);
		cnt = sqlSession.delete("category.Delete", cMap);
		return cnt;
	}
	
	public int Insert(CategoryVo cVo) {
		cnt = sqlSession.insert("category.Insert", cVo);
		return cnt;
	}
	
	public int SelectLast(String id) {
		cnt = sqlSession.selectOne("category.SelectLast", id);
		return cnt;
	}

}
