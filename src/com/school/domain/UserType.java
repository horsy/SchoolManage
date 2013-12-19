package com.school.domain;

import java.io.Serializable;

public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7532116288008686792L;
	private int id;
	private String typeName;
	private int enableLogin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getEnableLogin() {
		return enableLogin;
	}

	public void setEnableLogin(int enableLogin) {
		this.enableLogin = enableLogin;
	}

}
