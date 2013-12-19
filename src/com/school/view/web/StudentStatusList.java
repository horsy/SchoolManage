package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.StudentStatus;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class StudentStatusList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728826511288802024L;
	@Autowired
	private ServiceDao serviceDao;
	private List<StudentStatus>studentStatusList;

	public List<StudentStatus> getStudentStatusList() {
		return studentStatusList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		studentStatusList=serviceDao.getAllStudentStatus();
		for (int i = 0, len = studentStatusList.size(); i < len; i++) {
			if (studentStatusList.get(i).getId() == PublicSTH.DEFAULT_ID) {
				studentStatusList.remove(i);
				len--;
				i--;
			}
		}
		return SUCCESS;
	}

}
