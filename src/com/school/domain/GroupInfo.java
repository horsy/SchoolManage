package com.school.domain;

import java.io.Serializable;

public class GroupInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4315144600280719038L;

	private int id;
	private int typeId;
	private int parentId;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
