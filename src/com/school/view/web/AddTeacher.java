package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Sex;
import com.school.domain.TeacherType;
import com.school.service.ServiceDao;

public class AddTeacher extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3016252113040380108L;
	@Autowired
	private ServiceDao serviceDao;
	private List<Sex> sexList;
	private List<TeacherType> teacherTypeList;

	public List<Sex> getSexList() {
		return sexList;
	}

	

	public List<TeacherType> getTeacherTypeList() {
		return teacherTypeList;
	}



	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		sexList = serviceDao.getAllSex();
		teacherTypeList = serviceDao.getAllTeacherType();
		return INPUT;
	}

}
