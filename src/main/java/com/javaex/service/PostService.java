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

	public List<PostVo> getPostList(int cateNo) {
		return pDao.SelectAll(cateNo);
	}

	public PostVo getPostContent(PostVo pVo) {
		return pDao.Select(pVo);
	}
	public PostVo getPostContent(int postNo) {
		return pDao.Select(postNo);
	}

	public PostVo getLastPost(String id) {
		PostVo pVo = pDao.SelectLastPost(id);
		if (pVo == null) {
			pVo = new PostVo(1);
		}
		return pVo;
	}

	public List<PostVo> getLastPostList(String id) {
		return pDao.SelectLastPostList(id);
	}

}
