package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao bDao;
	@Autowired
	private CategoryDao cDao;
	
	public BlogVo getBlogInfo(String id) {
		return bDao.getBlogInfo(id);
	}
	public void doUpdate(BlogVo bVo) {
		if( bVo.getSaveName() != null ) {
			String saveDir = "C:\\JavaStudy\\upload";
			
			String orgName = bVo.getLogoFile().getOriginalFilename();
			
			String exName = orgName.substring(orgName.lastIndexOf("."));
			
			String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
			
			String filePath = saveDir + "\\" + saveName;
			
			try {
				byte[] fileData = bVo.getLogoFile().getBytes();
				
				OutputStream os =  new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
			
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			bVo.setSaveName(saveName);
		}
		bDao.updateInfo(bVo);
	}
	public List<CategoryVo> getCateList(String id) {
		return cDao.SelectAll(id);
	}
	
	public int doDeleteCateList(int cateNo, String id) {
		return cDao.Delete(cateNo, id);
	}
	public int doAddCate(CategoryVo cVo) {
		return cDao.Insert(cVo);
	}
	public CategoryVo getCategory(String cateName, String id) {
		return cDao.Select(cateName, id);
	}
}
