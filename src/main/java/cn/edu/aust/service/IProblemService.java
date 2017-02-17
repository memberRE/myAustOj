package cn.edu.aust.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.edu.aust.pojo.Problem;
import cn.edu.aust.pojo.ProblemWithBLOBs;
import cn.edu.aust.pojo.Testcase;
import cn.edu.aust.pojo.User;
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
     * 查询全部题目
     * @return
     */
    List<ProblemForm> selectAllProblem();

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

	/**
	 * 根据分类ID查询问题
	 * @param catelogId
	 * @return
	 */
	List<ProblemForm> selectProblemByCatelogId(int catelogId);

	/**
	 * 搜索问题列表
	 * @param search
	 * @return
	 */
	List<ProblemForm> getProblemListBySearch(String search);

	/**
	 * 查询此问题下所有的测试用例
	 * @param problemId
	 * @return
	 */
	String selectAllTestcase(List<String> submitdata,User user);
    
}
