package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSession sqlSession;
	int cnt = -1;
	
	public int InsertCmt(CommentVo cmtVo) {
		return cnt = sqlSession.insert("comment.InsertCmt", cmtVo);
	}
	
	public CommentVo Select(CommentVo cmtVo) {
		return sqlSession.selectOne("comment.Select", cmtVo);
	}
	
	public List<CommentVo> SelectAll(String id) {
		return sqlSession.selectList("comment.SelectAll", id);
	}
	public int Delete(int cmtNo) {
		return sqlSession.delete("comment.Delete", cmtNo);
	}

}
