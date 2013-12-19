package com.school.domain;

import java.io.Serializable;

public class BloodGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4917390679481577736L;
	private int id;
	private String groupName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
