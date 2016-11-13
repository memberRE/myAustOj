package cn.edu.aust.dao;

import cn.edu.aust.pojo.Notify;

public interface NotifyMapper {
    int deleteByPrimaryKey(Integer notifyId);

    int insert(Notify record);

    int insertSelective(Notify record);

    Notify selectByPrimaryKey(Integer notifyId);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}