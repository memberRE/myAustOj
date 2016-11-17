package cn.edu.aust.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.Contest;
import cn.edu.aust.pojo.form.ContestForm;
import cn.edu.aust.pojo.form.ContestProblemForm;
import cn.edu.aust.service.IContestService;
import cn.edu.aust.util.Contants;

@Controller
@RequestMapping("/contest")
public class ContestController {

	@Resource
	private IContestService contestService;

	private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

	/**
	 * 查询所有比赛
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView toContest(Integer offset) {
		ModelAndView mav = new ModelAndView("contest");
		// 获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		// 近期比赛
		List<ContestForm> cfRecentList = new ArrayList<>();
		cfRecentList = this.contestService.selectContestRecentList(date);
		// 已结束的比赛,这个使用分页进行显示
		List<ContestForm> cfLostList = new ArrayList<>();
		cfLostList = this.contestService.selectContestLostList(date);
		offset = offset == null ? 1 : offset;
		PageHelper.startPage(offset, Contants.CONTEST_NUM);
		// 用PageInfo对结果进行包装
		PageInfo<ContestForm> page = new PageInfo<ContestForm>(cfLostList);
		mav.addObject("contestRecent", cfRecentList);
		mav.addObject("info", page);
		return mav;
	}

	/**
	 * 验证校内比赛的密码
	 * 
	 * @param contestId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/piv", method = RequestMethod.POST)
	public void getContextProblemById(ContestForm cf, HttpServletResponse response) {
		// ModelAndView mav = new ModelAndView("contestdetail");
		// 验证密码
		System.out.println(cf.getContestId() + " " + cf.getPassword());
		Contest contest = this.contestService.selectByPrimaryKey(cf.getContestId());
		System.out.println("真实密码：" + contest.getPassword());
		Map<String, Object> maps = new HashMap<>();
		maps.put("ContestId", cf.getContestId());
		if (cf.getPassword().equals(contest.getPassword())) {
			// 密码验证成功
			maps.put("type", "1");
			//验证成功后跳转到比赛的题目列表页面
			
		} else {
			// 密码验证失败
			maps.put("type", "2");
		}
		try {
			// 设置页面不缓存
			response.setContentType("application/json");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(JSON.toJSON(maps));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询比赛问题列表
	 * @return
	 */
	@RequestMapping("/contestdetail/{contestId}")
	public ModelAndView getProblemInContest(@PathVariable("contestId") Integer contestId){
		ModelAndView mav = new ModelAndView("contestdetail");
		List<ContestProblemForm> cpfList = this.contestService.selectProListByContestId(contestId);
		for(ContestProblemForm cpf:cpfList){
			log.info("比赛题目表--->ID:" + cpf.getProblemId()+ 
					"  Title:" + cpf.getTitle() +
					"  AC:" + cpf.getAc() +
					"  Submit:" + cpf.getSubmit());
		}
		mav.addObject("CP",cpfList);
		mav.addObject("contestId",contestId);
		return mav;
	}
}
