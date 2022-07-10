package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao cmtDao;
	
	public CommentVo doAddCmt(CommentVo cmtVo) {
		cmtDao.InsertCmt(cmtVo);
		return cmtDao.Select(cmtVo);
	}

	public List<CommentVo> getCmtList(int postNo) {
		return cmtDao.SelectAll(postNo);
	}
	
	public int doDeleteCmt(int cmtNo) {
		return cmtDao.Delete(cmtNo);
	}

}
