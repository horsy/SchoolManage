package com.school.view.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.school.domain.Module;
import com.school.domain.School;

public class MainDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3751701685457661410L;
	private String titleName;
	private String loginUser;
	private List<Module> moduleList = new ArrayList<Module>();
	private String systemName;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
