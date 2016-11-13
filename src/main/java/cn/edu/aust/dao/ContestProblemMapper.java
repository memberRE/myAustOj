package cn.edu.aust.dao;

import cn.edu.aust.pojo.ContestProblem;

public interface ContestProblemMapper {
    int deleteByPrimaryKey(Integer contestProblemId);

    int insert(ContestProblem record);

    int insertSelective(ContestProblem record);

    ContestProblem selectByPrimaryKey(Integer contestProblemId);

    int updateByPrimaryKeySelective(ContestProblem record);

    int updateByPrimaryKey(ContestProblem record);
}