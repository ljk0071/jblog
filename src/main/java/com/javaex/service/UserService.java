package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao uDao;
	@Autowired
	private BlogDao bDao;
	
	public int doChkId(String id) {
		int cnt = uDao.Select(id) != null ? -1 : 1;
		return cnt;
	}
	public int doAddUser(UserVo uVo) {
		int cnt = uDao.Insert(uVo);
		if ( cnt != -1 ) {
			bDao.CreateBlog(uVo.getId(), uVo.getName());
		}
		return  cnt;
	}
	public UserVo doLogin(UserVo uVo) {
		return uDao.Select(uVo);
	}
}
