package com.school.system.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;

public class Login extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2623415718378529208L;

	/**
	 * @return
	 */
	private String version;

	public String getVersion() {
		return version;
	}

	public String execute() {
		// TODO 登录
		//取消最后访问页面
		ActionContext actionContext=ActionContext.getContext();
		Map<String,Object> session=actionContext.getSession();
		session.remove("ACEGI_SAVED_REQUEST_KEY");
		version=PublicSTH.VERSION;
		return INPUT;
	}

}
