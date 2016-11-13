package cn.edu.aust.pojo;

import java.util.Date;

public class Article {
    private Integer articleId;

    private String title;

    private Integer userId;

    private String catelog;

    private Date startTime;

    private Boolean totop;

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
        this.title = title == null ? null : title.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCatelog() {
        return catelog;
    }

    public void setCatelog(String catelog) {
        this.catelog = catelog == null ? null : catelog.trim();
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
}