package com.school.domain;

import java.io.Serializable;

public class Nation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2403491392893325948L;
	private int id;
	private String nation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

}
