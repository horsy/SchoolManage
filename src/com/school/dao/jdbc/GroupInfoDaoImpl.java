package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.GroupInfoDao;
import com.school.domain.GroupInfo;

@Component
public class GroupInfoDaoImpl implements GroupInfoDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String TYPE_ID = "type_id";
	private final String PARENT_ID = "parent_id";
	private final String NAME = "name";
	private final String TABLE_NAME = "t_group_info";

	@Override
	public int deleteGroupInfoById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + " =?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<GroupInfo> getAllGroupInfo() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + TYPE_ID + "," + PARENT_ID + ","
				+ NAME + " FROM " + TABLE_NAME;
		List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		while (rs.next()) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setId(rs.getInt(ID));
			groupInfo.setName(rs.getString(NAME));
			groupInfo.setParentId(rs.getInt(PARENT_ID));
			groupInfo.setTypeId(rs.getInt(TYPE_ID));
			groupInfoList.add(groupInfo);
		}
		return groupInfoList;
	}

	@Override
	public int insertGroupInfo(GroupInfo groupInfo) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + NAME + ","
				+ TYPE_ID + "," + PARENT_ID + " ) VALUES (?,?,?)";
		Object[] obj = { groupInfo.getName(), groupInfo.getTypeId(),
				groupInfo.getParentId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateGroupInfo(GroupInfo groupInfo) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + NAME + "=?,"
				+ TYPE_ID + "=?," + PARENT_ID + "=? WHERE " + ID + "=?";
		Object[] obj = { groupInfo.getName(), groupInfo.getTypeId(),
				groupInfo.getParentId(), groupInfo.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	private List<GroupInfo> getGroupListByPropertyAndValue(String property,
			Object value) {
		String sqlStr = "select " + ID + "," + TYPE_ID + "," + PARENT_ID + ","
				+ NAME + " FROM " + TABLE_NAME + " where " + property + " =?";
		Object[] obj = { value };
		List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setId(rs.getInt(ID));
			groupInfo.setName(rs.getString(NAME));
			groupInfo.setParentId(rs.getInt(PARENT_ID));
			groupInfo.setTypeId(rs.getInt(TYPE_ID));
			groupInfoList.add(groupInfo);
		}
		return groupInfoList;
	}

	@Override
	public List<GroupInfo> getGroupListByParentId(int parentId) {
		// TODO Auto-generated method stub
		return this.getGroupListByPropertyAndValue(PARENT_ID, parentId);
	}

	@Override
	public GroupInfo getGroupInfoById(int id) {
		// TODO Auto-generated method stub
		List<GroupInfo> groupInfoList = this.getGroupListByPropertyAndValue(ID,
				id);
		if (groupInfoList.size() > 0) {
			return groupInfoList.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<GroupInfo> getGroupListByTypeId(int typeId) {
		// TODO Auto-generated method stub
		return this.getGroupListByPropertyAndValue(TYPE_ID, typeId);
	}

}
