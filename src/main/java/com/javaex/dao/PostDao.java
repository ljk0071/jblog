package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<PostVo> SelectAll(int startRnum, int endRnum, String id, int cateNo) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("id", id);
		pMap.put("cateNo", cateNo);
		pMap.put("sNum", startRnum);
		pMap.put("eNum", endRnum);
		return sqlSession.selectList("post.SelectSnumEnum", pMap);
	}
	public int SelectTotalCnt(String id, int cateNo) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("id", id);
		pMap.put("cateNo", cateNo);
		int totalCnt = sqlSession.selectOne("post.SelectTotalCnt", pMap);
		return totalCnt;
	}
	public PostVo Select(PostVo pVo) {
		return sqlSession.selectOne("post.Select", pVo);
	}
	public PostVo Select(int postNo) {
		return sqlSession.selectOne("post.SelectVo", postNo);
	}
	
	public PostVo SelectLastPost(String id) {
		return sqlSession.selectOne("post.SelectLastPost", id);
	}
	
	public List<PostVo> SelectLastPostList(String id) {
		return sqlSession.selectList("post.SelectLastPostList", id);
	}

}
