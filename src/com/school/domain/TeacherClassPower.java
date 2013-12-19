package com.school.domain;

import java.io.Serializable;

public class TeacherClassPower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5369312097179025607L;
	private int teacherId;
	private int classId;
	private int r;
	private int c;
	private int d;
	private int a;

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
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

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

}
