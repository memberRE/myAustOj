package cn.edu.aust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.aust.dao.ContestMapper;
import cn.edu.aust.dao.ProblemMapper;
import cn.edu.aust.pojo.Contest;
import cn.edu.aust.pojo.form.ContestForm;
import cn.edu.aust.pojo.form.ContestProblemForm;
import cn.edu.aust.service.IContestService;

@Service("contestService")
public class ContestServiceImpl implements IContestService{

	@Resource
	private ContestMapper contestMapper;
	
	@Resource
	private ProblemMapper problemMapper;
	
	@Override
	public List<ContestForm> selectContestRecentList(String date) {
		return this.contestMapper.selectContestRecentList(date);
	}

	@Override
	public List<ContestForm> selectContestLostList(String date) {
		return this.contestMapper.selectContestLostList(date);
	}

	@Override
	public Contest selectByPrimaryKey(Integer contestId) {
		return this.contestMapper.selectByPrimaryKey(contestId);
	}

	@Override
	public List<ContestProblemForm> selectProListByContestId(Integer contestId) {
		List<ContestProblemForm> cpfList = this.contestMapper.selectProListByContestId(contestId);
		//查询通过数量
		
		for(ContestProblemForm pf:cpfList){
			//查询每个题目的提交数量以及通过数量
			int id = pf.getProblemId();
			int ac = problemMapper.selectAcById(id);
			int submit = problemMapper.selectSubmitById(id);
			pf.setAc(ac);
			pf.setSubmit(submit);
		}
		return cpfList;
	}

}
