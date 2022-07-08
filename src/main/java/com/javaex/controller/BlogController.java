package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.service.CommentService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService bService;
	@Autowired
	private CategoryService cService;
	@Autowired
	private PostService pService;
	@Autowired
	private CommentService cmtService;

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(Model model, @PathVariable("id") String id) {
		model.addAttribute("bVo", bService.getBlogInfo(id));
		model.addAttribute("cateInfoList", cService.getCateInfo(id));
		model.addAttribute("pVo", pService.getLastPost(id));
		model.addAttribute("cmtList", cmtService.getCmtList(id));
		return "/blog/blog-main";
	}

	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String basic(Model model, @PathVariable("id") String id) {
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "/blog/admin/blog-admin-basic";
	}

	@RequestMapping(value = "/{id}/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifybasic(Model model, @PathVariable("id") String id, @ModelAttribute BlogVo bVo) {
		bVo.setId(id);
		bService.doUpdate(bVo);
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "redirect:/" + id;
	}

	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifycate(Model model, @PathVariable("id") String id) {
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "/blog/admin/blog-admin-cate";
	}

}
