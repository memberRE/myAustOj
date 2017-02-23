package cn.edu.aust.pojo.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.edu.aust.pojo.User;

public class ArticleForm implements Serializable{
	private static final long serialVersionUID = -2301465605534387848L;

	private Integer articleId;

    private String title;

    private String catelog;

    private Date startTime;

    private Boolean totop;
    
    private String summary;

    private String content;
    
    private List<String> tags;

    private String[] tagsSec;
    
    private User user;
    
    private Integer markdown = 0;//文章是否是markdown格式,默认不是 
    
	public Integer getMarkdown() {
		return markdown;
	}

	public void setMarkdown(Integer markdown) {
		this.markdown = markdown;
	}

	public String[] getTagsSec() {
		return tagsSec;
	}

	public void setTagsSec(String[] tagsSec) {
		this.tagsSec = tagsSec;
	}

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
