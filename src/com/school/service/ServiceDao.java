package com.school.service;

import java.util.List;

import com.school.domain.BloodGroup;
import com.school.domain.Class;
import com.school.domain.Grade;
import com.school.domain.GroupInfo;
import com.school.domain.GroupType;
import com.school.domain.Level;
import com.school.domain.Log;
import com.school.domain.Module;
import com.school.domain.Nation;
import com.school.domain.PoliticalOrientation;
import com.school.domain.Review;
import com.school.domain.School;
import com.school.domain.Sex;
import com.school.domain.Student;
import com.school.domain.StudentStatus;
import com.school.domain.SystemAdmin;
import com.school.domain.SystemConfig;
import com.school.domain.Teacher;
import com.school.domain.TeacherType;
import com.school.domain.UserLoginDetails;
import com.school.domain.UserType;

public interface ServiceDao {

	public UserLoginDetails getUserLoginDetailsByLoginNameAndUserType(
			String loginName, int userTypeId);

	public int addSystemAdmin(SystemAdmin systemAdmin);

	public void updateLoginInfo(Log log);

	public int setSystemConfig(SystemConfig systemConfig);

	public SystemConfig getSystemCofnig();

	// public School getLocalSchoolInfo();

	public School getSchoolById(int id);

	public List<School> getAllSchool();

	public List<Module> getEnableModule();

	/**
	 * 获取年级列表
	 * 
	 * @param schoolId
	 *            学校ID
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页行数，如果是-1，则返回所有
	 * @return 年级列表
	 */
	public List<Grade> getGradeListByGroupId(int groupId, int page, int pageSize);

	public List<Level> getAllLevel();

	public int getGradePageTotal(int schoolId, int pageSize);

	public int addGrade(Grade grade);

	public boolean hasGradeNameForGrade(String gradeName, int gradeId);

	public Grade getGradeByGradeId(int gradeId);

	/**
	 * 获取年级下的班级列表
	 * 
	 * @param gradeId
	 *            班级所在的年级
	 * @param schoolId
	 *            学校ID
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页行数，-1则返回所有。
	 * @return 班级列表
	 */
	public List<Class> getClassListByGradeId(int gradeId, int page, int pageSize);

	public int getClassPageTotalByGradeIdAndPageSize(int gradeId, int pageSize);

	public Teacher getTeacherById(int id);

	public List<Teacher> getAllTeacher();

	public int addClass(Class clas);

	public Class getClassById(int id);

	public int updateGrade(Grade grade);

	public int updateClass(Class clas);

	/**
	 * 获取学生列表
	 * 
	 * @param classId
	 *            班级ID
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页大小，-1表示获取所有学生
	 * @return
	 */
	public List<Student> getStudentListByClassId(int classId, int page,
			int pageSize);

	public int getStudentPageTotalForClass(int classId, int pageSize);

	public List<Sex> getAllSex();

	public List<Review> getAllReview();

	public List<Nation> getAllNation();

	public List<BloodGroup> getAllBloodGroup();

	public List<PoliticalOrientation> getAllPoliticalOrientation();

	public List<StudentStatus> getAllStudentStatus();

	public Student getStudentByStudentId(String studentId);

	public int addStudent(Student student);

	public Student getStudentById(int id);

	public int updateStudent(Student student);

	public int setReviewByIdAndReviewValue(int id, int isReviewPass);

	// public int delStudentByIdArray(String id);

	public int delStudentByIdArray(Integer[] id);

	public int delClassByIdArray(Integer[] id);

	public int delGradeByIdArray(Integer[] id, int schoolId);

	public List<Teacher> getTeacherList(int page, int pageSize);

	public int getTeacherPageTotal(int pageSize);

	public List<UserType> getAllUserType();

	public List<TeacherType> getAllTeacherType();

	public int addTeacher(Teacher teacher);

	public boolean hasTeacherLoginName(String loginName);

	public int deleteTeacherByIdArray(Integer[] id);

	public int updateTeacher(Teacher teacher);

	public UserLoginDetails getLoginUserInfo();

	public int updateSystemAdminPassword(SystemAdmin systemAdmin);

	public List<Log> getLogList(int page, int pageSize, String startTime,
			String endTime, String conditionObj, Object conditionValue);

	public int getLogPageTotal(int pageSize, String startTime, String endTime,
			String conditionObj, Object conditionValue);

	public int updateSchool(School school);

	public int addTeacherType(TeacherType teacherType);

	public int deleteTeacherTypeByIdArray(Integer[] id);

	public int deleteNationByIdArray(Integer[] id);

	public int addNation(Nation nation);

	public int deleteBloodGroupByIdArray(Integer[] id);

	public int addBloodGroup(BloodGroup bloodGroup);

	public int addPoliticalOrientation(PoliticalOrientation politicalOrientation);

	public int deletePoliticalOrientationByIdArray(Integer[] id);

	public int addStudentStatus(StudentStatus status);

	public int deleteStudentStatusByIdArray(Integer[] id);

	public int addGroupInfo(GroupInfo groupInfo);

	public int updateGroupInfo(GroupInfo groupInfo);

	public int deleteGroupInfoById(Integer[] id, int schoolId);

	public List<GroupInfo> getAllGroupInfo();

	public List<GroupInfo> getGroupListByParentId(int parentId);

	public List<GroupType> getAllGroupType();

	public GroupInfo getGroupInfoById(int id);

	public List<Student> getStudentListByName(String name);

	public List<GroupInfo> getGroupListByTypeId(int typeId);
}
