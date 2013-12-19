package com.school.dao;

import com.school.domain.SystemAdmin;

public interface SystemAdminDao {

	public SystemAdmin getSystemAdminByLoginName(String loginName);

	public int insertSystemAdmin(SystemAdmin systemAdmin);

	public int updateSystemAdminLoginTime(SystemAdmin systemAdmin);
	
	public int updateSystemAdminPassword(SystemAdmin systemAdmin);
}
