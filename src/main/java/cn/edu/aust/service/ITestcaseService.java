package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.pojo.Testcase;

public interface ITestcaseService {

	List<Testcase> getTestcaseList(int problemId);

	void addTestcase(Testcase testcase);

	void updateTestcase(Testcase testcase);

	void deleteByPrimaryKey(int testcaseId);
}
