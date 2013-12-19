package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.BloodGroupDao;
import com.school.domain.BloodGroup;

@Component
public class BloodGroupDaoImpl implements BloodGroupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String GROUP_NAME = "group_name";
	private final String TABLE_NAME = "t_blood_group";

	@Override
	public int deleteBloodGroupById(int bloodGroupID) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " where " + ID + " =?";
		Object[] obj = { bloodGroupID };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<BloodGroup> getBloodGroupList() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + GROUP_NAME + " from "
				+ TABLE_NAME+" order by "+ID;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<BloodGroup> bloodGroupList = new ArrayList<BloodGroup>();
		while (rs.next()) {
			BloodGroup bloodGroup = new BloodGroup();
			bloodGroup.setId(rs.getInt(ID));
			bloodGroup.setGroupName(rs.getString(GROUP_NAME));
			bloodGroupList.add(bloodGroup);
		}
		return bloodGroupList;
	}

	@Override
	public int insertBloodGroup(BloodGroup bloodGroup) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + GROUP_NAME
				+ ") values(?)";
		Object[] obj = { bloodGroup.getGroupName() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateBloodGroup(BloodGroup bloodGroup) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " set " + GROUP_NAME
				+ "= ? where " + ID + "=?";
		Object[] obj = { bloodGroup.getGroupName(), bloodGroup.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
