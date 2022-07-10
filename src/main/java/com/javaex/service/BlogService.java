package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao bDao;
	@Autowired
	private PostDao pDao;

	public BlogVo getBlogInfo(String id) {
		return bDao.getBlogInfo(id);
	}
	
	public List<BlogVo> getSearchTitle(String keyword) {
		return bDao.getSearchTitle(keyword);
	}
	
	public List<BlogVo> getSearchName(String keyword) {
		return bDao.getSearchName(keyword);
	}

	public void doUpdate(BlogVo bVo) {

		String orgName = bVo.getLogoFile().getOriginalFilename();
		if (orgName != "") {
			String saveDir = "C:\\JavaStudy\\upload";

			String exName = orgName.substring(orgName.lastIndexOf("."));

			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

			String filePath = saveDir + "\\" + saveName;

			try {
				byte[] fileData = bVo.getLogoFile().getBytes();

				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);

				bos.write(fileData);
				bos.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			bVo.setSaveName(saveName);
		}

		bDao.updateInfo(bVo);
	}

	public Map<String, Object> getBlogPostList(int crtPage, String id, int cateNo) {

		Map<String, Object> pMap = new HashMap<String, Object>();
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		final int listCnt = 5;
		final int pageBtnCount = 5;

		int startRnum = (crtPage - 1) * listCnt + 1;
		int endRnum = startRnum + listCnt - 1;

		int endPageNum = (int) Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount;
		int startPageNum = (endPageNum - pageBtnCount) + 1;
		boolean prev = (startPageNum != 1) ? true : false;

		boolean next = false;

		int totalCnt = pDao.SelectTotalCnt(id, cateNo);
		if ((listCnt * endPageNum) < totalCnt) {
			next = true;
		} else {
			endPageNum = (int) Math.ceil(totalCnt / (double) listCnt);
		}

		List<PostVo> pList = pDao.SelectAll(startRnum, endRnum, id, cateNo);

		pMap.put("pList", pList);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageNum", startPageNum);
		pMap.put("endPageNum", endPageNum);
		pMap.put("crtPage", crtPage);
		pMap.put("pageBtnCount", pageBtnCount);
		return pMap;
	}

}
