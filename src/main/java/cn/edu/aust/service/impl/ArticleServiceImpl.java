package cn.edu.aust.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.edu.aust.dao.ArticleMapper;
import cn.edu.aust.dao.ArticleTagsMapper;
import cn.edu.aust.dao.TagsMapper;
import cn.edu.aust.pojo.ArticleTags;
import cn.edu.aust.pojo.ArticleWithBLOBs;
import cn.edu.aust.pojo.Tags;
import cn.edu.aust.pojo.form.ArticleForm;
import cn.edu.aust.pojo.form.ArticleJSONForm;
import cn.edu.aust.service.IArticleService;
import cn.edu.aust.util.JSONFileUtil;
import cn.edu.aust.util.MyLogUtil;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService{
	@Resource
	private ArticleMapper articleMapper;
	
	@Resource
	private TagsMapper tagsMapper;
	
	@Resource
	private ArticleTagsMapper articleTagsMapper;

	@Override
	public List<ArticleForm> getArticleFormList() {
		List<ArticleForm> afList = this.articleMapper.getArticleFormList();
		//查询标签
		for(ArticleForm af:afList){
			List<String> tags = this.articleMapper.getArticleTags(af.getArticleId());
			af.setTags(tags);
		}
		return afList;
	}

	@Override
	public ArticleForm getArticleById(Integer articleId) {
		ArticleForm af = this.articleMapper.getArticleById(articleId);
		List<String> tags = this.articleMapper.getArticleTags(af.getArticleId());
		af.setTags(tags);
		return af;
	}

	@Override
	public List<ArticleForm> searchArticle(String search) {
		//设置模糊搜索关键字
		search = new StringBuilder("%").append(search).append("%").toString();
		//根据搜索类容查找到所有文章ID
		List<Integer> articleIdList = this.articleMapper.getArticleIdSearch(search);
		List<ArticleForm> articleList = new ArrayList<>();
		for(Integer articleId: articleIdList){
			ArticleForm af = this.articleMapper.getArticleById(articleId);
			List<String> tags = this.articleMapper.getArticleTags(af.getArticleId());
			af.setTags(tags);
			articleList.add(af);
		}
		return articleList;
	}

	@Override
	public void refreshTags(String path) {
		List<Tags> tagsJSONList = this.articleMapper.getTagsList();
		String text = JSON.toJSONString(tagsJSONList);
		Logger log =  MyLogUtil.getLogger();
		log.info("tagsJSONList写入文件路径：" + path);
		log.info("转换为JSON的Tags："+text);
		JSONFileUtil.refreshTags(path, text);
	}

	@Override
	public void refreshArticle(String path) {
		List<ArticleJSONForm> articleJSONList = this.articleMapper.getArticleJSONList();
		
		String text = JSON.toJSONString(articleJSONList);
		Logger log =  MyLogUtil.getLogger();
		log.info("articleJSONList写入文件路径：" + path);
		log.info("转换为JSON的article："+text);
		JSONFileUtil.refreshTags(path, text);
	}

	@Override
	public void addArticle(ArticleForm articleForm) {
		ArticleWithBLOBs aw = ArticleFormToArticle(articleForm);
		//添加文章
		int articleId = articleMapper.insertSelectiveReturnId(aw);
		//添加标签
		List<String> tags = articleForm.getTags();
		for(String tagName : tags){
			//去标签表中查询，如果存在此名称的标签，则直接插入，否则就需要先插入标签再在文章标签表中进行插入
			if(tagsMapper.selectTagsByTagsName(tagName) == 1){
				//查询ID
				int tagsId = tagsMapper.selectTagsIdByTagsName(tagName);
				//标签存在，向文章标签表中插入
				ArticleTags at = new ArticleTags();
				at.setArticleId(articleId);
				at.setTagsId(tagsId);
				articleTagsMapper.insertSelective(at);
				
			}else{
				//插入标签
				Tags newTags = new Tags();
				newTags.setTagname(tagName);
				int tagsId = tagsMapper.insertSelectiveReturnId(newTags);
				
				ArticleTags at = new ArticleTags();
				at.setArticleId(articleId);
				at.setTagsId(tagsId);
				articleTagsMapper.insertSelective(at);
			}
		}
	}
	
	
	private ArticleWithBLOBs ArticleFormToArticle(ArticleForm af) {
		ArticleWithBLOBs aw = new ArticleWithBLOBs();
		if (af.getArticleId() != null) {
			aw.setArticleId(af.getArticleId());
		}
		if (af.getCatelog() != null) {
			aw.setCatelog(af.getCatelog());
		}
		if (af.getContent() != null) {
			aw.setContent(af.getContent());
		}
		if (af.getStartTime() != null) {
			aw.setStartTime(af.getStartTime());
		}
		if (af.getSummary() != null) {
			aw.setSummary(af.getSummary());
		}
		if (af.getTitle() != null) {
			aw.setTitle(af.getTitle());
		}
		if (af.getTotop() != null) {
			aw.setTotop(af.getTotop());
		}
		if (af.getUser() != null) {
			aw.setUserId(af.getUser().getUserId());
		}
		if(af.getTagsSec() != null){
			// 将标签数组设置到标签List中去
			List<String> tagsList = new ArrayList<String>();
			for (int i = 0; i < af.getTagsSec().length; i++) {
				tagsList.add(af.getTagsSec()[i]);
			}
			af.setTags(tagsList);
		}
		return aw;
	}

	
}
