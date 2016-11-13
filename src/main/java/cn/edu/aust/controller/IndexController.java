package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页控制器
 * @author lvbiao
 *
 */
@Controller
public class IndexController {
	
	/**
	 * 前往主页
	 * @return
	 */
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String toIndex(){
		return "index";
	}
}
