package cn.edu.aust.service;

import java.util.List;

import cn.edu.aust.pojo.Problem;
import cn.edu.aust.pojo.ProblemWithBLOBs;
import cn.edu.aust.pojo.form.ProblemForm;
import cn.edu.aust.pojo.form.SolutionForm;

public interface IProblemService {
	int deleteByPrimaryKey(Integer problemId);

    int insert(ProblemWithBLOBs record);

    int insertSelective(ProblemWithBLOBs record);

    ProblemWithBLOBs selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(ProblemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record);

    int updateByPrimaryKey(Problem record);
    
    /**
     * 按照问题类别查询题目
     * @param stage
     * @return
     */
    List<ProblemForm> selectByStage(Integer stage);

    /**
     * 查询具体问题
     * @param id
     * @return ProblemForm
     */
	ProblemForm selectProblemById(int id);

	/**
	 * 查询某个具体用户的提交列表
	 * @param userId
	 * @return
	 */
	List<SolutionForm> selectSubmitList(Integer userId);
    
}
