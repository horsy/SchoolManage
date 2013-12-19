package com.school.domain;

import java.io.Serializable;

public class ClassCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8105940219812609061L;
	private int classId;
	private int courseId;
	private int teacherId;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
}
