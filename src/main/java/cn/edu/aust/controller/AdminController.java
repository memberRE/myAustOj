package cn.edu.aust.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.aust.pojo.ProblemWithBLOBs;
import cn.edu.aust.pojo.User;
import cn.edu.aust.service.IProblemService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Resource
	private IProblemService problemService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toAdmin(){
		return "admin/admin";
	}
	
	@RequestMapping(value="/adminArticle",method=RequestMethod.GET)
	public String toAdminArticle(){
		return "admin/admin_article";
	}
	@RequestMapping(value="/adminUser",method=RequestMethod.GET)
	public String toAdminUser(){
		return "admin/admin_user";
	}
	
	//保存问题
	@RequestMapping(value="/addProblem",method=RequestMethod.POST)
	@ResponseBody 
	public String addProblem(@RequestBody  ProblemWithBLOBs pb, HttpSession session){
		System.out.println("添加题目" + pb.getTitle() + "  " + pb.getDescription());
		try {
			//设置作者
			User user = (User) session.getAttribute("userLogin");
			if(user == null){
				return "添加失败，请先登录~~";
			}
			pb.setUserId(user.getUserId());
			//设置题目类别
			
			
			//设置题目级别
			
			
			//设置时间限制
			
			//设置内存限制
			this.problemService.insertSelective(pb);
			return "添加成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "添加失败，出现未知错误~~";
		}
	}
	
	//保存问题
	@RequestMapping(value="/editProblem",method=RequestMethod.POST)
	@ResponseBody 
	public String editProblem(@RequestBody  ProblemWithBLOBs pb, HttpSession session){
		System.out.println("修改题目" + pb.getTitle() + "  " + pb.getDescription());
		try {
			this.problemService.updateByPrimaryKeySelective(pb);
			return "修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "修改失败，出现未知错误~~";
		}
	}
	
	//删除问题
	@RequestMapping(value="/deleteProblem",method=RequestMethod.POST)
	@ResponseBody 
	public String deleteProblem(@RequestBody String problemIdArray){
		String problemIdArrayNew = problemIdArray.substring(1, problemIdArray.length()-1);
		String[] problemIdArrayStr = problemIdArrayNew.split(",");
		//将字符串数组转换为整形数组
		int[] problemIdArrayInt = new int[problemIdArrayStr.length];
		for(int i = 0 ; i < problemIdArrayStr.length; i++){
			problemIdArrayInt[i] = Integer.parseInt(problemIdArrayStr[i]);
		}
		try {
			for(int piai : problemIdArrayInt){
				this.problemService.deleteByPrimaryKey(piai);
			}
			return "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败";
		}
	}
	
}
