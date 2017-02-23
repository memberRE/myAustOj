package cn.edu.aust.pojo;

import java.io.Serializable;

public class ContestProblem implements Serializable{
	private static final long serialVersionUID = -6147039558293218228L;

	private Integer contestProblemId;

    private Integer contestId;

    private Integer problemId;

    public Integer getContestProblemId() {
        return contestProblemId;
    }

    public void setContestProblemId(Integer contestProblemId) {
        this.contestProblemId = contestProblemId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
}