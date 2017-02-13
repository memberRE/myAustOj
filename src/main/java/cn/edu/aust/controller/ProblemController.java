package cn.edu.aust.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.Problem;
import cn.edu.aust.pojo.Solution;
import cn.edu.aust.pojo.User;
import cn.edu.aust.pojo.form.ProblemForm;
import cn.edu.aust.pojo.form.SolutionForm;
import cn.edu.aust.service.IProblemService;
import cn.edu.aust.util.PageUtil;
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
	 public @ResponseBody Map<String,Object> findStageProblem(
			 @RequestBody PageUtil pageUtil,
			 @PathVariable("stage") int stage){
		Map<String, Object> maps = new HashMap<>();
		/*//在服务端设置默认值
		pageNumber = pageNumber == null?1:pageNumber;
	    pageSize = pageSize == null?10:pageSize;
	    System.out.println("stage:" + stage);
	    */
		//log.info("pageSize:" + pageSize + "  pageNumber:" + pageNumber);
	    //分页查询问题
	    //PageHelper.startPage(pageNumber, pageSize);
	    PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
	    
	    //如果需要查询的等级为0,则查询全部问题
	    List<ProblemForm> list;
	    if(stage == 0 ){
	    	list = this.problemService.selectAllProblem();
	    }else{
	    	list = this.problemService.selectByStage(stage);
	    }
	    //用PageInfo对结果进行包装
	    PageInfo<ProblemForm> page = new PageInfo<ProblemForm>(list);
		
	    log.info("获取的数据总数：" + page.getTotal() + "  获取的数据：" + page.getList());
	    maps.put("total",page.getTotal());
	    maps.put("rows", page.getList());
	    
		return maps;
	}
	
	
	/**
	 * 根据问题ID查询问题
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}")
	 public ModelAndView findProblemById(@PathVariable("id") int id){
		
		ModelAndView mav = new ModelAndView("problem");
		ProblemForm pf = this.problemService.selectProblemById(id);
		if(null != pf){
			mav.addObject("problem",pf);
		}else{
			mav.addObject("error","获取题目失败，题目不存在！");
		}
		return mav;
	}
	
	/**
	 * 跳转到用户提交列表页面
	 * @return
	 */
	@RequestMapping("toSubmit")
	public String toSubmit(){
		return "submit";
	}
	
	/**
	 * 获取用户的提交列表
	 * @param
	 * @return
	 */
	@RequestMapping(value="/getsubmitList")
	public @ResponseBody Map<String,Object> getsubmitList(HttpSession session,
			@RequestBody PageUtil pageUtil){
		Map<String, Object> maps = new HashMap<>();
	    PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
	    User user = (User) session.getAttribute("userLogin");
	    //查询当前用户的提交列表
	    List<SolutionForm> SolutionList = this.problemService.selectSubmitList(user.getUserId());
	    //用PageInfo对结果进行包装
	    PageInfo<SolutionForm> page = new PageInfo<SolutionForm>(SolutionList);
	    log.info("获取的数据总数：" + page.getTotal() + "  获取的数据：" + page.getList());
	    maps.put("total",page.getTotal());
	    maps.put("rows", page.getList());
		return maps;
	}
	
	//转到搜索列表页面
	@RequestMapping(value="/tocatelog/{catelogId}/{content}")
	 public ModelAndView toCatelogproblem(@PathVariable("catelogId") int catelogId,
			 @PathVariable("content") String content){
		ModelAndView mav = new ModelAndView("catelogproblem");
		mav.addObject("catelogId",catelogId);
		mav.addObject("content",content);
		return mav;
	}
	
	
	@RequestMapping(value="/catelog/{catelogId}")
	 public @ResponseBody Map<String,Object> findProblemByCatelogId(
			 @PathVariable("catelogId") int catelogId,
			 @RequestBody PageUtil pageUtil){
		Map<String, Object> maps = new HashMap<>();
		PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
		List<ProblemForm> pfList = this.problemService.selectProblemByCatelogId(catelogId);
		 //用PageInfo对结果进行包装
	    PageInfo<ProblemForm> page = new PageInfo<ProblemForm>(pfList);
			maps.put("total",page.getTotal());
		    maps.put("rows", page.getList());
		return maps;
	}
	
	//转到搜索列表页面
		@RequestMapping(value="/search",method=RequestMethod.GET)
		 public ModelAndView toSearchproblem(String search){
			ModelAndView mav = new ModelAndView("searchproblem");
			mav.addObject("search",search);
			return mav;
		}
		
		@RequestMapping(value="/search/{search}",method=RequestMethod.POST)
		 public @ResponseBody Map<String,Object> getProblemListBySearch(
				 @PathVariable("search") String search,
				 @RequestBody PageUtil pageUtil){
			Map<String, Object> maps = new HashMap<>();
			PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
			List<ProblemForm> pfList = this.problemService.getProblemListBySearch(search);
			 //用PageInfo对结果进行包装
		    PageInfo<ProblemForm> page = new PageInfo<ProblemForm>(pfList);
				maps.put("total",page.getTotal());
			    maps.put("rows", page.getList());
			return maps;
		}
}
