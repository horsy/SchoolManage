package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Grade;
import com.school.domain.Teacher;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class AddClass extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1816288144949381760L;
	@Autowired
	private ServiceDao serviceDao;
	private int gradeId;
	private List<Grade> gradeList;
	private List<Teacher> teacherList;

	public int getGradeId() {
		return gradeId;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Grade grade = serviceDao.getGradeByGradeId(gradeId);
		gradeList = serviceDao.getGradeListByGroupId(grade.getGroupId(), 1,
				PublicSTH.ALL_ROW);
		teacherList = serviceDao.getAllTeacher();
		return INPUT;
	}

}
