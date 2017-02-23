package cn.edu.aust.pojo;

import java.io.Serializable;

public class ArticleTags implements Serializable{
	private static final long serialVersionUID = 4387095174001000695L;

	private Integer articleTagsId;

    private Integer articleId;

    private Integer tagsId;

    public Integer getArticleTagsId() {
        return articleTagsId;
    }

    public void setArticleTagsId(Integer articleTagsId) {
        this.articleTagsId = articleTagsId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagsId() {
        return tagsId;
    }

    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
    }
}