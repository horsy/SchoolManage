package com.school.dao;

import java.util.List;

import com.school.domain.TeacherType;

public interface TeacherTypeDao {

	public List<TeacherType> getAllTeacherType();

	public int insertTeacherType(TeacherType teacherType);

	public int deleteTeacherTypeById(int id);
}
