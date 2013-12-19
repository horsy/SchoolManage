package com.school.view.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.service.ServiceDao;

public class AddGroupInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -145606340000264614L;
	@Autowired
	private ServiceDao serviceDao;
	private String typeId;

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeId() {
		return typeId;
	}

	private int parentId;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
