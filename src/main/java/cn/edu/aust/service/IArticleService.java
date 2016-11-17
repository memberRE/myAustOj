package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.pojo.form.ArticleForm;

public interface IArticleService {

	List<ArticleForm> getArticleFormList();

	ArticleForm getArticleById(Integer articleId);

	List<ArticleForm> searchArticle(String search);

}
