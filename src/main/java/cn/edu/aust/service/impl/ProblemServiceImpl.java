package cn.edu.aust.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.aust.dao.ProblemMapper;
import cn.edu.aust.dao.SolutionMapper;
import cn.edu.aust.dao.TestcaseMapper;
import cn.edu.aust.judge.JudgeResult;
import cn.edu.aust.judge.MyOjCore;
import cn.edu.aust.judge.OjModel;
import cn.edu.aust.pojo.Problem;
import cn.edu.aust.pojo.ProblemWithBLOBs;
import cn.edu.aust.pojo.Solution;
import cn.edu.aust.pojo.Testcase;
import cn.edu.aust.pojo.User;
import cn.edu.aust.pojo.form.ProblemForm;
import cn.edu.aust.pojo.form.SolutionForm;
import cn.edu.aust.service.IProblemService;
import cn.edu.aust.util.FilePathUtil;

@Service("problemService")
public class ProblemServiceImpl implements IProblemService{

	@Resource
	private ProblemMapper problemMapper;
	
	@Resource
	private TestcaseMapper testcaseMapper;
	
	@Resource
	private SolutionMapper solutionMapper;
	
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

	@Override
	public String selectAllTestcase(List<String> submitdata,User user) {
		
		System.out.println("提交代码：" + submitdata.get(0) + "  " + submitdata.get(1));
		int problemId = Integer.parseInt(submitdata.get(0));
		//获取此类的名称
		String code = submitdata.get(1);
		Pattern pa = Pattern.compile("(public\\s+class\\s+)(\\w*)(\\{)");
		Matcher m = pa.matcher(code);
		if(m.find()){
			String className = m.group(2);
			System.out.println(className);
			//设置路径
			String path = FilePathUtil.getCourseFilePath() + "/src/main/webapp/static/code/";
			String filePath = path + className + ".java";
			File file = new File(filePath);
			try {
				//将代码写入到文件系统中去
				FileWriter fw = new FileWriter(file);
				fw.write(code);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//查询此问题的所有测试用例
			List<Testcase> testcaseList = this.testcaseMapper.selectAllTestCaseByProblemId(problemId);
			int runResult = -1;
			OjModel om = null;
			for(Testcase testcase : testcaseList){
				//对每一个测试用例都进行测试
				//调用后天判题服务器进行判断获取结果
				//必须保证在同一时刻不能传入的相同的类文件名
				MyOjCore moc = new MyOjCore(path, className, "main");//这里默认从main函数进入
				String[] normInput = testcase.getInput().trim().split("[\\s,;]+");//标准输入
				String[] normOutput = testcase.getOutput().trim().split("[\\s,;]+");//标准输出
				long timeLimit =  testcase.getTimeLimit();//设置时间限制
				float memoryLimit = testcase.getMemoryLimit();//设置内存限制
				moc.init();
				om = moc.run(normInput, normOutput,timeLimit,memoryLimit);
				runResult = om.getResult();
				//一旦某组数据未通过，就立即跳出循环不在进行判断
				if(runResult != JudgeResult.ACCEPTED){
					break;
				}
			}
			System.out.println("运行完成结果" + runResult);
			//删除java文件和类文件
			file.delete();
			new File( path + className + ".class").delete();
			//向数据库提交列表中写入
			Solution solution = new Solution();
			solution.setProblemId(problemId);
			solution.setUserId(user.getUserId());
			solution.setCodeLength(code.length());
			solution.setSource(code);
			solution.setSubmitDate(new Date());
			solution.setVerdict(om.getResult());
			solution.setLanguage((byte)5);
			if(om.getActualMemory() != 0.0){
				solution.setMemory((int)om.getActualMemory());
			}
			if(om.getActualTime() != 0L){
				solution.setTime((int)om.getActualTime());
			}
			this.solutionMapper.insertSelective(solution);
			return JudgeResult.toString(runResult);
		}else{
			return "代码错误，找不到主类";
		}
	}

}
