package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao pDao;
	
	public int doAddPost(PostVo pVo) {
		return pDao.Insert(pVo);
	}

	public List<PostVo> getPostInfo(int cateNo) {
		return pDao.SelectAll(cateNo);
	}

	public PostVo getPostContent(PostVo pVo) {
		return pDao.Select(pVo);
	}

	public PostVo getLastPost(String id) {
		return pDao.SelectLastPost(id);
	}

	public List<PostVo> getLastPostList(String id) {
		return pDao.SelectLastPostList(id);
	}

}
