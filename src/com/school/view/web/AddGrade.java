package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Level;
import com.school.service.ServiceDao;

public class AddGrade extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6330759296033886270L;
	@Autowired
	private ServiceDao serviceDao;
	private List<Level> levelList;
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<Level> getLevelList() {
		return levelList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		levelList = serviceDao.getAllLevel();
		return INPUT;
	}

}
