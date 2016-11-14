package cn.edu.aust.pojo.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.aust.pojo.User;

public class ContestForm {
	private Integer contestId;

	private String title;

	private Date startTime;

	private Date endTime;

	private Integer type;

	private String password;

	private User user;

	private String description;
	
	private String localStartTime;
	
	private String localEndTime;

	public String getLocalEndTime() {
		return this.localEndTime;
	}
	
	public void setLocalEndTime(String localEndTime){
		this.localEndTime = localEndTime;
	}


	public String getLocalStartTime() {
		return this.localStartTime;
	}

	public void setLocalStartTime(String localStartTime) {
		this.localStartTime = localStartTime;
	}

	public Integer getContestId() {
		return contestId;
	}

	public void setContestId(Integer contestId) {
		this.contestId = contestId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String localStartTime = sdf.format(this.startTime);
		this.localStartTime = localStartTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String LocalEndTime = sdf.format(this.endTime);
		this.localEndTime = LocalEndTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
