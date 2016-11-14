package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.pojo.Contest;
import cn.edu.aust.pojo.form.ContestForm;
import cn.edu.aust.pojo.form.ContestProblemForm;


public interface IContestService {
	
	List<ContestForm> selectContestRecentList(String date);
	
	List<ContestForm> selectContestLostList(String date);

	Contest selectByPrimaryKey(Integer contestId);

	/**
	 * 通过比赛ID查询此次比赛所有的问题
	 * @param contestId
	 * @return
	 */
	List<ContestProblemForm> selectProListByContestId(Integer contestId);
}
