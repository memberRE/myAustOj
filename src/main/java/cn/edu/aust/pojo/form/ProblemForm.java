package cn.edu.aust.pojo.form;

import java.io.Serializable;

import cn.edu.aust.pojo.Catelog;
import cn.edu.aust.pojo.User;

public class ProblemForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer problemId;

    private String title;
    
    private String description;

    private String input;

    private String output;

    private String sampleInput;

    private String sampleOutput;

    private String hint;

    private String tag;

    private Catelog catelog;

    private Integer stage;

    private Integer timeLimit;

    private Integer memoryLimit;

    private User user;
    
    private String ratio;//百分号表示的通过率
    
    private String acFra;//分数表示提交数
    
    private Integer ac;//提交通过的数量
    
    private Integer submit;//提交的数量
    
    public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getAcFra() {
		return acFra;
	}

	public void setAcFra(String acFra) {
		this.acFra = acFra;
	}

    public Integer getAc() {
		return ac;
	}

	public void setAc(Integer ac) {
		this.ac = ac;
	}

	public Integer getSubmit() {
		return submit;
	}

	public void setSubmit(Integer submit) {
		this.submit = submit;
	}

	public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTag() {
        return tag;
    }

    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getSampleInput() {
		return sampleInput;
	}

	public void setSampleInput(String sampleInput) {
		this.sampleInput = sampleInput;
	}

	public String getSampleOutput() {
		return sampleOutput;
	}

	public void setSampleOutput(String sampleOutput) {
		this.sampleOutput = sampleOutput;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }


    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

	public Catelog getCatelog() {
		return catelog;
	}

	public void setCatelog(Catelog catelog) {
		this.catelog = catelog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}