package com.school.system.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginView extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1030751168884148938L;

	/**
	 * @return
	 */
	public String execute() {
		// TODO 登录
		//取消最后访问页面
		ActionContext actionContext=ActionContext.getContext();
		Map<String,Object> session=actionContext.getSession();
		session.remove("ACEGI_SAVED_REQUEST_KEY");
		
		return INPUT;
	}
}
