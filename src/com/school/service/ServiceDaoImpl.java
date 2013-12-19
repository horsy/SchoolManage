package com.school.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.school.dao.BloodGroupDao;
import com.school.dao.ClassDao;
import com.school.dao.GradeDao;
import com.school.dao.GroupInfoDao;
import com.school.dao.GroupTypeDao;
import com.school.dao.LevelDao;
import com.school.dao.LogDao;
import com.school.dao.ModuleDao;
import com.school.dao.NationDao;
import com.school.dao.PoliticalOrientationDao;
import com.school.dao.ReviewDao;
import com.school.dao.SchoolDao;
import com.school.dao.SexDao;
import com.school.dao.StudentDao;
import com.school.dao.StudentStatusDao;
import com.school.dao.SystemAdminDao;
import com.school.dao.SystemConfigDao;
import com.school.dao.TeacherDao;
import com.school.dao.TeacherTypeDao;
import com.school.dao.UserTypeDao;
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
import com.school.publicSomething.PublicSTH;

@Component
public class ServiceDaoImpl implements ServiceDao {

	@Autowired
	private GroupTypeDao groupTypeDao;
	@Autowired
	private GroupInfoDao groupInfoDao;
	@Autowired
	private TeacherTypeDao teacherTypeDao;
	@Autowired
	private StudentStatusDao studentStatusDao;
	@Autowired
	private PoliticalOrientationDao politicalOrientationDao;
	@Autowired
	private LogDao logDao;
	@Autowired
	private SystemAdminDao systemAdminDao;
	@Autowired
	private SystemConfigDao systemConfigDao;
	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private LevelDao levelDao;
	@Autowired
	private ClassDao classDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SexDao sexDao;
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private NationDao nationDao;
	@Autowired
	private BloodGroupDao bloodGroupDao;
	@Autowired
	private UserTypeDao userTypeDao;

	@Override
	public UserLoginDetails getUserLoginDetailsByLoginNameAndUserType(
			String loginName, int userTypeId) {
		// TODO Auto-generated method stub
		UserLoginDetails userLoginDetails = new UserLoginDetails();
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		userLoginDetails.setUserType(userTypeId);
		if (userTypeId == PublicSTH.USER_TYPE_OF_ADMIN) {
			SystemAdmin systemAdmin = systemAdminDao
					.getSystemAdminByLoginName(loginName);
			userLoginDetails
					.setEnabled(systemAdmin.getEnableLogin() == PublicSTH.ENABLE);
			userLoginDetails.setName(systemAdmin.getUserName());
			userLoginDetails.setPassword(systemAdmin.getPassword());
			userLoginDetails.setUserId(systemAdmin.getId());
			userLoginDetails.setUsername(systemAdmin.getLoginName());
			userLoginDetails.setLastLoginIp(systemAdmin.getLastLoginIP());
			userLoginDetails.setLastLoginTime(systemAdmin.getLastLoginTime());
			userLoginDetails.setNextPage(PublicSTH.PAGE_LOGINED[1]);
			authorities[0] = new GrantedAuthorityImpl(PublicSTH.PRIV[1]);
			userLoginDetails.setAuthorities(authorities);
		} else if (userTypeId == PublicSTH.USER_TYPE_OF_STUDENT) {
			Student student = studentDao.getStudentByStudentId(loginName);
			if (student == null) {
				userLoginDetails.setNextPage(PublicSTH.PAGE_LOGINED[2]);
				userLoginDetails.setErrorInfo("该学号不存在，确认学号是否输入正确（" + loginName
						+ "）");
				userLoginDetails.setEnabled(true);
				authorities[0] = new GrantedAuthorityImpl(PublicSTH.PRIV[2]);
				userLoginDetails.setAuthorities(authorities);
			} else if (student.getIsReviewPass() == PublicSTH.REVIEW_NOT_ADD) {
				userLoginDetails.setNextPage(PublicSTH.PAGE_LOGINED[0]);
				userLoginDetails.setUsername(loginName);
				userLoginDetails.setEnabled(true);
				userLoginDetails.setPassword(student.getPassword());
				authorities[0] = new GrantedAuthorityImpl(PublicSTH.PRIV[0]);
				userLoginDetails.setAuthorities(authorities);
			} else if (student.getIsReviewPass() == PublicSTH.REVIEW_NOT) {
				userLoginDetails.setNextPage(PublicSTH.PAGE_LOGINED[2]);
				userLoginDetails.setErrorInfo("信息已录入，待审核");
				userLoginDetails.setEnabled(true);
				userLoginDetails.setUsername(loginName);
				userLoginDetails.setPassword(student.getPassword());
				authorities[0] = new GrantedAuthorityImpl(PublicSTH.PRIV[2]);
				userLoginDetails.setAuthorities(authorities);
			} else if (student.getIsReviewPass() == PublicSTH.REVIEW_NOT_PASS) {
				userLoginDetails.setNextPage(PublicSTH.PAGE_LOGINED[0]);
				userLoginDetails.setUsername(loginName);
				userLoginDetails.setEnabled(true);
				userLoginDetails.setPassword(student.getPassword());
				authorities[0] = new GrantedAuthorityImpl(PublicSTH.PRIV[0]);
				userLoginDetails.setAuthorities(authorities);
			} else if (student.getIsReviewPass() == PublicSTH.REVIEW_PASS) {
				userLoginDetails.setNextPage(PublicSTH.PAGE_LOGINED[2]);
				userLoginDetails.setUsername(loginName);
				userLoginDetails.setEnabled(true);
				userLoginDetails.setPassword(student.getPassword());
				userLoginDetails.setErrorInfo("已通过审核");
				authorities[0] = new GrantedAuthorityImpl(PublicSTH.PRIV[2]);
				userLoginDetails.setAuthorities(authorities);
			}
		}

		return userLoginDetails;
	}

