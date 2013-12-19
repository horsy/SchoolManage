package com.school.dao;

import java.util.List;

import com.school.domain.GroupInfo;

public interface GroupInfoDao {

	public int insertGroupInfo(GroupInfo groupInfo);

	public int updateGroupInfo(GroupInfo groupInfo);

	public int deleteGroupInfoById(int id);

	public List<GroupInfo> getAllGroupInfo();

	public List<GroupInfo> getGroupListByParentId(int parentId);
	
	public GroupInfo getGroupInfoById(int id);
	public List<GroupInfo> getGroupListByTypeId(int typeId);
}
