package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	int cnt = -1;
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlogInfo(String id) {
		return sqlSession.selectOne("blog.SelectInfo", id);
	}
	
	public List<BlogVo> getSearchTitle(String keyword) {
		return sqlSession.selectList("blog.SearchInfoTitle", keyword);
	}
	
	public List<BlogVo> getSearchName(String keyword) {
		return sqlSession.selectList("blog.SearchInfoName", keyword);
	}
	public int NameTotalCnt(String keyword) {
		cnt = sqlSession.selectOne("blog.NameTotal", keyword);
		return cnt;
	}
	
	public int TitleTotalCnt(String keyword) {
		cnt = sqlSession.selectOne("blog.TitleTotal", keyword);
		return cnt;
	}
	
	public int CreateBlog(String id, String name) {
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("id", id);
		bMap.put("blogTitle", name+"의 블로그입니다.");
		bMap.put("saveName", "16574376404000de85c58-d844-42a6-8a61-b5f7937b4d6f.jpg");
		cnt = sqlSession.insert("blog.CreateBlog", bMap);
		return cnt;
	}
	
	public int updateInfo(BlogVo bVo) {
		cnt = sqlSession.update("blog.updateInfo", bVo);
		return cnt;
	}
	
	public List<BlogVo> SelectTitle(int startRnum, int endRnum, String keyword) {
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("keyword", keyword);
		bMap.put("sNum", startRnum);
		bMap.put("eNum", endRnum);
		return sqlSession.selectList("blog.SelectSnumEnumTitle", bMap);
	}
	
	public List<BlogVo> SelectName(int startRnum, int endRnum, String keyword) {
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("keyword", keyword);
		bMap.put("sNum", startRnum);
		bMap.put("eNum", endRnum);
		return sqlSession.selectList("blog.SelectSnumEnumName", bMap);
	}
	

}
