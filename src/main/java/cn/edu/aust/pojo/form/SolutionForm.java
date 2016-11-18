package cn.edu.aust.pojo.form;

import java.io.Serializable;
import java.util.Date;

import cn.edu.aust.pojo.Problem;

public class SolutionForm implements Serializable{
	private static final long serialVersionUID = -8558440012512246159L;

	private Integer solutionId;

    private Problem problem;

    private Integer userId;

    private Integer memory;

    private Integer time;

    private Date submitDate;

    private Integer codeLength;

    private Byte language;

    private Integer verdict;

    private String source;

	public Integer getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(Integer codeLength) {
		this.codeLength = codeLength;
	}

	public Byte getLanguage() {
		return language;
	}

	public void setLanguage(Byte language) {
		this.language = language;
	}

	public Integer getVerdict() {
		return verdict;
	}

	public void setVerdict(Integer verdict) {
		this.verdict = verdict;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
    
}
