package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService cmtService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/cmt", method = { RequestMethod.GET, RequestMethod.POST })
	public CommentVo addCmt(@PathVariable("id") String id, @RequestBody CommentVo cmtVo) {
		return cmtService.doAddCmt(cmtVo);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/cmt/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public int deletecmt(@PathVariable("id") String id, @RequestBody int cmtNo) {
		int result = cmtService.doDeleteCmt(cmtNo);
		return result;
	}

}
