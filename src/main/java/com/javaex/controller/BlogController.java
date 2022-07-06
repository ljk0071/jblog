package com.javaex.controller;

import java.util.List;

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
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService bService;
	
	@RequestMapping(value="/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String main(Model model, @PathVariable("id") String id) {
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "/blog/blog-main";
	}
	@RequestMapping(value="/{id}/admin/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public String basic(Model model, @PathVariable("id") String id) {
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "/blog/admin/blog-admin-basic";
	}
	
	@RequestMapping(value="/{id}/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifybasic(Model model, @PathVariable("id") String id, @ModelAttribute BlogVo bVo) {
		bVo.setId(id);
		bService.doUpdate(bVo);
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "redirect:/"+id;
	}
	
	@RequestMapping(value="/{id}/admin/category", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifycate(Model model, @PathVariable("id") String id) {
		model.addAttribute("bVo", bService.getBlogInfo(id));
		return "/blog/admin/blog-admin-cate";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/cateList", method= {RequestMethod.GET, RequestMethod.POST})
	public List<CategoryVo> modifycateList(@PathVariable("id") String id) {
		return bService.getCateList(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/cateList/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public int deletecateList(@PathVariable("id") String id, @RequestBody int cateNo ) {
		int result = bService.doDeleteCateList(cateNo, id);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/cateList/add", method= {RequestMethod.GET, RequestMethod.POST})
	public CategoryVo addcateList(@PathVariable("id") String id, @RequestBody CategoryVo cVo ) {
		cVo.setId(id);
		bService.doAddCate(cVo);
		return bService.getCategory(cVo.getCateName(), id);
	}
}
