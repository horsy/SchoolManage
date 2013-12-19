package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.PoliticalOrientationDao;
import com.school.domain.PoliticalOrientation;

@Component
public class PoliticalOrientationDaoImpl implements PoliticalOrientationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String POLITICAL_ORIENTATION = "political_orientation";
	private final String TABLE_NAME = "t_political_orientation";

	@Override
	public List<PoliticalOrientation> getAllPoliticalOrientation() {
		// TODO Auto-generated method stub

		String sqlStr = "select " + ID + "," + POLITICAL_ORIENTATION + " FROM "
				+ TABLE_NAME + " order by " + ID;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<PoliticalOrientation> poList = new ArrayList<PoliticalOrientation>();
		while (rs.next()) {
			PoliticalOrientation po = new PoliticalOrientation();
			po.setId(rs.getInt(ID));
			po.setPoliticalOrientation(rs.getString(POLITICAL_ORIENTATION));
			poList.add(po);
		}
		return poList;
	}

	@Override
	public int addPoliticalOrientation(PoliticalOrientation politicalOrientation) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " ("
				+ POLITICAL_ORIENTATION + ") VALUES(?)";
		Object[] obj = { politicalOrientation.getPoliticalOrientation() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int deletePoliticalOrientionById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
