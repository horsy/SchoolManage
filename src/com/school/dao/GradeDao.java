package com.school.dao;

import java.util.List;

import com.school.domain.Grade;

public interface GradeDao {

	public List<Grade> getGradeListByGroupId(int groupId, int page,
			int pageSize);

	public int getGradeListPageTotal(int groupId, int pageSize);

	public List<Grade> getAllGradeById(int groupId);

	public int insertGrade(Grade grade);

	public List<Grade> getGradeListByGradeName(String gradeName);

	public Grade getGradeByGradeId(int gradeId);

	public int updateGrade(Grade grade);
	
	public int deleteGradeById(int id);
}
