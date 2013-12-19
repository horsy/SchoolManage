package com.school.dao;

import java.util.List;

import com.school.domain.Teacher;

public interface TeacherDao {

	public List<Teacher> getAllTeacher();

	public int insertTeacher(Teacher teacher);

	public int updateTeacher(Teacher teacher);

	public int deleteTeacherById(int id);

	public Teacher getTeacherById(int id);

	/**
	 * 获取教师列表信息
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            列表数，如果为-1，则获取所有教师信息。
	 * @return 教师列表
	 */
	public List<Teacher> getTeacherList(int page, int pageSize);

	public int getTeacherPageTotal(int pageSize);

	public boolean hasTeacherLoginName(String loginName);

	public int updateTeacherTypeToDefaultByTypeIdArray(Integer[] id);
}
