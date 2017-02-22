package cn.edu.aust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.aust.dao.TestcaseMapper;
import cn.edu.aust.pojo.Testcase;
import cn.edu.aust.service.ITestcaseService;

@Service("testcaseService")
public class TestcaseServiceImpl implements ITestcaseService{

	@Resource
	private TestcaseMapper testcaseMapper;
	
	@Override
	public List<Testcase> getTestcaseList(int problemId) {
		List<Testcase> testcaseList = testcaseMapper.selectAllTestCaseByProblemId(problemId);
		return testcaseList;
	}

	@Override
	public void addTestcase(Testcase testcase) {
		this.testcaseMapper.insertSelective(testcase);
		
	}

	@Override
	public void updateTestcase(Testcase testcase) {
		this.testcaseMapper.updateByPrimaryKeySelective(testcase);
	}

	@Override
	public void deleteByPrimaryKey(int testcaseId) {
		this.testcaseMapper.deleteByPrimaryKey(testcaseId);
	}

}
