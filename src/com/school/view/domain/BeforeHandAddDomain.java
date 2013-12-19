package com.school.view.domain;

import java.io.Serializable;

public class BeforeHandAddDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2790336677912674102L;
	private String studentIdStart;
	private String studentIdEnd;
	private String classId;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStudentIdStart() {
		return studentIdStart;
	}

	public void setStudentIdStart(String studentIdStart) {
		this.studentIdStart = studentIdStart;
	}

	public String getStudentIdEnd() {
		return studentIdEnd;
	}

	public void setStudentIdEnd(String studentIdEnd) {
		this.studentIdEnd = studentIdEnd;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
}
