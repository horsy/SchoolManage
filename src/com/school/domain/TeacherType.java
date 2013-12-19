package com.school.domain;

import java.io.Serializable;

public class TeacherType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7681648069042383867L;
	private int id;
	private String typeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
