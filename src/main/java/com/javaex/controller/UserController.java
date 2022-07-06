package com.javaex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		return "/user/joinForm";
	}
	
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm(HttpServletRequest request) {
		String url = request.getHeader("Referer");
		if (url != null && !url.contains("/user")) {
			request.getSession().setAttribute("prevPage", url);
	    }
		return "/user/loginForm";
	}
	
	@RequestMapping(value="/loginCheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginCheck(HttpSession session,@ModelAttribute UserVo uVo ) {
		UserVo authUser = uService.doLogin(uVo);
		String url = (String)session.getAttribute("prevPage");
		if ( authUser != null && url != null) {
			session.setAttribute("authUser", authUser);
		    return "redirect:"+url;
		} else if(authUser != null) {
			session.setAttribute("authUser", authUser);
		    return "redirect:../";
		}
		return "redirect:./loginForm?result=fail";
	}
	@RequestMapping(value="/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		String url = (String)session.getAttribute("prevPage");
		session.invalidate();
		return "redirect:"+url;
	}
	
	@RequestMapping(value="/joinSuccess", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinOk() {
		return "/user/joinSuccess";
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public int joinCheck(@RequestBody UserVo uVo) {
		int cnt = uService.doAddUser(uVo);
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "/idCheck", produces="application/json; charset=UTF-8", method = { RequestMethod.GET, RequestMethod.POST })
	public int idCheck(@RequestBody String id) {
		String realId = id.replace("=", "");
		
		int cnt = uService.doChkId(realId);

		return cnt;
	}
	
	

	
}
