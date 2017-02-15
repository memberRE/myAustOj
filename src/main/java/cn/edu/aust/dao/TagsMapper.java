package cn.edu.aust.dao;

import cn.edu.aust.pojo.Tags;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagsId);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagsId);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);

	int selectTagsByTagsName(String tagName);

	int selectTagsIdByTagsName(String tagName);

	int insertSelectiveReturnId(Tags record);
}