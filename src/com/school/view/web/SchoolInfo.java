package com.school.view.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.School;
import com.school.service.ServiceDao;

public class SchoolInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -460282420170128698L;
	@Autowired
	private ServiceDao serviceDao;
	private com.school.domain.SystemConfig systemConfig;

	public com.school.domain.SystemConfig getSystemConfig() {
		return systemConfig;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		systemConfig = serviceDao.getSystemCofnig();
		return SUCCESS;
	}

}
