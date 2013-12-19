package com.school.dao;

import java.util.List;

import com.school.domain.School;

public interface SchoolDao {

	public School getLocalSchool();

	public School getSchoolById(int id);

	public List<School> getAllSchool();
	
	public int updateSchoolInfo(School school);
}
