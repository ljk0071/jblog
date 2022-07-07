package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	int cnt = -1;
	
	@Autowired
	private SqlSession sqlSession;
	
	public int Insert(PostVo pVo) {
		cnt = sqlSession.insert("post.Insert", pVo);
		return cnt;
	}
	
	public List<PostVo> SelectAll(int cateNo) {
		return sqlSession.selectList("post.SelectAll", cateNo);
	}
	
	public PostVo Select(PostVo pVo) {
		return sqlSession.selectOne("post.Select", pVo);
	}

}
