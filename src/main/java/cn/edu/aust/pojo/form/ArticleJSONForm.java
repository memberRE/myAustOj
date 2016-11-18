package cn.edu.aust.pojo.form;

import java.io.Serializable;

public class ArticleJSONForm implements Serializable {
	private static final long serialVersionUID = -9096556127527789021L;
	
	private Integer id;
	
	private String title;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
