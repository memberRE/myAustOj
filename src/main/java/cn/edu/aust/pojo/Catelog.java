package cn.edu.aust.pojo;

import java.io.Serializable;

public class Catelog implements Serializable{
	private static final long serialVersionUID = 1976116937117631103L;

	private Integer catelogId;

    private String catename;

    public Integer getCatelogId() {
        return catelogId;
    }

    public void setCatelogId(Integer catelogId) {
        this.catelogId = catelogId;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename == null ? null : catename.trim();
    }
}