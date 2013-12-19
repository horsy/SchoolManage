package com.school.domain;

import java.io.Serializable;

public class StudentStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8681597069272712148L;
	private int id;
	private String statusName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
