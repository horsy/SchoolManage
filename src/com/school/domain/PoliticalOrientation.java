package com.school.domain;

import java.io.Serializable;

public class PoliticalOrientation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9047436450905072750L;
	private int id;
	private String politicalOrientation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPoliticalOrientation() {
		return politicalOrientation;
	}

	public void setPoliticalOrientation(String politicalOrientation) {
		this.politicalOrientation = politicalOrientation;
	}
}
