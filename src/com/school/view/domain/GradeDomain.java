package com.school.view.domain;

import java.io.Serializable;

public class GradeDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5991957258530951647L;
	private String inSchoolYear;
	private String levelId;
	private String gradeName;
	private String id;
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInSchoolYear() {
		return inSchoolYear;
	}

	public void setInSchoolYear(String inSchoolYear) {
		this.inSchoolYear = inSchoolYear;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
}
