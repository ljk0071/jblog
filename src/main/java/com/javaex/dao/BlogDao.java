package com.javaex.dao;

import java.util.HashMap;
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
	
	public int CreateBlog(String id, String name) {
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("id", id);
		bMap.put("blogTitle", name+"의 블로그입니다.");
		cnt = sqlSession.insert("blog.CreateBlog", bMap);
		return cnt;
	}
	
	public int updateInfo(BlogVo bVo) {
		cnt = sqlSession.update("blog.updateInfo", bVo);
		return cnt;
	}

}
