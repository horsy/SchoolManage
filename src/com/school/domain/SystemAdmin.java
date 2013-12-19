package com.school.domain;

import java.io.Serializable;

public class SystemAdmin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4228332567266395704L;
	private int id;
	private String loginName;
	private String userName;
	private String password;
	private String lastLoginTime;
	private String lastLoginIP;
	private int enableLogin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public int getEnableLogin() {
		return enableLogin;
	}

	public void setEnableLogin(int enableLogin) {
		this.enableLogin = enableLogin;
	}

}
