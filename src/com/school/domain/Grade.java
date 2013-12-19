package com.school.domain;

import java.io.Serializable;

public class Grade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1648077869248842275L;
	private int id;
	private String gradeName;
	private int inSchoolYear;
	private int groupId;
	private int levelId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getInSchoolYear() {
		return inSchoolYear;
	}

	public void setInSchoolYear(int inSchoolYear) {
		this.inSchoolYear = inSchoolYear;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
}
