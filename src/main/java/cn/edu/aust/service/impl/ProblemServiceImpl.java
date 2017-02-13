package cn.edu.aust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.aust.dao.ProblemMapper;
import cn.edu.aust.pojo.Problem;
import cn.edu.aust.pojo.ProblemWithBLOBs;
import cn.edu.aust.pojo.form.ProblemForm;
import cn.edu.aust.pojo.form.SolutionForm;
import cn.edu.aust.service.IProblemService;

@Service("problemService")
public class ProblemServiceImpl implements IProblemService{

	@Resource
	private ProblemMapper problemMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer problemId) {
		return problemMapper.deleteByPrimaryKey(problemId);
	}

	@Override
	public int insert(ProblemWithBLOBs record) {
		return problemMapper.insert(record);
	}

	@Override
	public int insertSelective(ProblemWithBLOBs record) {
		return problemMapper.insertSelective(record);
	}

	@Override
	public ProblemWithBLOBs selectByPrimaryKey(Integer problemId) {
		return problemMapper.selectByPrimaryKey(problemId);
	}

	@Override
	public int updateByPrimaryKeySelective(ProblemWithBLOBs record) {
		return problemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record) {
		return problemMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Problem record) {
		return problemMapper.updateByPrimaryKey(record);
	}

	/**
	 * 查询问题
	 */
	@Override
	public List<ProblemForm> selectByStage(Integer stage) {
		List<ProblemForm> proFormList = problemMapper.selectByStage(stage);
		for(ProblemForm pf:proFormList){
			//查询每个题目的提交数量以及通过数量
			int id = pf.getProblemId();
			int ac = problemMapper.selectAcById(id);
			int submit = problemMapper.selectSubmitById(id);
			String ratio;
			if(0 == submit && 0 == ac){
				ratio = "0";
			}else{
				ratio = (Math.round((float)ac/(float)submit*100)) + "%";
			}
			pf.setAc(ac);
			pf.setSubmit(submit);
			pf.setRatio(ratio);
			pf.setAcFra(ac + "/" + submit);
		}
		return proFormList;
	}

	@Override
	public ProblemForm selectProblemById(int id) {
		return problemMapper.selectProblemById(id);
	}

	@Override
	public List<SolutionForm> selectSubmitList(Integer userId) {
		return problemMapper.selectSubmitList(userId);
	}

	@Override
	public List<ProblemForm> selectProblemByCatelogId(int catelogId) {
		
		List<ProblemForm> proFormList = problemMapper.selectProblemByCatelogId(catelogId);
		for(ProblemForm pf:proFormList){
			//查询每个题目的提交数量以及通过数量
			int id = pf.getProblemId();
			int ac = problemMapper.selectAcById(id);
			int submit = problemMapper.selectSubmitById(id);
			String ratio;
			if(0 == submit && 0 == ac){
				ratio = "0";
			}else{
				ratio = (Math.round((float)ac/(float)submit*100)) + "%";
			}
			pf.setAc(ac);
			pf.setSubmit(submit);
			pf.setRatio(ratio);
			pf.setAcFra(ac + "/" + submit);
		}
		return proFormList;
	}

	@Override
	public List<ProblemForm> getProblemListBySearch(String search) {
		//设置模糊搜索字符串
		search = new StringBuffer("%").append(search).append("%").toString();
		List<ProblemForm> proFormList = problemMapper.getProblemListBySearch(search);
		for(ProblemForm pf:proFormList){
			//查询每个题目的提交数量以及通过数量
			int id = pf.getProblemId();
			int ac = problemMapper.selectAcById(id);
			int submit = problemMapper.selectSubmitById(id);
			String ratio;
			if(0 == submit && 0 == ac){
				ratio = "0";
			}else{
				ratio = (Math.round((float)ac/(float)submit*100)) + "%";
			}
			pf.setAc(ac);
			pf.setSubmit(submit);
			pf.setRatio(ratio);
			pf.setAcFra(ac + "/" + submit);
		}
		return proFormList;
	}

	@Override
	public List<ProblemForm> selectAllProblem() {
		List<ProblemForm> proFormList = problemMapper.selectAllProblem();
		for(ProblemForm pf:proFormList){
			//查询每个题目的提交数量以及通过数量
			int id = pf.getProblemId();
			int ac = problemMapper.selectAcById(id);
			int submit = problemMapper.selectSubmitById(id);
			String ratio;
			if(0 == submit && 0 == ac){
				ratio = "0";
			}else{
				ratio = (Math.round((float)ac/(float)submit*100)) + "%";
			}
			pf.setAc(ac);
			pf.setSubmit(submit);
			pf.setRatio(ratio);
			pf.setAcFra(ac + "/" + submit);
		}
		return proFormList;
	}

}
