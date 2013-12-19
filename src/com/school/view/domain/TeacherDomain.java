package com.school.view.domain;

import java.io.Serializable;

public class TeacherDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3119219748506490233L;

	private String id;
	private String loginName;
	private String userName;
	private String typeId;
	private String telephone;
	private String enableLogin;
	private String sexId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEnableLogin() {
		return enableLogin;
	}

	public void setEnableLogin(String enableLogin) {
		this.enableLogin = enableLogin;
	}

	public String getSexId() {
		return sexId;
	}

	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
}
