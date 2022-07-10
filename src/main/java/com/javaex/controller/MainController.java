package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class MainController {
	
	@Autowired
	private BlogService bService;
	
	
	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(Model model, @RequestParam(value="keyword", required=false, defaultValue="")String keyword
			,@RequestParam(value="kwdOpt", required=false, defaultValue="")String kwdOpt) {
		if (keyword=="") {
			return "/main/index";
		}
		else {
			List<BlogVo> bList;
			if (kwdOpt.equals("optTitle")) {
				bList = bService.getSearchTitle(keyword);
			} else {
				bList = bService.getSearchName(keyword);
			}
			model.addAttribute("bList", bList);
		}
		return "/main/index";
	}

}
