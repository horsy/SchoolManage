package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.UserTypeDao;
import com.school.domain.UserType;

@Component
public class UserTypeDaoImpl implements UserTypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String TYPE_NAME = "type_name";
	private final String ENABLE_LOGIN = "enable_login";
	private final String TABLE_NAME = "t_user_type";

	@Override
	public List<UserType> getAllUserType() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + TYPE_NAME + "," + ENABLE_LOGIN
				+ " FROM " + TABLE_NAME;
		List<UserType> userTypeList = new ArrayList<UserType>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		while (rs.next()) {
			UserType userType = new UserType();
			userType.setId(rs.getInt(ID));
			userType.setTypeName(rs.getString(TYPE_NAME));
			userType.setEnableLogin(rs.getInt(ENABLE_LOGIN));
			userTypeList.add(userType);
		}
		return userTypeList;
	}

}
