package cn.edu.aust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.form.ProblemForm;
import cn.edu.aust.service.IProblemService;

/**
 * 问题控制器
 * @author lvbiao
 *
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {
	
	@Resource
	private IProblemService problemService;
	private static final Logger log = LoggerFactory.getLogger(ProblemController.class);
	
	/**
	 * 按照问题等级进行查询
	 * @return
	 */
	@RequestMapping(value="/findStageProblem/{stage}")
	public @ResponseBody Map<String,Object>  findStageProblem(
			Integer pageSize,Integer pageNumber,@PathVariable("stage") int stage){
		
		Map<String, Object> maps = new HashMap<>();
		//在服务端设置默认值
		pageNumber = pageNumber == null?1:pageNumber;
	    pageSize = pageSize == null?15:pageSize;
	    System.out.println("stage:" + stage);
	    
	    //分页查询问题
	    PageHelper.startPage(pageNumber, pageSize);
	    List<ProblemForm> list = this.problemService.selectByStage(stage);
	    //用PageInfo对结果进行包装
	    PageInfo<ProblemForm> page = new PageInfo<ProblemForm>(list);
	    //测试PageInfo全部属性
		
	    for(ProblemForm pr : list){
	    	System.out.println(pr.getTitle() + "  " + pr.getUser().getNickname());
	    	log.info("按类型查询问题" + pr.getTitle() + "  " + pr.getUser().getNickname());
	    }
	    maps.put("total",page.getTotal());
	    maps.put("rows", page.getList());
	    
	    /*ProblemWithBLOBs problem = this.problemService.selectByPrimaryKey(1001);
		System.out.println(problem.getTitle() + "  " + problem.getDescription());
		maps.put("total",1);
		maps.put("rows", problem);*/
		return maps;
	}
	
}
