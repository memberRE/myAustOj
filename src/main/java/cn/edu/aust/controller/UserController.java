package cn.edu.aust.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.Solution;
import cn.edu.aust.pojo.User;
import cn.edu.aust.pojo.form.RankForm;
import cn.edu.aust.pojo.form.SolutionForm;
import cn.edu.aust.service.IUserService;
import cn.edu.aust.util.DecriptUtil;
import cn.edu.aust.util.PageUtil;

/**
 * 登录页面控制器
 * @author lvbiao
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private IUserService userService;
	private static final Logger log = LoggerFactory.getLogger(ProblemController.class);
	
	
	/**
	 * 前往登录页面
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLogin(){
		return "login";
	}
	
	/**
	 * 前往注册页面
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String toRegister(){
		return "register";
	}
	
	/**
	 * 验证用户
	 * @return
	 */
	@RequestMapping(value="/verification",method=RequestMethod.POST)
	public void verification(User user,
			String codevalidate,
			HttpServletResponse response,
			HttpServletRequest request,
			HttpSession session){
		Map<String, Object> maps = new HashMap<>();
		// 设置页面不缓存
		PrintWriter out = null;
		try {
			response.setContentType("application/json");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//验证验证码
		String code = (String) session.getAttribute("codeValidate");
		if(!code.equalsIgnoreCase(codevalidate.trim())){
			//验证码错误
			maps.put("type", "2");
			out.print(JSON.toJSON(maps));
			out.flush();
			out.close();
		}else{
			//验证码正确再继续进行其他验证
			System.out.println(user.getUsername() + "  " + user.getPassword());
			user.setUsername(user.getUsername().trim());
			user.setPassword(user.getPassword().trim());
			//将密码进行MD5加密
			try {
				user.setPassword(DecriptUtil.getMD5(user.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//进行验证
			User userLogin = this.userService.verificationUser(user);
			if(null != userLogin){
				//验证成功 将用户信息加入到session中去
				maps.put("type", "1");
				session.setAttribute("userLogin", userLogin);
			}else{
				//验证失败
				maps.put("type", "0");
			}
			out.print(JSON.toJSON(maps));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 检查用户名是否已经被占用
	 * @param username
	 */
	@RequestMapping(value="/check/{username}",method=RequestMethod.POST)
	public void checkUsername( @PathVariable("username") String username,
			HttpServletResponse response){
		Map<String, Object> maps = new HashMap<>();
		System.out.println("username:" + username);
		//检查用户名是否存在
		User user = this.userService.selectUserByUsername(username);
		if(user != null){
			//有户名已存在
			maps.put("type", "1");
		}else{
			maps.put("type", "0");
		}
		// 设置页面不缓存
		try {
			response.setContentType("application/json");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(JSON.toJSON(maps));
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 注册用户
	 * @param codeText
	 * @param response
	 */
	@RequestMapping(value="/registerUser/{codevalidate}",method=RequestMethod.POST)
	public void registerUser( @PathVariable("codevalidate") String codevalidate,
			HttpServletResponse response,
			HttpSession session,
			User user){
		Map<String, Object> maps = new HashMap<>();
		// 设置页面不缓存
		PrintWriter out = null;
		try {
			response.setContentType("application/json");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//验证验证码
		String code = (String) session.getAttribute("codeValidate");
		if(!code.equalsIgnoreCase(codevalidate.trim())){
			//验证码错误
			maps.put("type", "0");
			out.print(JSON.toJSON(maps));
			out.flush();
			out.close();
		}else{
			//验证码正确再继续进行其他验证
			//添加用户
			System.out.println(user.getUsername()+" "
					+user.getPassword() + "  " +user.getEmail());
			user.setUsername(user.getUsername().trim());
			user.setPassword(user.getPassword().trim());
			//给密码进行加密
			try {
				user.setPassword(DecriptUtil.getMD5(user.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//添加到数据库
			this.userService.insertSelective(user);
			//返回数据
			maps.put("type", "1");
			out.print(JSON.toJSON(maps));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 前往用户具体页面
	 * @param userID
	 */
	@RequestMapping(value="/getUser/{userId}",method=RequestMethod.GET)
	public ModelAndView getUser( @PathVariable("userId") String userId,
			HttpSession session){
		ModelAndView mav = new ModelAndView("user");
		User user = (User) session.getAttribute("userLogin");
		if(user == null){
			user = this.userService.selectUserById(Integer.parseInt(userId));
		}
		
		Solution acSolution = new Solution();
		acSolution.setUserId(user.getUserId());
		acSolution.setVerdict(5);
		//查询当前用户已经通过的问题ID
		List<Integer> FindACPronblemIdList = this.userService.FindACPronblemId(acSolution);
		//查询当前用户正在攻克的问题ID
		List<Integer> FindACBingPronblemIdList = this.userService.FindACBingPronblemId(acSolution);
		
		mav.addObject("ACProId",FindACPronblemIdList);
		mav.addObject("ACBingProId",FindACBingPronblemIdList);
		mav.addObject("user",user);
		return mav;
	}
	
	@RequestMapping("/update")
	public ModelAndView updateUser(User user,HttpSession session){
		//保存用户
		this.userService.updateByPrimaryKeySelective(user);
		
		//重新查询用户
		User newUser = this.userService.selectUserById(user.getUserId());
		//将session中用户更新
		session.setAttribute("userLogin", newUser);
		ModelAndView mav = new ModelAndView("redirect:/user/getUser/"+user.getUserId());
		return mav;
	}
	
	/**
	 * 用户修改头像
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateimg")
	public ModelAndView updateImg(HttpSession session,HttpServletRequest request,
			@RequestParam("file") CommonsMultipartFile file){
		User user = (User) session.getAttribute("userLogin");
		ModelAndView mav = new ModelAndView("redirect:/user/getUser/"+user.getUserId());
        long  startTime=System.currentTimeMillis();
        String path = request.getSession().getServletContext().getRealPath("/") + "static\\images\\" + file.getOriginalFilename();
        //String path="F:/mavenProject/myAustOj/src/main/webapp/static/images/"+file.getOriginalFilename();
        log.info("开始上传文件  路径" + path);
        
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       long  endTime=System.currentTimeMillis();
       System.out.println("上传文件的花费时间："+String.valueOf(endTime-startTime)+"ms");
       //修改用户头像路径
       String webpath = "/static/images/" + file.getOriginalFilename();
       user.setAvatar(webpath);
       this.userService.updateByPrimaryKeySelective(user);
       session.setAttribute("userLogin", user);
		return mav;
	}
	
	
	@RequestMapping(value = "/userRank")
	public @ResponseBody Map<String, Object> userRank(HttpSession session, @RequestBody PageUtil pageUtil) {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<RankForm> rankFormList = this.userService.userRank();

		PageHelper.startPage(pageUtil.getOffset() / pageUtil.getLimit() + 1, pageUtil.getLimit());
		// 用PageInfo对结果进行包装
		PageInfo<RankForm> page = new PageInfo<RankForm>(rankFormList);
		maps.put("total", page.getTotal());
		maps.put("rows", page.getList());
		return maps;
	}
}
