package com.school.domain;

import java.io.Serializable;

public class Review implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210125278789767929L;
	private int id;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
