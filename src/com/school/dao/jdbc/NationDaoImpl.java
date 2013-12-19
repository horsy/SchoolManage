package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.NationDao;
import com.school.domain.Nation;

@Component
public class NationDaoImpl implements NationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String NATION = "nation";
	private final String TABLE_NAME = "t_nation";

	@Override
	public List<Nation> getAllNation() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + NATION + " FROM " + TABLE_NAME +" order by "+ID+" asc";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<Nation> nationList = new ArrayList<Nation>();
		while (rs.next()) {
			Nation nation = new Nation();
			nation.setId(rs.getInt(ID));
			nation.setNation(rs.getString(NATION));
			nationList.add(nation);
		}
		return nationList;
	}

	@Override
	public int deleteNationById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int insertNation(Nation nation) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + NATION
				+ ") VALUES(?)";
		Object[] obj = { nation.getNation() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
