package com.school.view.web;

import com.opensymphony.xwork2.ActionSupport;

public class Review extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175323484065474866L;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}
}
