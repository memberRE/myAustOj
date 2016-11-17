package cn.edu.aust.pojo.form;

import java.util.Date;
import java.util.List;

import cn.edu.aust.pojo.User;

public class ArticleForm {
	private Integer articleId;

    private String title;

    private String catelog;

    private Date startTime;

    private Boolean totop;
    
    private String summary;

    private String content;
    
    private List<String> tags;

    private User user;
    
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCatelog() {
		return catelog;
	}

	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Boolean getTotop() {
		return totop;
	}

	public void setTotop(Boolean totop) {
		this.totop = totop;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
