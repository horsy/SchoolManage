package com.school.domain;

import java.io.Serializable;

public class Class implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3520654100081086844L;
	private int id;
	private String className;
	private int gradeId;
	private int teacherId;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
}
