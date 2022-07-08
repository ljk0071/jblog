package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService cService;
	
	@ResponseBody
	@RequestMapping(value = "/{id}/cateList", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> setcateList(@PathVariable("id") String id) {
		return cService.getCateList(id);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/cateList/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public int deletecateList(@PathVariable("id") String id, @RequestBody int cateNo) {
		int result = cService.doDeleteCateList(cateNo, id);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/cateList/add", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo addcateList(@PathVariable("id") String id, @RequestBody CategoryVo cVo) {
		cVo.setId(id);
		cService.doAddCate(cVo);
		return cService.getCategory(cVo.getCateName(), id);
	}

}
