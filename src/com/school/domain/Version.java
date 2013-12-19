package com.school.domain;

import java.io.Serializable;

public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5971619524775268559L;
	private int id;
	private int dbv;
	private String pv;
	private String dbvLastUpdate;
	private String pvLastUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDbv() {
		return dbv;
	}

	public void setDbv(int dbv) {
		this.dbv = dbv;
	}

	public String getPv() {
		return pv;
	}

	public void setPv(String pv) {
		this.pv = pv;
	}

	public String getDbvLastUpdate() {
		return dbvLastUpdate;
	}

	public void setDbvLastUpdate(String dbvLastUpdate) {
		this.dbvLastUpdate = dbvLastUpdate;
	}

	public String getPvLastUpdate() {
		return pvLastUpdate;
	}

	public void setPvLastUpdate(String pvLastUpdate) {
		this.pvLastUpdate = pvLastUpdate;
	}
}
