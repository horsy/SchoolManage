package com.school.domain;

import java.io.Serializable;

public class SystemConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4914819135721522209L;
	private int id;
	private int showLevel;
	private int logStoreMouth;
	private String systemName;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShowLevel() {
		return showLevel;
	}

	public void setShowLevel(int showLevel) {
		this.showLevel = showLevel;
	}

	public int getLogStoreMouth() {
		return logStoreMouth;
	}

	public void setLogStoreMouth(int logStoreMouth) {
		this.logStoreMouth = logStoreMouth;
	}
}
