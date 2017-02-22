package cn.edu.aust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.Testcase;
import cn.edu.aust.service.ITestcaseService;
import cn.edu.aust.util.PageUtil;

@Controller
@RequestMapping("/testcase")
public class TestcaseController {

	@Resource
	private ITestcaseService testcaseService;
	
	
	@RequestMapping(value="/{problemId}",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> toTestcase(@PathVariable("problemId") String problemId,
			@RequestBody PageUtil pageUtil){
		
		Map<String, Object> maps = new HashMap<>();
	    //分页查询问题
	    PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
	    int problemIdint = Integer.parseInt(problemId);
	    System.out.println("problemId---->:" + problemIdint);
	    List<Testcase> list = this.testcaseService.getTestcaseList(problemIdint);
	    //用PageInfo对结果进行包装
	    PageInfo<Testcase> page = new PageInfo<Testcase>(list);
	    maps.put("total",page.getTotal());
	    maps.put("rows", page.getList());
		return maps;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody 
	public String add(@RequestBody Testcase testcase){
		System.out.println("添加数据---> ID:" + testcase.getProblemId() + "获取到标准输入输出：" + testcase.getInput() + "  " + testcase.getOutput());
		try {
			this.testcaseService.addTestcase(testcase);
		} catch (Exception e) {
			e.printStackTrace();
			return "添加出现异常";
		}
		return "添加成功";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody 
	public String update(@RequestBody Testcase testcase){
		System.out.println("修改数据--> ID:" + testcase.getProblemId() + "获取到标准输入输出：" + testcase.getInput() + "  " + testcase.getOutput());
		try {
			this.testcaseService.updateTestcase(testcase);
		} catch (Exception e) {
			e.printStackTrace();
			return "修改出现异常";
		}
		return "修改成功";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody 
	public String delete(@RequestBody String testcaseIdStr){
		String testcaseIdArrayNew = testcaseIdStr.substring(1, testcaseIdStr.length()-1);
		String[] testcaseIdArrayStr = testcaseIdArrayNew.split(",");
		//将字符串数组转换为整形数组
		int[] testcaseIdArrayInt = new int[testcaseIdArrayStr.length];
		for(int i = 0 ; i < testcaseIdArrayStr.length; i++){
			testcaseIdArrayInt[i] = Integer.parseInt(testcaseIdArrayStr[i]);
		}
		try {
			for(int testcaseId : testcaseIdArrayInt){
				this.testcaseService.deleteByPrimaryKey(testcaseId);
			}
			return "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败";
		}
	}
}
