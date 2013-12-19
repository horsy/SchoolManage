package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Sex;
import com.school.domain.Teacher;
import com.school.domain.TeacherType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class EditTeacher extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4803604025224931191L;
	@Autowired
	private ServiceDao serviceDao;
	private String id;
	private List<Sex> sexList;
	private List<TeacherType> teacherTypeList;

	public void setId(String id) {
		this.id = id;
	}

	public List<Sex> getSexList() {
		return sexList;
	}

	public List<TeacherType> getTeacherTypeList() {
		return teacherTypeList;
	}

	private String errorInfo;

	public String getErrorInfo() {
		return errorInfo;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		id = id.trim();
		boolean isTeacher = false;
		if (PublicSTH.isNumber(id)) {
			teacher = serviceDao.getTeacherById(Integer.parseInt(id));
			if (teacher != null) {
				isTeacher = true;
				sexList = serviceDao.getAllSex();
				teacherTypeList = serviceDao.getAllTeacherType();
			} else {
				errorInfo = "教师不存在，请刷新列表";
			}

		} else {
			errorInfo = "参数错误，错误ID号";
		}
		if (isTeacher) {
			return SUCCESS;
		} else {
			return "errorInfo";
		}

	}

	private Teacher teacher;

	public Teacher getTeacher() {
		return teacher;
	}

}
