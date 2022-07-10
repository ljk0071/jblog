package com.javaex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;

@Controller
public class MainController {
	
	@Autowired
	private BlogService bService;
	private Map<String, Object> bMap = new HashMap<String, Object>();
	
	
	@RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(Model model, @RequestParam(value="keyword", required=false, defaultValue="")String keyword
			,@RequestParam(value="kwdOpt", required=false, defaultValue="")String kwdOpt
			,@RequestParam(value="crtPage", required=false, defaultValue="1")int crtPage) {
		if (keyword=="") {
			return "/main/index";
		}
		else {
			if (kwdOpt.equals("optTitle")) {
				bMap = bService.getBlogTitleList(crtPage, keyword);
			} else {
				bMap = bService.getBlogNameList(crtPage, keyword);
			}
		}
		bMap.put("crtPage", crtPage);
		bMap.put("kwdOpt", kwdOpt);
		bMap.put("keyword", keyword);
		model.addAttribute("bMap", bMap);
		return "/main/index";
	}

}
