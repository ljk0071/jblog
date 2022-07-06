package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	int count = -1;
	
	@Autowired
	private SqlSession sqlSession;
	
	public String Select(String id) {
		String chkId = sqlSession.selectOne("userbook.SelectChkId", id);
		return chkId;
	}
	public UserVo Select(UserVo uVo) {
		UserVo authUser = sqlSession.selectOne("userbook.SelectLogin", uVo);
		return authUser;
	}
	
	public int Insert(UserVo uVo) {
		count = sqlSession.insert("userbook.Insert", uVo);
		return count;
	}

}
