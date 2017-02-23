package cn.edu.aust.pojo;

import java.io.Serializable;

public class Tags implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8306091683266086625L;

	private Integer tagsId;

    private String tagname;

    public Integer getTagsId() {
        return tagsId;
    }

    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname == null ? null : tagname.trim();
    }
}