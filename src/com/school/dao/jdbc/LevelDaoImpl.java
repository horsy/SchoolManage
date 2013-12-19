package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.LevelDao;
import com.school.domain.Level;

@Component
public class LevelDaoImpl implements LevelDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String NAME = "name";
	private final String TABLE_NAME = "t_level";

	@Override
	public int deleteLevelById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Level> getAllLevel() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + NAME + " FROM " + TABLE_NAME+" order by "+ID+" desc";
		List<Level> levelList = new ArrayList<Level>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		while (rs.next()) {
			Level level = new Level();
			level.setId(rs.getInt(ID));
			level.setLevel(rs.getString(NAME));
			levelList.add(level);
		}
		return levelList;
	}

	@Override
	public Level getLevelById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + NAME + " FROM " + TABLE_NAME
				+ " where " + ID + "=?";
		Object[] obj = { id };
		Level level = new Level();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		if (rs.next()) {
			level.setId(rs.getInt(ID));
			level.setLevel(rs.getString(NAME));
		}
		return level;
	}

	@Override
	public int insertLevel(Level level) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + NAME
				+ ") VALUES ( ? )";
		Object[] obj = { level.getLevel() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateLevel(Level level) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + NAME + "=? WHERE "
				+ ID + " =?";
		Object[] obj = { level.getLevel(), level.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public boolean hasLevel(String name) {
		// TODO Auto-generated method stub
		String sqlStr = "select count(*) from " + TABLE_NAME + " WHERE " + NAME
				+ " =?";
		Object[] obj = { name };
		return jdbcTemplate.queryForInt(sqlStr, obj) > 0;
	}

}