	@Override
	public int addSystemAdmin(SystemAdmin systemAdmin) {
		// TODO Auto-generated method stub
		int num = systemAdminDao.insertSystemAdmin(systemAdmin);
		this.insertLog("增加系统管理员" + systemAdmin.getLoginName());
		return num;
	}

	@Override
	public void updateLoginInfo(Log log) {
		// TODO Auto-generated method stub
		logDao.insertLog(log);
		if (log.getUserTypeId() == PublicSTH.USER_TYPE_OF_ADMIN) {
			SystemAdmin systemAdmin = new SystemAdmin();
			systemAdmin.setLastLoginIP(log.getIp());
			systemAdmin.setLastLoginTime(log.getDateTime());
			systemAdmin.setId(log.getUserId());
			systemAdminDao.updateSystemAdminLoginTime(systemAdmin);
		}
	}

	private void insertLog(String content) {
		UserLoginDetails loginUser = this.getLoginUserInfo();
		Log log = new Log();
		if (loginUser == null) {

		} else {
			log.setContent(content);
			log.setIp(loginUser.getLastLoginIp());
			log.setUserId(loginUser.getUserId());
			log.setUserName(loginUser.getUsername());
			log.setUserTypeId(loginUser.getUserType());
			log.setDateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
					.format(new Date()));
			logDao.insertLog(log);
		}
	}

	@Override
	public int setSystemConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		if (systemConfig.getLogStoreMouth() == 0) {
			systemConfig
					.setLogStoreMouth(PublicSTH.SYSTEM_CONFIG_LOG_STORE_MOUTH);
		}
		int num = systemConfigDao.setSystemConfig(systemConfig);
		this.insertLog("更新系统设置");
		return num;
	}

	@Override
	public SystemConfig getSystemCofnig() {
		// TODO Auto-generated method stub
		return systemConfigDao.getSystemConfig();
	}

	// @Override
	// public School getLocalSchoolInfo() {
	// // TODO Auto-generated method stub
	// return schoolDao.getLocalSchool();
	// }

	@Override
	public List<Module> getEnableModule() {
		// TODO Auto-generated method stub
		List<Module> moduleList = moduleDao.getAllModule();
		int size = moduleList.size();
		for (int i = 0; i < size; i++) {
			if (moduleList.get(i).getEnable() != 1) {
				moduleList.remove(i);
				i--;
				size--;
			}
		}
		return moduleList;
	}

	@Override
	public List<Grade> getGradeListByGroupId(int id, int page, int pageSize) {
		// TODO Auto-generated method stub
		if (pageSize == PublicSTH.ALL_ROW) {
			return gradeDao.getAllGradeById(id);
		} else {
			return gradeDao.getGradeListByGroupId(id, page, pageSize);
		}
	}

	@Override
	public School getSchoolById(int id) {
		// TODO Auto-generated method stub
		return schoolDao.getSchoolById(id);
	}

	@Override
	public List<Level> getAllLevel() {
		// TODO Auto-generated method stub
		return levelDao.getAllLevel();
	}

	@Override
	public int getGradePageTotal(int schoolId, int pageSize) {
		// TODO Auto-generated method stub
		return gradeDao.getGradeListPageTotal(schoolId, pageSize);
	}

	@Override
	public int addGrade(Grade grade) {
		// TODO Auto-generated method stub
		int num = gradeDao.insertGrade(grade);
		this.insertLog("添加年级：" + grade.getGradeName());

		return num;
	}

	@Override
	public boolean hasGradeNameForGrade(String gradeName, int gradeId) {
		// TODO Auto-generated method stub
		List<Grade> gradeList = gradeDao.getGradeListByGradeName(gradeName);
		boolean hasGradeName = false;
		if (gradeId == PublicSTH.NO_ID) {
			hasGradeName = gradeList.size() > 0 ? true : false;
		} else {
			for (Grade grade : gradeList) {
				if (grade.getId() != gradeId) {
					hasGradeName = true;
					break;
				}
			}
		}
		return hasGradeName;
	}

	@Override
	public List<School> getAllSchool() {
		// TODO Auto-generated method stub
		return schoolDao.getAllSchool();
	}

	@Override
	public Grade getGradeByGradeId(int gradeId) {
		// TODO Auto-generated method stub
		return gradeDao.getGradeByGradeId(gradeId);
	}

	@Override
	public List<Class> getClassListByGradeId(int gradeId, int page, int pageSize) {
		// TODO Auto-generated method stub
		if (pageSize == PublicSTH.ALL_ROW) {
			return classDao.getClassListByGradeId(gradeId);
		} else {
			return classDao.getClassListByGradeId(gradeId, page, pageSize);
		}
	}

	@Override
	public Teacher getTeacherById(int id) {
		// TODO Auto-generated method stub
		return teacherDao.getTeacherById(id);
	}

	@Override
	public List<Teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		return teacherDao.getAllTeacher();
	}

	@Override
	public int addClass(Class clas) {
		// TODO Auto-generated method stub
		int num = classDao.insertClass(clas);
		this.insertLog("添加班级：" + clas.getClassName());
		return num;
	}

	@Override
	public Class getClassById(int id) {
		// TODO Auto-generated method stub
		return classDao.getClassById(id);
	}

	@Override
	public int updateGrade(Grade grade) {
		// TODO Auto-generated method stub
		int num = gradeDao.updateGrade(grade);
		this.insertLog("更新年级信息：" + grade.getGradeName());
		return num;
	}

	@Override
	public int updateClass(Class clas) {
		// TODO Auto-generated method stub
		int num = classDao.updateClass(clas);
		this.insertLog("更新班级信息：" + clas.getClassName());
		return num;
	}

	@Override
	public List<Student> getStudentListByClassId(int classId, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		if (pageSize == PublicSTH.ALL_ROW) {
			return studentDao.getStudentListByClasssId(classId);
		} else {
			return studentDao.getStudentListByClassId(classId, page, pageSize);
		}
	}

	@Override
	public int getStudentPageTotalForClass(int classId, int pageSize) {
		// TODO Auto-generated method stub
		return studentDao.getStudentPageTotalForClass(classId, pageSize);
	}

	@Override
	public List<Sex> getAllSex() {
		// TODO Auto-generated method stub
		return sexDao.getAllSex();
	}

	@Override
	public List<Review> getAllReview() {
		// TODO Auto-generated method stub
		return reviewDao.getAllReview();
	}

	@Override
	public List<Nation> getAllNation() {
		// TODO Auto-generated method stub
		return nationDao.getAllNation();
	}

	@Override
	public List<BloodGroup> getAllBloodGroup() {
		// TODO Auto-generated method stub
		return bloodGroupDao.getBloodGroupList();
	}

	@Override
	public List<PoliticalOrientation> getAllPoliticalOrientation() {
		// TODO Auto-generated method stub
		return politicalOrientationDao.getAllPoliticalOrientation();
	}

	@Override
	public List<StudentStatus> getAllStudentStatus() {
		// TODO Auto-generated method stub
		return studentStatusDao.getAllStudentStatus();
	}

	@Override
	public Student getStudentByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return studentDao.getStudentByStudentId(studentId);
	}

	@Override
	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		int num = studentDao.insertStudent(student);
		this.insertLog("添加学生：" + student.getUserName());
		return num;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return studentDao.getStudentById(id);
	}

	@Override
	public int updateStudent(Student student) {
		// TODO Auto-generated method stub
		int num = studentDao.updateStudent(student);
		this.insertLog("修改学生信息：" + student.getUserName());
		return num;
	}

	@Override
	public int setReviewByIdAndReviewValue(int id, int isReviewPass) {
		// TODO Auto-generated method stub
		int num = studentDao.setReviewByIdAndReviewValue(id, isReviewPass);
		Student student = this.getStudentById(id);
		List<Review> reviewList = this.getAllReview();
		String reviewStr = "";
		for (Review review : reviewList) {
			if (review.getId() == isReviewPass) {
				reviewStr = review.getContent();
				break;
			}
		}
		this.insertLog("审核学生信息：" + student.getUserName() + "," + reviewStr);
		return num;
	}

	// @Override
	// public int delStudentByIdArray(String id) {
	// // TODO Auto-generated method stub
	// return studentDao.delStudentByIdArray(id);
	// }

	@Override
	public int getClassPageTotalByGradeIdAndPageSize(int gradeId, int pageSize) {
		// TODO Auto-generated method stub
		return classDao.getClassPageTotalForGrade(gradeId, pageSize);
	}

	@Override
	public int delClassByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer clasNames = new StringBuffer();
		for (int i : id) {
			List<Student> studentList = studentDao.getStudentListByClasssId(i);
			if (studentList != null && studentList.size() > 0) {
				List<Integer> idList = new ArrayList<Integer>();
				for (Student student : studentList) {
					idList.add(student.getId());
				}
				Integer[] idA = new Integer[idList.size()];
				for (int j = 0; j < idList.size(); j++) {
					idA[j] = idList.get(j);
				}
				num += this.delStudentByIdArray(idA);
			}
			Class clas = this.getClassById(i);
			num += classDao.deleteClassById(i);
			clasNames.append(clas.getClassName() + ",");
		}
		this.insertLog("删除班级：" + clasNames.toString() + "，及该班级的学生信息");
		return num;
	}

	@Override
	public int delStudentByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		for (int i : id) {
			Student student = this.getStudentById(i);
			num += studentDao.deleteStudentById(i);
			if (student.getUserName() == null
					|| "".equals(student.getUserName())) {
				names.append(student.getStudentId() + ",");
			} else {
				names.append(student.getUserName() + ",");
			}

		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除学生：" + names.toString());
		return num;
	}

	@Override
	public int delGradeByIdArray(Integer[] id, int schoolId) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer gradeName = new StringBuffer();
		for (int i : id) {
			List<Class> classList = classDao.getClassListByGradeId(i);
			if (classList != null && classList.size() > 0) {
				List<Integer> idList = new ArrayList<Integer>();
				for (Class clas : classList) {
					idList.add(clas.getId());
				}
				Integer[] idA = new Integer[idList.size()];
				for (int j = 0; j < idList.size(); j++) {
					idA[j] = idList.get(j);
				}
				num += this.delClassByIdArray(idA);
			}
			Grade grade = this.getGradeByGradeId(i);
			num += gradeDao.deleteGradeById(i);
			gradeName.append(grade.getGradeName() + ",");
		}
		this.insertLog("删除年级：" + gradeName.toString() + "及下属的班级和学生信息");
		return num;
	}

	@Override
	public List<Teacher> getTeacherList(int page, int pageSize) {
		// TODO Auto-generated method stub
		if (pageSize == -1) {
			return teacherDao.getAllTeacher();
		} else {
			return teacherDao.getTeacherList(page, pageSize);
		}
	}

	@Override
	public int getTeacherPageTotal(int pageSize) {
		// TODO Auto-generated method stub
		return teacherDao.getTeacherPageTotal(pageSize);
	}

	@Override
	public List<UserType> getAllUserType() {
		// TODO Auto-generated method stub
		return userTypeDao.getAllUserType();
	}

	@Override
	public List<TeacherType> getAllTeacherType() {
		// TODO Auto-generated method stub
		return teacherTypeDao.getAllTeacherType();
	}

	@Override
	public int addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		int num = teacherDao.insertTeacher(teacher);
		this.insertLog("添加教师：" + teacher.getUserName());
		return num;
	}

	@Override
	public boolean hasTeacherLoginName(String loginName) {
		// TODO Auto-generated method stub
		return teacherDao.hasTeacherLoginName(loginName);
	}

	@Override
	public int deleteTeacherByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		num = classDao.updateClassTeacherIdToDefault(id);
		for (int i : id) {
			Teacher teacher = teacherDao.getTeacherById(i);
			num = teacherDao.deleteTeacherById(i);
			names.append(teacher.getUserName() + ",");
		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除教师：" + names.toString());
		return num;
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		int num = teacherDao.updateTeacher(teacher);
		this.insertLog("更新教师信息：" + teacher.getUserName());
		return num;
	}

	@Override
	public UserLoginDetails getLoginUserInfo() {
		// TODO Auto-generated method stub
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				UserLoginDetails user = (UserLoginDetails) auth.getPrincipal();
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public int updateSystemAdminPassword(SystemAdmin systemAdmin) {
		// TODO Auto-generated method stub
		int num = systemAdminDao.updateSystemAdminPassword(systemAdmin);
		UserLoginDetails loginUser = this.getLoginUserInfo();
		this.insertLog("修改系统管理员（" + loginUser.getUsername() + "）密码");
		return num;
	}

	@Override
	public List<Log> getLogList(int page, int pageSize, String startTime,
			String endTime, String conditionObj, Object conditionValue) {
		// TODO Auto-generated method stub
		return logDao.getLogList(page, pageSize, startTime, endTime,
				conditionObj, conditionValue);
	}

	@Override
	public int getLogPageTotal(int pageSize, String startTime, String endTime,
			String conditionObj, Object conditionValue) {
		// TODO Auto-generated method stub
		return logDao.getLogPageTotal(pageSize, startTime, endTime,
				conditionObj, conditionValue);
	}

	@Override
	public int updateSchool(School school) {
		// TODO Auto-generated method stub
		int num = schoolDao.updateSchoolInfo(school);
		this.insertLog("修改学校信息：" + school.getSchoolName());
		return num;
	}

	@Override
	public int addTeacherType(TeacherType teacherType) {
		// TODO Auto-generated method stub
		int num = teacherTypeDao.insertTeacherType(teacherType);
		this.insertLog("添加教师职位：" + teacherType.getTypeName());
		return num;
	}

	@Override
	public int deleteTeacherTypeByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		num = teacherDao.updateTeacherTypeToDefaultByTypeIdArray(id);
		List<TeacherType> teacherTypeList = teacherTypeDao.getAllTeacherType();
		for (int i : id) {
			for (TeacherType teacherType : teacherTypeList) {
				if (teacherType.getId() == i) {
					names.append(teacherType.getTypeName() + ",");
					break;
				}
			}
			num = teacherTypeDao.deleteTeacherTypeById(i);
		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除教师职位：" + names.toString());
		return num;
	}

	@Override
	public int addNation(Nation nation) {
		// TODO Auto-generated method stub
		int num = nationDao.insertNation(nation);
		this.insertLog("添加民族：" + nation.getNation());
		return num;
	}

	@Override
	public int deleteNationByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		num = studentDao.updateStudentNationToDefaultIdByNationIdArray(id);
		List<Nation> nationList = nationDao.getAllNation();
		for (int i : id) {
			for (Nation nation : nationList) {
				if (nation.getId() == i) {
					names.append(nation.getNation() + ",");
					break;
				}
			}
			num = nationDao.deleteNationById(i);
		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除民族：" + names.toString());
		return num;
	}

	@Override
	public int addBloodGroup(BloodGroup bloodGroup) {
		// TODO Auto-generated method stub
		int num = bloodGroupDao.insertBloodGroup(bloodGroup);
		this.insertLog("添加血型：" + bloodGroup.getGroupName());
		return num;
	}

	@Override
	public int deleteBloodGroupByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		num = studentDao
				.updateStudentBloodGroupToDefaultIdByBloodGroupIdArray(id);
		List<BloodGroup> bloodGroupList = bloodGroupDao.getBloodGroupList();
		for (int i : id) {
			for (BloodGroup bloodGroup : bloodGroupList) {
				if (bloodGroup.getId() == i) {
					names.append(bloodGroup.getGroupName() + ",");
					break;
				}
			}
			num = bloodGroupDao.deleteBloodGroupById(i);
		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除血型：" + names.toString());
		return num;
	}

	@Override
	public int addPoliticalOrientation(PoliticalOrientation politicalOrientation) {
		// TODO Auto-generated method stub
		int num = politicalOrientationDao
				.addPoliticalOrientation(politicalOrientation);
		this.insertLog("添加政治面貌："
				+ politicalOrientation.getPoliticalOrientation());
		return num;
	}

	@Override
	public int deletePoliticalOrientationByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		num = studentDao
				.updateStudentPoliticalOrientationToDefaultIdByPoliticalOrientationId(id);
		List<PoliticalOrientation> politicalOrientationList = politicalOrientationDao
				.getAllPoliticalOrientation();
		for (int i : id) {
			for (PoliticalOrientation politicalOrientation : politicalOrientationList) {
				if (politicalOrientation.getId() == i) {
					names.append(politicalOrientation.getPoliticalOrientation()
							+ ",");
					break;
				}
			}
			num = politicalOrientationDao.deletePoliticalOrientionById(i);
		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除血型：" + names.toString());
		return num;
	}

	@Override
	public int addStudentStatus(StudentStatus status) {
		// TODO Auto-generated method stub
		int num = studentStatusDao.insertStudentStatus(status);
		this.insertLog("添加学生状态：" + status.getStatusName());
		return num;
	}

	@Override
	public int deleteStudentStatusByIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		int num = 0;
		StringBuffer names = new StringBuffer();
		num = studentDao.updateStudentStutasToDefaultIdByStutasId(id);
		List<StudentStatus> statusList = studentStatusDao.getAllStudentStatus();
		for (int i : id) {
			for (StudentStatus status : statusList) {
				if (status.getId() == i) {
					names.append(status.getStatusName() + ",");
					break;
				}
			}
			num = studentStatusDao.deleteStudentStatusById(i);
		}
		if (id.length > 0) {
			names.deleteCharAt(names.lastIndexOf(","));
		}
		this.insertLog("删除学生状态：" + names.toString());
		return num;
	}

	@Override
	public int addGroupInfo(GroupInfo groupInfo) {
		// TODO Auto-generated method stub
		int num = groupInfoDao.insertGroupInfo(groupInfo);
		this.insertLog("增加：" + groupInfo.getName());
		return num;
	}

	@Override
	public int deleteGroupInfoById(Integer[] id, int schoolId) {
		// TODO Auto-generated method stub
		int num = 0;
		String groupName="";
		for (int i : id) {
			GroupInfo g=groupInfoDao.getGroupInfoById(i);
			groupName+=g.getName()+",";
			List<GroupInfo> groupList = groupInfoDao.getGroupListByParentId(i);
			Integer[] gId = new Integer[groupList.size()];
			int j = 0;
			for (GroupInfo group : groupList) {
				gId[j] = group.getId();
				j++;
			}
			num+=this.deleteGroupInfoById(gId, schoolId);

			List<Grade> gradeList = gradeDao.getGradeListByGroupId(i, 1, 10000);
			Integer[] gradeId = new Integer[gradeList.size()];
			j=0;
			for(Grade grade:gradeList){
				gradeId[j]=grade.getId();
				j++;
			}
			this.delGradeByIdArray(gradeId, schoolId);
			num+=groupInfoDao.deleteGroupInfoById(i);
		}
		if(id.length>0){
			this.insertLog("删除：" + groupName);
		}
		return num;
	}

	@Override
	public List<GroupInfo> getAllGroupInfo() {
		// TODO Auto-generated method stub
		return groupInfoDao.getAllGroupInfo();
	}

	@Override
	public List<GroupInfo> getGroupListByParentId(int parentId) {
		// TODO Auto-generated method stub
		return groupInfoDao.getGroupListByParentId(parentId);
	}

	@Override
	public int updateGroupInfo(GroupInfo groupInfo) {
		// TODO Auto-generated method stub
		int num = groupInfoDao.updateGroupInfo(groupInfo);
		this.insertLog("修改：“" + groupInfo.getName() + "”的信息");
		return num;
	}

	@Override
	public List<GroupType> getAllGroupType() {
		// TODO Auto-generated method stub
		return groupTypeDao.getAllGroupType();
	}

	@Override
	public GroupInfo getGroupInfoById(int id) {
		// TODO Auto-generated method stub
		return groupInfoDao.getGroupInfoById(id);
	}

	@Override
	public List<Student> getStudentListByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.getStudentListByName(name);
	}

	@Override
	public List<GroupInfo> getGroupListByTypeId(int typeId) {
		// TODO Auto-generated method stub
		return groupInfoDao.getGroupListByTypeId(typeId);
	}
}
