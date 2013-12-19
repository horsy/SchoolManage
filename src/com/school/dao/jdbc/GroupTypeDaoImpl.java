package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.GroupTypeDao;
import com.school.domain.GroupType;

@Component
public class GroupTypeDaoImpl implements GroupTypeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String NAME = "name";
	private final String PARENT_ID = "parent_id";
	private final String IS_PARENT = "is_parent";
	private final String TYPE_CODE = "type_code";
	private final String TABLE_NAME = "t_group_type";

	@Override
	public List<GroupType> getAllGroupType() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + NAME + "," + PARENT_ID + ","
				+ IS_PARENT + "," + TYPE_CODE + " FROM " + TABLE_NAME;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<GroupType> groupTypeList = new ArrayList<GroupType>();
		while (rs.next()) {
			GroupType groupType = new GroupType();
			groupType.setId(rs.getInt(ID));
			groupType.setName(rs.getString(NAME));
			groupType.setIsParent(rs.getInt(IS_PARENT));
			groupType.setParentId(rs.getInt(PARENT_ID));
			groupType.setTypeCode(rs.getString(TYPE_CODE));
			groupTypeList.add(groupType);
		}

		return groupTypeList;
	}

}
