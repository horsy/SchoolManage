package com.school.domain;

import java.io.Serializable;

public class TeacherGradePower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2871399482890438966L;
	private int teacherId;
	private int gradeId;
	private int r;
	private int c;
	private int a;
	private int d;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}
}
