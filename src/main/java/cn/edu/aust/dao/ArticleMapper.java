package cn.edu.aust.dao;

import java.util.List;

import cn.edu.aust.pojo.Article;
import cn.edu.aust.pojo.ArticleWithBLOBs;
import cn.edu.aust.pojo.form.ArticleForm;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

	List<ArticleForm> getArticleFormList();

	List<String> getArticleTags(Integer articleId);

	ArticleForm getArticleById(Integer articleId);

	List<Integer> getArticleIdSearch(String search);
}