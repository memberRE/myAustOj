package cn.edu.aust.dao;

import cn.edu.aust.pojo.ArticleTags;

public interface ArticleTagsMapper {
    int deleteByPrimaryKey(Integer articleTagsId);

    int insert(ArticleTags record);

    int insertSelective(ArticleTags record);

    ArticleTags selectByPrimaryKey(Integer articleTagsId);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);
}