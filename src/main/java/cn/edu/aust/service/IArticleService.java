package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.pojo.form.ArticleForm;

public interface IArticleService {

	/**
	 * 获取文章列表
	 * @return
	 */
	List<ArticleForm> getArticleFormList();

	/**
	 * 通过ID查询文章
	 * @param articleId
	 * @return
	 */
	ArticleForm getArticleById(Integer articleId);

	/**
	 * 搜索文章
	 * @param search
	 * @return
	 */
	List<ArticleForm> searchArticle(String search);
	
	/**
	 * 更新标签文件
	 * @param path 
	 */
	void refreshTags(String path);

	/**
	 * 更新最新文章标签
	 * @param path
	 */
	void refreshArticle(String path);

}
