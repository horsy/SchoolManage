package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.SexDao;
import com.school.domain.Sex;

@Component
public class SexDaoImpl implements SexDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String SEX = "sex";
	private final String TABLE_NAME = "t_sex";

	@Override
	public int deleteSexById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sex> getAllSex() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + SEX + " FROM " + TABLE_NAME;
		List<Sex> sexList = new ArrayList<Sex>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		while (rs.next()) {
			Sex sex = new Sex();
			sex.setId(rs.getInt(ID));
			sex.setSex(rs.getString(SEX));
			sexList.add(sex);
		}
		return sexList;
	}

	@Override
	public int insertSex(Sex sex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSex(Sex sex) {
		// TODO Auto-generated method stub
		return 0;
	}

}
