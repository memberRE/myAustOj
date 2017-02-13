package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin")
public class AdminController {
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toAdmin(){
		return "admin/admin";
	}
	
	@RequestMapping(value="/adminArticle",method=RequestMethod.GET)
	public String toAdminArticle(){
		return "admin/admin_article";
	}
	@RequestMapping(value="adminUser",method=RequestMethod.GET)
	public String toAdminUser(){
		return "admin/admin_user";
	}
	
}
