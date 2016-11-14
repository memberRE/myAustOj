package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 菜单项控制器
 * @author lvbiao
 *
 */
@Controller
public class MenuController {
	
	/**
	 * 前往起步页面
	 */
	@RequestMapping(value="/start",method=RequestMethod.GET)
	public String toStart(){
		return "start";
	}
	
	/**
	 * 前往训练页面
	 */
	@RequestMapping(value="/practice",method=RequestMethod.GET)
	public String toPractice(){
		return "practice";
	}
	
	/**
	 * 前往进阶页面
	 */
	@RequestMapping(value="/master",method=RequestMethod.GET)
	public String toMaster(){
		return "master";
	}
	
	
	/**
	 * 前往排名页面
	 */
	@RequestMapping(value="/rank",method=RequestMethod.GET)
	public String toRank(){
		return "rank";
	}
	

}
