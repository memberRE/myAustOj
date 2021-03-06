package cn.edu.aust.dao;

import cn.edu.aust.pojo.Solution;

public interface SolutionMapper {
    int deleteByPrimaryKey(Integer solutionId);

    int insert(Solution record);

    int insertSelective(Solution record);

    Solution selectByPrimaryKey(Integer solutionId);

    int updateByPrimaryKeySelective(Solution record);

    int updateByPrimaryKeyWithBLOBs(Solution record);

    int updateByPrimaryKey(Solution record);
}