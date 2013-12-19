package com.school.system.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;

public class Exit extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1921670890737959962L;
	private String version;

	public String getVersion() {
		return version;
	}

	public String execute() {
		// TODO 登录
		//在session删除账户信息
		ActionContext actionContext=ActionContext.getContext();
		Map<String,Object> session=actionContext.getSession();
		session.remove("ACEGI_SECURITY_CONTEXT");
		
		version=PublicSTH.VERSION;
		return INPUT;
	}
}
