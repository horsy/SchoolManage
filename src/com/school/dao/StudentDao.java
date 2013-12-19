package com.school.dao;

import java.util.List;

import com.school.domain.Student;

public interface StudentDao {

	public List<Student> getStudentListByClassId(int classId, int page,
			int pageSize);

	public List<Student> getStudentListByClasssId(int classId);

	public int insertStudent(Student student);

	public int updateStudent(Student student);

	public int deleteStudentById(int id);

	public Student getStudentById(int id);

	/**
	 * 根据学号，获取学生信息，如果该学号对应的学生信息不存在，则返回null
	 * 
	 * @param studentId
	 *            学号
	 * @return 学生信息 或 null
	 */
	public Student getStudentByStudentId(String studentId);

	public int getStudentPageTotalForClass(int classId, int pageSize);

	public int setReviewByIdAndReviewValue(int id, int review);

	public int delStudentByIdArray(String id);

	public int updateStudentNationToDefaultIdByNationIdArray(Integer[] id);

	public int updateStudentBloodGroupToDefaultIdByBloodGroupIdArray(
			Integer[] id);

	public int updateStudentPoliticalOrientationToDefaultIdByPoliticalOrientationId(
			Integer[] id);
	
	public int updateStudentStutasToDefaultIdByStutasId(Integer[]id);
	
	public List<Student>getStudentListByName(String name);

}
