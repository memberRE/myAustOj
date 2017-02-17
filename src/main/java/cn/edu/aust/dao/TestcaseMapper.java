package cn.edu.aust.dao;

import java.util.List;

import cn.edu.aust.pojo.Testcase;

public interface TestcaseMapper {
    int deleteByPrimaryKey(Integer testcaseId);

    int insert(Testcase record);

    int insertSelective(Testcase record);

    Testcase selectByPrimaryKey(Integer testcaseId);

    int updateByPrimaryKeySelective(Testcase record);

    int updateByPrimaryKey(Testcase record);

	List<Testcase> selectAllTestCaseByProblemId(int problemId);
}