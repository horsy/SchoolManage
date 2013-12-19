package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.domain.Class;

public class BeforeHandAdd extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4994063364719879526L;
	@Autowired
	private ServiceDao serviceDao;
	private String classId;
	private List<Class> classList=new ArrayList<Class>();

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public List<Class> getClassList() {
		return classList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Class clas;
		if(PublicSTH.isNumber(classId)){
			clas=serviceDao.getClassById(Integer.parseInt(classId));
			classList=serviceDao.getClassListByGradeId(clas.getGradeId(), 1, -1);
		}
		return INPUT;
	}

}
