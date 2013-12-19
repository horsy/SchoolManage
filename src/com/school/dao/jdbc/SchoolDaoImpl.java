package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.SchoolDao;
import com.school.domain.School;
import com.school.publicSomething.PublicSTH;

@Component
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String SCHOOL_NAME = "school_name";
	private final String TARGET = "target";
	private final String IP = "ip";
	private final String PASSWORD = "passwd";
	private final String SCHOOL_TYPE = "school_type";
	private final String DATE_TIME = "date_time";
	private final String TABLE_NAME = "t_school";

	@Override
	public School getLocalSchool() {
		// TODO Auto-generated method stub
		String sqlStr = "SELECT " + ID + "," + SCHOOL_NAME + "," + TARGET + ","
				+ IP + "," + PASSWORD + "," + SCHOOL_TYPE + "," + DATE_TIME
				+ " FROM " + TABLE_NAME + " where " + SCHOOL_TYPE + "=?";
		Object[] obj = { PublicSTH.LOCAL_SCHOOL_TYPE };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		School school = new School();
		if (rs.next()) {
			school.setDateTime(rs.getString(DATE_TIME));
			school.setId(rs.getInt(ID));
			school.setIp(rs.getString(IP));
			school.setPassword(rs.getString(PASSWORD));
			school.setSchoolName(rs.getString(SCHOOL_NAME));
			school.setSchoolType(rs.getInt(SCHOOL_TYPE));
			school.setTarget(rs.getString(TARGET));
		}
		return school;
	}

	@Override
	public School getSchoolById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "SELECT " + ID + "," + SCHOOL_NAME + "," + TARGET + ","
				+ IP + "," + PASSWORD + "," + SCHOOL_TYPE + "," + DATE_TIME
				+ " FROM " + TABLE_NAME + " where " + ID + "=?";
		Object[] obj = { id };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		School school = new School();
		if (rs.next()) {
			school.setDateTime(rs.getString(DATE_TIME));
			school.setId(rs.getInt(ID));
			school.setIp(rs.getString(IP));
			school.setPassword(rs.getString(PASSWORD));
			school.setSchoolName(rs.getString(SCHOOL_NAME));
			school.setSchoolType(rs.getInt(SCHOOL_TYPE));
			school.setTarget(rs.getString(TARGET));
		}
		return school;
	}

	@Override
	public List<School> getAllSchool() {
		// TODO Auto-generated method stub
		String sqlStr = "SELECT " + ID + "," + SCHOOL_NAME + "," + TARGET + ","
				+ IP + "," + PASSWORD + "," + SCHOOL_TYPE + "," + DATE_TIME
				+ " FROM " + TABLE_NAME;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<School> schoolList = new ArrayList<School>();
		while (rs.next()) {
			School school = new School();
			school.setDateTime(rs.getString(DATE_TIME));
			school.setId(rs.getInt(ID));
			school.setIp(rs.getString(IP));
			school.setPassword(rs.getString(PASSWORD));
			school.setSchoolName(rs.getString(SCHOOL_NAME));
			school.setSchoolType(rs.getInt(SCHOOL_TYPE));
			school.setTarget(rs.getString(TARGET));
			schoolList.add(school);
		}
		return schoolList;
	}

	@Override
	public int updateSchoolInfo(School school) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + SCHOOL_NAME + "=?,"
				+ TARGET + "=?," + IP + "=?," + PASSWORD + "=?," + SCHOOL_TYPE
				+ "=?," + DATE_TIME + "=? WHERE " + ID + "=?";
		Object[] obj = { school.getSchoolName(), school.getTarget(),
				school.getIp(), school.getPassword(), school.getSchoolType(),
				school.getDateTime(), school.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
