package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.TeacherDao;
import com.school.domain.Teacher;
import com.school.publicSomething.PublicSTH;

@Component
public class TeacherDaoImpl implements TeacherDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String LOGIN_NAME = "login_name";
	private final String USER_NAME = "user_name";
	private final String PASSWORD = "passwd";
	private final String TYPE_ID = "type_id";
	private final String TELEPHONE = "telephone";
	private final String LAST_LOGIN_TIME = "last_login_time";
	private final String LAST_LOGIN_IP = "last_login_ip";
	private final String ENABLE_LOGIN = "enable_login";
	private final String SEX_ID = "sex_id";
	private final String TABLE_NAME = "t_teacher";

	@Override
	public int deleteTeacherById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " where " + ID + " =?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Teacher> getAllTeacher() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + LOGIN_NAME + "," + USER_NAME
				+ "," + PASSWORD + "," + TYPE_ID + "," + TELEPHONE + ","
				+ LAST_LOGIN_TIME + "," + LAST_LOGIN_IP + "," + ENABLE_LOGIN
				+ "," + SEX_ID + " FROM " + TABLE_NAME;
		List<Teacher> teacherList = new ArrayList<Teacher>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		while (rs.next()) {
			Teacher teacher = new Teacher();
			teacher.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			teacher.setId(rs.getInt(ID));
			teacher.setLastLoginIp(rs.getString(LAST_LOGIN_IP));
			teacher.setLastLoginTime(rs.getString(LAST_LOGIN_TIME));
			teacher.setLoginName(rs.getString(LOGIN_NAME));
			teacher.setPassword(rs.getString(PASSWORD));
			teacher.setSexId(rs.getInt(SEX_ID));
			teacher.setTelephone(rs.getString(TELEPHONE));
			teacher.setTypeId(rs.getInt(TYPE_ID));
			teacher.setUserName(rs.getString(USER_NAME));
			teacherList.add(teacher);
		}
		return teacherList;
	}

	private List<Teacher> getTeacherListByPropertyAndValue(String property,
			Object value) {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		String sqlStr = "select " + ID + "," + LOGIN_NAME + "," + USER_NAME
				+ "," + PASSWORD + "," + TYPE_ID + "," + TELEPHONE + ","
				+ LAST_LOGIN_TIME + "," + LAST_LOGIN_IP + "," + ENABLE_LOGIN
				+ "," + SEX_ID + " FROM " + TABLE_NAME + " WHERE " + property
				+ " =? ";
		Object[] obj = { value };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			Teacher teacher = new Teacher();
			teacher.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			teacher.setId(rs.getInt(ID));
			teacher.setLastLoginIp(rs.getString(LAST_LOGIN_IP));
			teacher.setLastLoginTime(rs.getString(LAST_LOGIN_TIME));
			teacher.setLoginName(rs.getString(LOGIN_NAME));
			teacher.setPassword(rs.getString(PASSWORD));
			teacher.setSexId(rs.getInt(SEX_ID));
			teacher.setTelephone(rs.getString(TELEPHONE));
			teacher.setTypeId(rs.getInt(TYPE_ID));
			teacher.setUserName(rs.getString(USER_NAME));
			teacherList.add(teacher);
		}
		return teacherList;
	}

	@Override
	public Teacher getTeacherById(int id) {
		// TODO Auto-generated method stub
		List<Teacher> teacherList = this.getTeacherListByPropertyAndValue(ID,
				id);
		return teacherList.size() > 0 ? teacherList.get(0) : null;
	}

	@Override
	public int insertTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + "(" + LOGIN_NAME + ","
				+ USER_NAME + "," + PASSWORD + "," + TYPE_ID + "," + TELEPHONE
				+ "," + ENABLE_LOGIN + "," + SEX_ID + ") VALUES(?,?,?,?,?,?,?)";
		Object[] obj = { teacher.getLoginName(), teacher.getUserName(),
				teacher.getPassword(), teacher.getTypeId(),
				teacher.getTelephone(), teacher.getEnableLogin(),
				teacher.getSexId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + LOGIN_NAME + "=?,"
				+ USER_NAME + "=?," + TYPE_ID + "=?,"
				+ TELEPHONE + "=?," + ENABLE_LOGIN + "=?," + SEX_ID
				+ "=? WHERE " + ID + "=?";
		Object[] obj = { teacher.getLoginName(), teacher.getUserName(), teacher.getTypeId(),
				teacher.getTelephone(), teacher.getEnableLogin(),
				teacher.getSexId(), teacher.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Teacher> getTeacherList(int page, int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + LOGIN_NAME + "," + USER_NAME
				+ "," + PASSWORD + "," + TYPE_ID + "," + TELEPHONE + ","
				+ LAST_LOGIN_TIME + "," + LAST_LOGIN_IP + "," + ENABLE_LOGIN
				+ "," + SEX_ID + " FROM " + TABLE_NAME + " limit ?,?";
		Object[] obj = { (page - 1) * pageSize, pageSize };
		List<Teacher> teacherList = new ArrayList<Teacher>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			Teacher teacher = new Teacher();
			teacher.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			teacher.setId(rs.getInt(ID));
			teacher.setLastLoginIp(rs.getString(LAST_LOGIN_IP));
			teacher.setLastLoginTime(rs.getString(LAST_LOGIN_TIME));
			teacher.setLoginName(rs.getString(LOGIN_NAME));
			teacher.setPassword(rs.getString(PASSWORD));
			teacher.setSexId(rs.getInt(SEX_ID));
			teacher.setTelephone(rs.getString(TELEPHONE));
			teacher.setTypeId(rs.getInt(TYPE_ID));
			teacher.setUserName(rs.getString(USER_NAME));
			teacherList.add(teacher);
		}
		return teacherList;
	}

	@Override
	public int getTeacherPageTotal(int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select count(*) from " + TABLE_NAME;
		int totalRow = jdbcTemplate.queryForInt(sqlStr);
		int pageTotal = totalRow / pageSize;
		pageTotal = totalRow % pageSize > 0 ? pageTotal + 1 : pageTotal;
		return pageTotal;
	}

	@Override
	public boolean hasTeacherLoginName(String loginName) {
		// TODO Auto-generated method stub
		String sqlStr = "select count(*) from " + TABLE_NAME + " WHERE "
				+ LOGIN_NAME + "=?";
		Object[] obj = { loginName };
		return jdbcTemplate.queryForInt(sqlStr, obj) > 0;
	}

	@Override
	public int updateTeacherTypeToDefaultByTypeIdArray(Integer[] id) {
		// TODO Auto-generated method stub
		StringBuffer idStr = new StringBuffer();
		for (int tId : id) {
			idStr.append(String.valueOf(tId) + ",");
		}
		if (idStr != null && idStr.length() > 0) {
			idStr.deleteCharAt(idStr.lastIndexOf(","));
		}
		String sqlStr = "update " + TABLE_NAME + " SET " + TYPE_ID
				+ " = "+PublicSTH.DEFAULT_ID+" WHERE " + TYPE_ID + " IN (" + idStr + ")";
		return jdbcTemplate.update(sqlStr);
	}
}
