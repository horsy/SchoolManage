package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.StudentDao;
import com.school.domain.Student;
import com.school.publicSomething.PublicSTH;

@Component
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String STUDENT_ID = "student_id";
	private final String USER_NAME = "user_name";
	private final String CLASS_ID = "class_id";
	private final String BIRTHDAY = "birthday";
	private final String SEX_ID = "sex_id";
	private final String IDENTITY_CARD = "identity_card";
	private final String NATION_ID = "nation_id";
	private final String HU_KOU = "hukou";
	private final String TELEPHONE = "telephone";
	private final String EMAIL = "email";
	private final String QQ = "qq";
	private final String DORM = "dorm";
	private final String BLOOD_GROUP_ID = "blood_group_id";
	private final String STATUS_ID = "status_id";
	private final String ENTRANCE_DATE = "entrance_date";
	private final String LEAVE_SCHOOL_DATE = "leave_school_date";
	private final String POLITICAL_ORIENTATION_ID = "political_orientation_id";
	private final String GUARDIAN = "guardian";
	private final String GUARDIAN_TELEPHONE = "guardian_telephone";
	private final String HOME_ADDRESS = "home_address";
	private final String TOUCH_ADDRESS = "touch_address";
	private final String SPECIAL_SKILL = "special_skill";
	private final String RESUME = "resume";
	private final String ENABLE_LOGIN = "enable_login";
	private final String IS_REVIEW_PASS = "is_review_pass";
	private final String ADD_USER = "add_user";
	private final String ADD_TIME = "add_time";
	private final String ADD_IP = "add_ip";
	private final String LAST_LOGIN_DATE = "last_login_date";
	private final String LAST_LOGIN_IP = "last_login_ip";
	private final String TABLE_NAME = "t_student";
	private final String PASSWORD = "passwd";
	private final String PHOTO = "photo";

	@Override
	public List<Student> getStudentListByClassId(int classId, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + STUDENT_ID + "," + USER_NAME
				+ "," + PASSWORD + "," + CLASS_ID + "," + BIRTHDAY + ","
				+ SEX_ID + "," + IDENTITY_CARD + "," + NATION_ID + "," + HU_KOU
				+ "," + TELEPHONE + "," + EMAIL + "," + QQ + "," + DORM + ","
				+ BLOOD_GROUP_ID + "," + STATUS_ID + "," + ENTRANCE_DATE + ","
				+ LEAVE_SCHOOL_DATE + "," + POLITICAL_ORIENTATION_ID + ","
				+ GUARDIAN + "," + GUARDIAN_TELEPHONE + "," + HOME_ADDRESS
				+ "," + TOUCH_ADDRESS + "," + SPECIAL_SKILL + "," + RESUME
				+ "," + ENABLE_LOGIN + "," + IS_REVIEW_PASS + "," + ADD_USER
				+ "," + ADD_TIME + "," + ADD_IP + "," + LAST_LOGIN_DATE + ","
				+ LAST_LOGIN_IP + " from " + TABLE_NAME + " WHERE " + CLASS_ID
				+ "=? limit ?,?";
		Object[] obj = { classId, (page - 1) * pageSize, pageSize };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		List<Student> studnetList = new ArrayList<Student>();
		while (rs.next()) {
			Student student = new Student();
			student.setAddIp(rs.getString(ADD_IP));
			student.setAddTime(rs.getString(ADD_TIME));
			student.setAddUser(rs.getString(ADD_USER));
			student.setBirthday(rs.getString(BIRTHDAY));
			student.setBloodGroupId(rs.getInt(BLOOD_GROUP_ID));
			student.setClassId(rs.getInt(CLASS_ID));
			student.setDorm(rs.getString(DORM));
			student.setEmail(rs.getString(EMAIL));
			student.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			student.setEntranceDate(rs.getString(ENTRANCE_DATE));
			student.setGuardian(rs.getString(GUARDIAN));
			student.setGuardianTelephone(rs.getString(GUARDIAN_TELEPHONE));
			student.setHomeAddress(rs.getString(HOME_ADDRESS));
			student.setHukou(rs.getString(HU_KOU));
			student.setId(rs.getInt(ID));
			student.setIdentityCardId(rs.getString(IDENTITY_CARD));
			student.setLastLoginDate(rs.getString(LAST_LOGIN_DATE));
			student.setLastLoginIp(rs.getString(LAST_LOGIN_IP));
			student.setNationId(rs.getInt(NATION_ID));
			student.setPoliticalOrientationId(rs
					.getInt(POLITICAL_ORIENTATION_ID));
			student.setLeaveSchoolDate(rs.getString(LEAVE_SCHOOL_DATE));
			student.setQq(rs.getString(QQ));
			student.setResume(rs.getString(RESUME));
			student.setSexId(rs.getInt(SEX_ID));
			student.setSpecialSkill(rs.getString(SPECIAL_SKILL));
			student.setStatusId(rs.getInt(STATUS_ID));
			student.setStudentId(rs.getString(STUDENT_ID));
			student.setTouchAddress(rs.getString(TOUCH_ADDRESS));
			student.setUserName(rs.getString(USER_NAME));
			student.setIsReviewPass(rs.getInt(IS_REVIEW_PASS));
			student.setTelephone(rs.getString(TELEPHONE));
			student.setPassword(rs.getString(PASSWORD));
			studnetList.add(student);
		}
		return studnetList;
	}

	@Override
	public List<Student> getStudentListByClasssId(int classId) {
		// TODO Auto-generated method stub

		return this.getStudentListByPropertyAndValue(CLASS_ID, classId);
	}

	@Override
	public int deleteStudentById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	private List<Student> getStudentListByPropertyAndValue(String property,
			Object value) {
		String sqlStr = "select " + ID + "," + STUDENT_ID + "," + USER_NAME
				+ "," + PASSWORD + "," + CLASS_ID + "," + BIRTHDAY + ","
				+ SEX_ID + "," + IDENTITY_CARD + "," + NATION_ID + "," + HU_KOU
				+ "," + TELEPHONE + "," + EMAIL + "," + QQ + "," + DORM + ","
				+ BLOOD_GROUP_ID + "," + STATUS_ID + "," + ENTRANCE_DATE + ","
				+ LEAVE_SCHOOL_DATE + "," + POLITICAL_ORIENTATION_ID + ","
				+ GUARDIAN + "," + GUARDIAN_TELEPHONE + "," + HOME_ADDRESS
				+ "," + TOUCH_ADDRESS + "," + SPECIAL_SKILL + "," + RESUME
				+ "," + ENABLE_LOGIN + "," + IS_REVIEW_PASS + "," + ADD_USER
				+ "," + ADD_TIME + "," + ADD_IP + "," + LAST_LOGIN_DATE + ","
				+ LAST_LOGIN_IP + "," + PHOTO + " from " + TABLE_NAME
				+ " WHERE " + property + "=?";
		Object[] obj = { value };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		List<Student> studnetList = new ArrayList<Student>();
		while (rs.next()) {
			Student student = new Student();
			student.setAddIp(rs.getString(ADD_IP));
			student.setAddTime(rs.getString(ADD_TIME));
			student.setAddUser(rs.getString(ADD_USER));
			student.setBirthday(rs.getString(BIRTHDAY));
			student.setBloodGroupId(rs.getInt(BLOOD_GROUP_ID));
			student.setClassId(rs.getInt(CLASS_ID));
			student.setDorm(rs.getString(DORM));
			student.setEmail(rs.getString(EMAIL));
			student.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			student.setEntranceDate(rs.getString(ENTRANCE_DATE));
			student.setGuardian(rs.getString(GUARDIAN));
			student.setGuardianTelephone(rs.getString(GUARDIAN_TELEPHONE));
			student.setHomeAddress(rs.getString(HOME_ADDRESS));
			student.setHukou(rs.getString(HU_KOU));
			student.setId(rs.getInt(ID));
			student.setIdentityCardId(rs.getString(IDENTITY_CARD));
			student.setLastLoginDate(rs.getString(LAST_LOGIN_DATE));
			student.setLastLoginIp(rs.getString(LAST_LOGIN_IP));
			student.setNationId(rs.getInt(NATION_ID));
			student.setPoliticalOrientationId(rs
					.getInt(POLITICAL_ORIENTATION_ID));
			student.setLeaveSchoolDate(rs.getString(LEAVE_SCHOOL_DATE));
			student.setQq(rs.getString(QQ));
			student.setResume(rs.getString(RESUME));
			student.setSexId(rs.getInt(SEX_ID));
			student.setSpecialSkill(rs.getString(SPECIAL_SKILL));
			student.setStatusId(rs.getInt(STATUS_ID));
			student.setStudentId(rs.getString(STUDENT_ID));
			student.setTouchAddress(rs.getString(TOUCH_ADDRESS));
			student.setUserName(rs.getString(USER_NAME));
			student.setIsReviewPass(rs.getInt(IS_REVIEW_PASS));
			student.setTelephone(rs.getString(TELEPHONE));
			student.setPassword(rs.getString(PASSWORD));
			student.setPhoto(rs.getString(PHOTO));
			studnetList.add(student);
		}
		return studnetList;
	}

	private List<Student> getStudentListByPropertyAndValueLike(String property,
			Object value) {
		String sqlStr = "select " + ID + "," + STUDENT_ID + "," + USER_NAME
				+ "," + PASSWORD + "," + CLASS_ID + "," + BIRTHDAY + ","
				+ SEX_ID + "," + IDENTITY_CARD + "," + NATION_ID + "," + HU_KOU
				+ "," + TELEPHONE + "," + EMAIL + "," + QQ + "," + DORM + ","
				+ BLOOD_GROUP_ID + "," + STATUS_ID + "," + ENTRANCE_DATE + ","
				+ LEAVE_SCHOOL_DATE + "," + POLITICAL_ORIENTATION_ID + ","
				+ GUARDIAN + "," + GUARDIAN_TELEPHONE + "," + HOME_ADDRESS
				+ "," + TOUCH_ADDRESS + "," + SPECIAL_SKILL + "," + RESUME
				+ "," + ENABLE_LOGIN + "," + IS_REVIEW_PASS + "," + ADD_USER
				+ "," + ADD_TIME + "," + ADD_IP + "," + LAST_LOGIN_DATE + ","
				+ LAST_LOGIN_IP + "," + PHOTO + " from " + TABLE_NAME
				+ " WHERE " + property + " like '%" + value + "%'";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<Student> studnetList = new ArrayList<Student>();
		while (rs.next()) {
			Student student = new Student();
			student.setAddIp(rs.getString(ADD_IP));
			student.setAddTime(rs.getString(ADD_TIME));
			student.setAddUser(rs.getString(ADD_USER));
			student.setBirthday(rs.getString(BIRTHDAY));
			student.setBloodGroupId(rs.getInt(BLOOD_GROUP_ID));
			student.setClassId(rs.getInt(CLASS_ID));
			student.setDorm(rs.getString(DORM));
			student.setEmail(rs.getString(EMAIL));
			student.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			student.setEntranceDate(rs.getString(ENTRANCE_DATE));
			student.setGuardian(rs.getString(GUARDIAN));
			student.setGuardianTelephone(rs.getString(GUARDIAN_TELEPHONE));
			student.setHomeAddress(rs.getString(HOME_ADDRESS));
			student.setHukou(rs.getString(HU_KOU));
			student.setId(rs.getInt(ID));
			student.setIdentityCardId(rs.getString(IDENTITY_CARD));
			student.setLastLoginDate(rs.getString(LAST_LOGIN_DATE));
			student.setLastLoginIp(rs.getString(LAST_LOGIN_IP));
			student.setNationId(rs.getInt(NATION_ID));
			student.setPoliticalOrientationId(rs
					.getInt(POLITICAL_ORIENTATION_ID));
			student.setLeaveSchoolDate(rs.getString(LEAVE_SCHOOL_DATE));
			student.setQq(rs.getString(QQ));
			student.setResume(rs.getString(RESUME));
			student.setSexId(rs.getInt(SEX_ID));
			student.setSpecialSkill(rs.getString(SPECIAL_SKILL));
			student.setStatusId(rs.getInt(STATUS_ID));
			student.setStudentId(rs.getString(STUDENT_ID));
			student.setTouchAddress(rs.getString(TOUCH_ADDRESS));
			student.setUserName(rs.getString(USER_NAME));
			student.setIsReviewPass(rs.getInt(IS_REVIEW_PASS));
			student.setTelephone(rs.getString(TELEPHONE));
			student.setPassword(rs.getString(PASSWORD));
			student.setPhoto(rs.getString(PHOTO));
			studnetList.add(student);
		}
		return studnetList;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		List<Student> studentList = this.getStudentListByPropertyAndValue(ID,
				id);
		return studentList.size() > 0 ? studentList.get(0) : null;
	}

	@Override
	public Student getStudentByStudentId(String studentId) {
		// TODO Auto-generated method stub
		List<Student> studentList = this.getStudentListByPropertyAndValue(
				STUDENT_ID, studentId);
		return studentList.size() > 0 ? studentList.get(0) : null;
	}

	@Override
	public int insertStudent(Student student) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into "
				+ TABLE_NAME
				+ "("
				+ STUDENT_ID
				+ ","
				+ USER_NAME
				+ ","
				+ PASSWORD
				+ ","
				+ CLASS_ID
				+ ","
				+ BIRTHDAY
				+ ","
				+ SEX_ID
				+ ","
				+ IDENTITY_CARD
				+ ","
				+ NATION_ID
				+ ","
				+ HU_KOU
				+ ","
				+ TELEPHONE
				+ ","
				+ EMAIL
				+ ","
				+ QQ
				+ ","
				+ DORM
				+ ","
				+ BLOOD_GROUP_ID
				+ ","
				+ STATUS_ID
				+ ","
				+ ENTRANCE_DATE
				+ ","
				+ LEAVE_SCHOOL_DATE
				+ ","
				+ POLITICAL_ORIENTATION_ID
				+ ","
				+ GUARDIAN
				+ ","
				+ GUARDIAN_TELEPHONE
				+ ","
				+ HOME_ADDRESS
				+ ","
				+ TOUCH_ADDRESS
				+ ","
				+ SPECIAL_SKILL
				+ ","
				+ RESUME
				+ ","
				+ ENABLE_LOGIN
				+ ","
				+ IS_REVIEW_PASS
				+ ","
				+ ADD_USER
				+ ","
				+ ADD_TIME
				+ ","
				+ ADD_IP
				+ ","
				+ LAST_LOGIN_DATE
				+ ","
				+ LAST_LOGIN_IP
				+ ","
				+ PHOTO
				+ ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = { student.getStudentId(), student.getUserName(),
				student.getPassword(), student.getClassId(),
				student.getBirthday(), student.getSexId(),
				student.getIdentityCardId(), student.getNationId(),
				student.getHukou(), student.getTelephone(), student.getEmail(),
				student.getQq(), student.getDorm(), student.getBloodGroupId(),
				student.getStatusId(), student.getEntranceDate(),
				student.getLeaveSchoolDate(),
				student.getPoliticalOrientationId(), student.getGuardian(),
				student.getGuardianTelephone(), student.getHomeAddress(),
				student.getTouchAddress(), student.getSpecialSkill(),
				student.getResume(), student.getEnableLogin(),
				student.getIsReviewPass(), student.getAddUser(),
				student.getAddTime(), student.getAddIp(),
				student.getLastLoginDate(), student.getLastLoginIp(),
				student.getPhoto() };

		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateStudent(Student student) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " set " + STUDENT_ID + "=?,"
				+ USER_NAME + "=?," + CLASS_ID + "=?," + BIRTHDAY + "=?,"
				+ SEX_ID + "=?," + IDENTITY_CARD + "=?," + NATION_ID + "=?,"
				+ HU_KOU + "=?," + TELEPHONE + "=?," + EMAIL + "=?," + QQ
				+ "=?," + DORM + "=?," + BLOOD_GROUP_ID + "=?," + STATUS_ID
				+ "=?," + ENTRANCE_DATE + "=?," + LEAVE_SCHOOL_DATE + "=?,"
				+ POLITICAL_ORIENTATION_ID + "=?," + GUARDIAN + "=?,"
				+ GUARDIAN_TELEPHONE + "=?," + HOME_ADDRESS + "=?,"
				+ TOUCH_ADDRESS + "=?," + SPECIAL_SKILL + "=?," + RESUME
				+ "=?," + ENABLE_LOGIN + "=?," + IS_REVIEW_PASS + "=?,"
				+ ADD_USER + "=?," + ADD_TIME + "=?," + ADD_IP + "=?,"
				+ LAST_LOGIN_DATE + "=?," + LAST_LOGIN_IP + "=?," + PHOTO
				+ "=? WHERE " + ID + "=?";
		Object[] obj = { student.getStudentId(), student.getUserName(),
				student.getClassId(), student.getBirthday(),
				student.getSexId(), student.getIdentityCardId(),
				student.getNationId(), student.getHukou(),
				student.getTelephone(), student.getEmail(), student.getQq(),
				student.getDorm(), student.getBloodGroupId(),
				student.getStatusId(), student.getEntranceDate(),
				student.getLeaveSchoolDate(),
				student.getPoliticalOrientationId(), student.getGuardian(),
				student.getGuardianTelephone(), student.getHomeAddress(),
				student.getTouchAddress(), student.getSpecialSkill(),
				student.getResume(), student.getEnableLogin(),
				student.getIsReviewPass(), student.getAddUser(),
				student.getAddTime(), student.getAddIp(),
				student.getLastLoginDate(), student.getLastLoginIp(),
				student.getPhoto(), student.getId() };

		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int getStudentPageTotalForClass(int classId, int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select count(*) from " + TABLE_NAME + " WHERE "
				+ CLASS_ID + "=" + classId;
		int totalRow = jdbcTemplate.queryForInt(sqlStr);
		int pageTotal = totalRow / pageSize;
		pageTotal = totalRow % pageSize > 0 ? pageTotal + 1 : pageTotal;
		return pageTotal;
	}

	@Override
	public int setReviewByIdAndReviewValue(int id, int review) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + IS_REVIEW_PASS
				+ "=? WHERE " + ID + "=?";
		Object[] obj = { review, id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int delStudentByIdArray(String id) {
		// TODO Auto-generated method stub

		String sqlStr = "delete from "
				+ TABLE_NAME
				+ " WHERE "
				+ ID
				+ " IN ("
				+ id.replaceAll("_", ",").replaceAll("obj", "").replaceFirst(
						",", "") + ")";
		// Object[] obj = { id.replaceAll("_", ",").replaceAll("obj",
		// "").replaceFirst(",", "") };
		return jdbcTemplate.update(sqlStr);
	}

	@Override
	public int updateStudentNationToDefaultIdByNationIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		StringBuffer idStr = new StringBuffer();
		for (int tId : id) {
			idStr.append(String.valueOf(tId) + ",");
		}
		if (idStr != null && idStr.length() > 0) {
			idStr.deleteCharAt(idStr.lastIndexOf(","));
		}
		String sqlStr = "update " + TABLE_NAME + " SET " + NATION_ID + " = "
				+ PublicSTH.DEFAULT_ID + " WHERE " + NATION_ID + " IN ("
				+ idStr + ")";
		return jdbcTemplate.update(sqlStr);
	}

	@Override
	public int updateStudentBloodGroupToDefaultIdByBloodGroupIdArray(
			Integer[] id) {
		// TODO Auto-generated method stub
		StringBuffer idStr = new StringBuffer();
		for (int tId : id) {
			idStr.append(String.valueOf(tId) + ",");
		}
		if (idStr != null && idStr.length() > 0) {
			idStr.deleteCharAt(idStr.lastIndexOf(","));
		}
		String sqlStr = "update " + TABLE_NAME + " SET " + BLOOD_GROUP_ID
				+ " = " + PublicSTH.DEFAULT_ID + " WHERE " + BLOOD_GROUP_ID
				+ " IN (" + idStr + ")";
		return jdbcTemplate.update(sqlStr);
	}

	@Override
	public int updateStudentPoliticalOrientationToDefaultIdByPoliticalOrientationId(
			Integer[] id) {
		// TODO Auto-generated method stub
		StringBuffer idStr = new StringBuffer();
		for (int tId : id) {
			idStr.append(String.valueOf(tId) + ",");
		}
		if (idStr != null && idStr.length() > 0) {
			idStr.deleteCharAt(idStr.lastIndexOf(","));
		}
		String sqlStr = "update " + TABLE_NAME + " SET "
				+ POLITICAL_ORIENTATION_ID + " = " + PublicSTH.DEFAULT_ID
				+ " WHERE " + POLITICAL_ORIENTATION_ID + " IN (" + idStr + ")";
		return jdbcTemplate.update(sqlStr);
	}

	@Override
	public int updateStudentStutasToDefaultIdByStutasId(Integer[] id) {
		// TODO Auto-generated method stub
		StringBuffer idStr = new StringBuffer();
		for (int tId : id) {
			idStr.append(String.valueOf(tId) + ",");
		}
		if (idStr != null && idStr.length() > 0) {
			idStr.deleteCharAt(idStr.lastIndexOf(","));
		}
		String sqlStr = "update " + TABLE_NAME + " SET " + STATUS_ID + " = "
				+ PublicSTH.DEFAULT_ID + " WHERE " + STATUS_ID + " IN ("
				+ idStr + ")";
		return jdbcTemplate.update(sqlStr);
	}

	@Override
	public List<Student> getStudentListByName(String name) {
		// TODO Auto-generated method stub
		return this.getStudentListByPropertyAndValueLike(USER_NAME, name);
	}

}
