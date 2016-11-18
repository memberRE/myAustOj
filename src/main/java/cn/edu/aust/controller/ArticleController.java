package cn.edu.aust.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.form.ArticleForm;
import cn.edu.aust.service.IArticleService;
import cn.edu.aust.util.Contants;
import cn.edu.aust.util.MyLogUtil;

@Controller
@RequestMapping("articles")
public class ArticleController {
	@Resource
	private IArticleService articleService;
	Logger log = MyLogUtil.getLogger();
	/**
	 * 查询文章列表
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView getArticleList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("articlelist");
		List<ArticleForm> articleFormList = this.articleService.getArticleFormList();
		PageHelper.startPage(1,Contants.ARTICLE_PAGESIZE);
	    //用PageInfo对结果进行包装
	    PageInfo<ArticleForm> pageinfo = new PageInfo<ArticleForm>(articleFormList);
		mav.addObject("pageinfo",pageinfo);
		
		//测试refreshTags,更新标签JSON文件，这里的功能将来需要转移到添加文章的控制器里边去
		//获取JSON文件路径
		//String path = request.getSession().getServletContext().getRealPath("/") + "static\\json\\tags.json";
		//开发时保存路径
		String tagsPath = Contants.STATIC_PATH + "\\json\\tags.json";
		this.articleService.refreshTags(tagsPath);
		
		//测试refreshArticle,更新标签JSON文件，这里的功能将来需要转移到添加文章的控制器里边去
		//获取JSON文件路径
		//String path = request.getSession().getServletContext().getRealPath("/") + "static\\json\\tags.json";
		//开发时保存路径
		String articlePath = Contants.STATIC_PATH + "\\json\\article.json";
		this.articleService.refreshArticle(articlePath);
		
		
		return mav;
	}
	
	/**
	 * 转到某一篇具体的文章
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/{articleId}",method=RequestMethod.GET)
	public ModelAndView getArticleById(@PathVariable("articleId") Integer articleId){
		ModelAndView mav = new ModelAndView("article");
		ArticleForm articleForm = this.articleService.getArticleById(articleId);
		mav.addObject("article",articleForm);
		return mav;
	}
	
	/**
	 * 搜索文章
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/search")
	public ModelAndView search(String search){
		ModelAndView mav = new ModelAndView("articlelist");
		log.info("search:" + search);
		List<ArticleForm> articleFormList = this.articleService.searchArticle(search);
		PageHelper.startPage(1,Contants.ARTICLE_PAGESIZE);
	    //用PageInfo对结果进行包装
	    PageInfo<ArticleForm> pageinfo = new PageInfo<ArticleForm>(articleFormList);
		mav.addObject("pageinfo",pageinfo);
		mav.addObject("search",search);
		return mav;
	}
}
