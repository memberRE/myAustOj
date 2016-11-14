package cn.edu.aust.dao;

import java.util.List;

import cn.edu.aust.pojo.Contest;
import cn.edu.aust.pojo.form.ContestForm;
import cn.edu.aust.pojo.form.ContestProblemForm;

public interface ContestMapper {
    int deleteByPrimaryKey(Integer contestId);

    int insert(Contest record);

    int insertSelective(Contest record);

    Contest selectByPrimaryKey(Integer contestId);

    int updateByPrimaryKeySelective(Contest record);

    int updateByPrimaryKeyWithBLOBs(Contest record);

    int updateByPrimaryKey(Contest record);

	List<ContestForm> selectContestRecentList(String date);
	
	List<ContestForm> selectContestLostList(String date);

	List<ContestProblemForm> selectProListByContestId(Integer contestId);

	int selectAcById(int id);

	int selectSubmitById(int id);
}