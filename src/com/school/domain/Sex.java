package com.school.domain;

import java.io.Serializable;

public class Sex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2992058602366490473L;
	private int id;
	private String sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
