package cn.edu.aust.pojo;

import java.io.Serializable;

public class ArticleWithBLOBs extends Article implements Serializable{
	private static final long serialVersionUID = 410868741524437599L;

	private String summary;

    private String content;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}