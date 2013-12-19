package com.school.view.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.GroupInfo;
import com.school.service.ServiceDao;

public class EditGroup extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3427964255631173784L;
	@Autowired
	private ServiceDao serviceDao;
	private GroupInfo groupInfo;
	private int id;
	private String errorInfo;

	public String getErrorInfo() {
		return errorInfo;
	}

	public GroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		groupInfo = serviceDao.getGroupInfoById(id);
		if (groupInfo == null) {
			errorInfo="信息不存在，请刷新列表重试";
			return "error";
		} else {
			return SUCCESS;
		}

	}

}
