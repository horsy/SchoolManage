package com.school.domain;

import java.io.Serializable;

public class TeacherModulePower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9208046793939278913L;
	private int teacherId;
	private int moduleId;
	private int r;
	private int a;
	private int c;
	private int d;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

}
