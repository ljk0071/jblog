package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
public class PostController {
	
	@Autowired
	private BlogService bService;
	@Autowired
	private CategoryService cService;
	@Autowired
	private PostService pService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/cate", method = { RequestMethod.GET, RequestMethod.POST })
	public List<PostVo> pList(@PathVariable("id") String id, @RequestBody int cateNo) {
		return pService.getPostList(cateNo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}/post", method = { RequestMethod.GET, RequestMethod.POST })
	public PostVo post(@PathVariable("id") String id, @RequestBody PostVo pVo) {
		return pService.getPostContent(pVo);
	}

//	@ResponseBody
//	@RequestMapping(value = "/{id}/postList", method = { RequestMethod.GET, RequestMethod.POST })
//	public List<PostVo> postlist(@PathVariable("id") String id) {
//		return pService.getLastPostList(id);
//	}

	@RequestMapping(value = "/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(@PathVariable("id") String id, Model model) {
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("bVo", bService.getBlogInfo(id));
		bMap.put("cateInfoList", cService.getCateInfo(id));
		model.addAttribute("bMap", bMap);
		return "/blog/admin/blog-admin-write";
	}

	@RequestMapping(value = "/{id}/admin/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@PathVariable("id") String id, @ModelAttribute PostVo pVo) {
		pService.doAddPost(pVo);
		return "redirect:/" + id + "/admin/writeForm";
	}

}
