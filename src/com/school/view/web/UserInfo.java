package com.school.view.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.UserLoginDetails;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class UserInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1011357153383129223L;
	@Autowired
	private ServiceDao serviceDao;
	private UserLoginDetails loginUser;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		loginUser = serviceDao.getLoginUserInfo();
		if(loginUser==null){
			return "login";
		}else if(loginUser.getUserType()==PublicSTH.USER_TYPE_OF_ADMIN){
			return "adminInfo";
		}else {
			return "login";
		}
	}

}
