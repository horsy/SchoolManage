package com.school.dao;

import java.util.List;

import com.school.domain.Class;

public interface ClassDao {

	public int insertClass(Class c);

	public int deleteClassById(int id);

	public int updateClass(Class c);

	public List<Class> getAllClass();

	public Class getClassById(int id);

	public List<Class> getClassListByGradeId(int gradeId);

	public List<Class> getClassListByGradeId(int gradeId, int page, int pageSize);

	public int getClassPageTotalForGrade(int gradeId, int pageSize);

	public List<Class> getClassListByTeacherId(int teacherId);

	public int updateClassTeacherIdToDefault(Integer[] teacherId);
}
