package com.school.dao;

import com.school.domain.SystemConfig;

public interface SystemConfigDao {

	public SystemConfig getSystemConfig();
	
	public int setSystemConfig(SystemConfig systemConfig);
}
