package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao bDao;
	
	public BlogVo getBlogInfo(String id) {
		return bDao.getBlogInfo(id);
	}
	public void doUpdate(BlogVo bVo) {
		
		String saveDir = "C:\\JavaStudy\\upload";
		
		String orgName = bVo.getLogoFile().getOriginalFilename();
		
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		
		String filePath = saveDir + "\\" + saveName;
		
		bVo.setSaveName(saveName);
		
		bDao.updateInfo(bVo);
		
		try {
			byte[] fileData = bVo.getLogoFile().getBytes();
			
			OutputStream os =  new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
