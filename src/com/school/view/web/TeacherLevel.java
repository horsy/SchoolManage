package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Nation;
import com.school.domain.TeacherType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class TeacherLevel extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2032354227613152688L;
	@Autowired
	private ServiceDao serviceDao;
	private List<TeacherType> teacherTypeList;

	public List<TeacherType> getTeacherTypeList() {
		return teacherTypeList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		teacherTypeList = serviceDao.getAllTeacherType();
		for (int i = 0, len = teacherTypeList.size(); i < len; i++) {
			if (teacherTypeList.get(i).getId() == PublicSTH.DEFAULT_ID) {
				teacherTypeList.remove(i);
				len--;
				i--;
			}
		}
		return SUCCESS;
	}

}
