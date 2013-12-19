package com.school.dao;

import java.util.List;

import com.school.domain.StudentStatus;

public interface StudentStatusDao {

	public List<StudentStatus> getAllStudentStatus();

	public int insertStudentStatus(StudentStatus status);

	public int deleteStudentStatusById(int id);
}
