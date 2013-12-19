package com.school.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.SystemAdminDao;
import com.school.domain.SystemAdmin;

@Component
public class SystemAdminDaoImpl implements SystemAdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String LOGIN_NAME = "login_name";
	private final String USER_NAME = "user_name";
	private final String PASSWORD = "passwd";
	private final String LAST_LOGIN_TIME = "last_login_time";
	private final String LAST_LOGIN_IP = "last_login_ip";
	private final String ENABLE_LOGIN = "enable_login";
	private final String TABLE_NAME = "t_system_admin";

	@Override
	public SystemAdmin getSystemAdminByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return this.getSystemAdminByPropertyAndValue(LOGIN_NAME, loginName);
	}

	private SystemAdmin getSystemAdminByPropertyAndValue(String property,
			Object value) {
		SystemAdmin systemAdmin = new SystemAdmin();

		String sqlStr = "select " + ID + "," + LOGIN_NAME + "," + USER_NAME
				+ "," + PASSWORD + "," + LAST_LOGIN_TIME + "," + LAST_LOGIN_IP
				+ "," + ENABLE_LOGIN + " from " + TABLE_NAME + " where "
				+ property + " =?";
		Object[] obj = { value };
		// try {
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);

		if (rs.next()) {
			systemAdmin.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			systemAdmin.setId(rs.getInt(ID));
			systemAdmin.setLastLoginIP(rs.getString(LAST_LOGIN_IP));
			systemAdmin.setLastLoginTime(rs.getString(LAST_LOGIN_TIME));
			systemAdmin.setPassword(rs.getString(PASSWORD));
			systemAdmin.setUserName(rs.getString(USER_NAME));
			systemAdmin.setLoginName(rs.getString(LOGIN_NAME));
		}
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return systemAdmin;
	}

	@Override
	public int insertSystemAdmin(SystemAdmin systemAdmin) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + LOGIN_NAME + ","
				+ USER_NAME + "," + PASSWORD + "," + ENABLE_LOGIN
				+ ") values(?,?,?,?)";
		Object[] obj = { systemAdmin.getLoginName(), systemAdmin.getUserName(),
				systemAdmin.getPassword(), systemAdmin.getEnableLogin() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateSystemAdminLoginTime(SystemAdmin systemAdmin) {
		// TODO Auto-generated method stub
		String sqlStr = "UPDATE " + TABLE_NAME + " SET " + LAST_LOGIN_IP
				+ "=?," + LAST_LOGIN_TIME + "=? WHERE " + ID + "=?";
		Object[] obj = { systemAdmin.getLastLoginIP(),
				systemAdmin.getLastLoginTime(), systemAdmin.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateSystemAdminPassword(SystemAdmin systemAdmin) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + PASSWORD
				+ "=? WHERE " + ID + "=?";
		Object[] obj = { systemAdmin.getPassword(), systemAdmin.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
