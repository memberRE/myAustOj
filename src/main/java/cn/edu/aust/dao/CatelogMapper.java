package cn.edu.aust.dao;

import cn.edu.aust.pojo.Catelog;

public interface CatelogMapper {
    int deleteByPrimaryKey(Integer catelogId);

    int insert(Catelog record);

    int insertSelective(Catelog record);

    Catelog selectByPrimaryKey(Integer catelogId);

    int updateByPrimaryKeySelective(Catelog record);

    int updateByPrimaryKey(Catelog record);
}