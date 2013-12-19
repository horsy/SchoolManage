package com.school.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.SystemConfigDao;
import com.school.domain.SystemConfig;

@Component
public class SystemConfigDaoImpl implements SystemConfigDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String SHOW_LEVEL = "show_level";
	private final String LOG_STORE_MOUTH = "log_store_mouth";
	private final String TABLE_NAME = "t_system_config";
	private final String SYSTEM_NAME = "system_name";

	@Override
	public SystemConfig getSystemConfig() {
		// TODO Auto-generated method stub
		String sqlStr = "SELECT " + ID + "," + SHOW_LEVEL + ","
				+ LOG_STORE_MOUTH + "," + SYSTEM_NAME + " FROM " + TABLE_NAME;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		SystemConfig systemConfig = new SystemConfig();
		if (rs.next()) {
			systemConfig.setId(rs.getInt(ID));
			systemConfig.setLogStoreMouth(rs.getInt(LOG_STORE_MOUTH));
			systemConfig.setShowLevel(rs.getInt(SHOW_LEVEL));
			systemConfig.setSystemName(rs.getString(SYSTEM_NAME));
		}
		return systemConfig;
	}

	@Override
	public int setSystemConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		if (this.hasSystemConfig(systemConfig)) {
			return this.updateSystemConfig(systemConfig);
		} else {
			return this.insertSystemConfig(systemConfig);
		}
	}

	private boolean hasSystemConfig(SystemConfig systemConfig) {
		if (systemConfig.getId() == 0) {
			return false;
		} else {
			return true;
		}
	}

	private int updateSystemConfig(SystemConfig systemConfig) {
		String sqlStr = "update " + TABLE_NAME + " SET " + SHOW_LEVEL + "=?,"
				+ LOG_STORE_MOUTH + "=?," + SYSTEM_NAME + "=? WHERE " + ID
				+ "=?";
		Object[] obj = { systemConfig.getShowLevel(),
				systemConfig.getLogStoreMouth(), systemConfig.getSystemName(),
				systemConfig.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	private int insertSystemConfig(SystemConfig systemConfig) {
		String sqlStr = "insert into " + TABLE_NAME + " (" + SHOW_LEVEL + ","
				+ LOG_STORE_MOUTH + "," + SYSTEM_NAME + ") VALUES(?,?,?,?)";
		Object[] obj = { systemConfig.getShowLevel(),
				systemConfig.getLogStoreMouth(), systemConfig.getSystemName() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
