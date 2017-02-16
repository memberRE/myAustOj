package cn.edu.aust.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.edu.aust.pojo.User;
import cn.edu.aust.pojo.form.ArticleForm;
import cn.edu.aust.service.IArticleService;
import cn.edu.aust.util.Contants;
import cn.edu.aust.util.FilePathUtil;
import cn.edu.aust.util.MyLogUtil;
import cn.edu.aust.util.PageUtil;

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
		//判断文章内容是文件路径还是具体的文件路径
		File file = new File(articleForm.getContent());
		if(file.isFile()){
			//这里要将文件类容设置到文章中
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader (fr);
				String contentLine;
				StringBuffer sb = new StringBuffer();
				while((contentLine = br.readLine()) != null){  
					sb.append(contentLine + "\r\n");
				}
				fr.close(); 
				articleForm.setContent(sb.toString());
				articleForm.setMarkdown(1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
	
	/**
	 * 分页获取文章列表
	 * @param pageUtil
	 * @return
	 */
	@RequestMapping(value="/getAllArticle")
	 public @ResponseBody Map<String,Object> getAllArticle(
			 @RequestBody PageUtil pageUtil){
		log.info("文章列表查询：");
		Map<String, Object> maps = new HashMap<>();
	    //分页查询问题
	    PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
	    
	    List<ArticleForm> list = this.articleService.getArticleFormList();
	    //用PageInfo对结果进行包装
	    PageInfo<ArticleForm> page = new PageInfo<ArticleForm>(list);
	    log.info("文章列表查询：" + page.getList().get(0).getTitle());
	    log.info("获取的数据总数：" + page.getTotal() + "  获取的数据：" + page.getList());
	    maps.put("total",page.getTotal());
	    maps.put("rows", page.getList());
		return maps;
	}
	
	@RequestMapping(value="/toArticleAdd")
	public String toArticleAdd(){
		return "admin/article_add";
	}
	
	@RequestMapping(value="/toArticleEditor/{articleId}")
	public ModelAndView toArticleEditor(@PathVariable("articleId") Integer articleId){
		ModelAndView mav = new ModelAndView("admin/article_editor");
		ArticleForm articleForm = this.articleService.getArticleById(articleId);
		//判断文章内容是文件路径还是具体的文件路径
		File file = new File(articleForm.getContent());
		if(file.isFile()){
			//这里要将文件类容设置到文章中
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader (fr);
				String contentLine;
				StringBuffer sb = new StringBuffer();
				while((contentLine = br.readLine()) != null){  
					sb.append(contentLine + "\r\n");
				}
				fr.close(); 
				articleForm.setContent(sb.toString());
				articleForm.setMarkdown(1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//设置文章标签
		List<String> tagsList = articleForm.getTags();
		if(tagsList != null){
			StringBuffer sb = new StringBuffer();
			for(String str : tagsList){
				sb.append(str + ",");
			}
			articleForm.setTagsSec(new String[]{sb.toString()});
		}
		
		mav.addObject("article",articleForm);
		return mav;
	}
	
	/*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("/springUpload")
    public void  springUpload(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException
    {
    	System.out.println("开始上传文件");
         long  startTime=System.currentTimeMillis();
         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    //String path="F:/mavenProject/myAustOj/src/main/webapp/static/images/article/"+file.getOriginalFilename();
                    String path = FilePathUtil.getCourseFilePath() + "/src/main/webapp/static/images/article/"+file.getOriginalFilename();
                    System.out.println("创建文件路径"+path);
                    //上传
                    File ofile = new File(path);
                    file.transferTo(ofile);
                    //将图片请求路径返回返回
                    String pa = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()   + request.getContextPath() + "/static/images/article/" + file.getOriginalFilename();
                    PrintWriter out = response.getWriter();
                    out.append(pa);
                }
            }
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return ; 
    }
    
    /**
     * 管理员删除文章
     * @param userIdArray
     * @return
     */
    @RequestMapping(value="/deleteArticle",method=RequestMethod.POST)
    @ResponseBody 
    public  String deleteArticle(@RequestBody String articleId){
		String articleIdNew = articleId.substring(1, articleId.length()-1);
		String[] articleIdArrayStr = articleIdNew.split(",");
		//将字符串数组转换为整形数组
		int[] articleIdArrayInt = new int[articleIdArrayStr.length];
		for(int i = 0 ; i < articleIdArrayStr.length; i++){
			articleIdArrayInt[i] = Integer.parseInt(articleIdArrayStr[i]);
		}
		for(int articleIdArray : articleIdArrayInt){
			this.articleService.deleteArticleById(articleIdArray);
		}
    	return "删除成功";
    }
    
    	
    	
    	/**
     * 添加文章
     * @param article
     * @param response
     */
  	@RequestMapping(value="/insert")
  	@ResponseBody 
  	public  String insert(@RequestBody ArticleForm articleForm,HttpServletResponse response,HttpSession session,HttpServletRequest request){
  		System.out.println("请求到达" +   articleForm.getTitle());
  		//将文章保存到内存中
  		//String path = "F:/mavenProject/myAustOj/src/main/webapp/static/article/" + articleForm.getTitle()+ ".md";
  		String path = FilePathUtil.getCourseFilePath() + "/src/main/webapp/static/article/" + articleForm.getTitle()+ ".md";
  		System.out.println("路径：" + path);
  		File file = new File(path);
  		try {
  			FileWriter fw = new FileWriter(file);
  			fw.write(articleForm.getContent());
  			fw.flush();
  			fw.close();
  			//将文章的内容设置为路径
  			articleForm.setContent(path);
  			//设置作者
  			User user = (User) session.getAttribute("userLogin");
  			articleForm.setUser(user);
  			
  			//存入数据库
  			this.articleService.addArticle(articleForm);
  			
  			/*
  			//如果数据库中存在就不在向数据库中存入
  			int state = this.articleService.judgeArticleByaContent(path);
  			System.out.println("state" + state);
  			//说明文章已经存在
  			if(state == 1){
  				//不做其他操作
  			}else{
  				//将数据存入到数据库中
  				this.articleService.insertSelective(articleForm);
  			}
  			*/
  			/*PrintWriter pw = response.getWriter();
  			pw.write("1");
  			pw.flush();
  			pw.close();*/
  		} catch (IOException e1) {
  			e1.printStackTrace();
  			return "保存文章失败";
  		}
  		return "文章保存成功";
  	}
  	
  	
}
