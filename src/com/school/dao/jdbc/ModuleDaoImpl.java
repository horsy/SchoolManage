package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.ModuleDao;
import com.school.domain.Module;

@Component
public class ModuleDaoImpl implements ModuleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String MODULE_NAME = "module_name";
	private final String ENABLE = "enable";
	private final String PICTURE = "picture";
	private final String TARGET = "target";
	private final String TABLE_NAME = "t_module";

	@Override
	public int delModuleByModuleId(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE" + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Module> getAllModule() {
		// TODO Auto-generated method stub
		String sqlStr = "SELECT " + ID + "," + MODULE_NAME + "," + ENABLE + ","
				+ PICTURE + "," + TARGET + " FROM " + TABLE_NAME;
		List<Module> moduleList = new ArrayList<Module>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		while (rs.next()) {
			Module module = new Module();
			module.setEnable(rs.getInt(ENABLE));
			module.setId(rs.getInt(ID));
			module.setModuleName(rs.getString(MODULE_NAME));
			module.setPicture(rs.getString(PICTURE));
			module.setTarget(rs.getString(TARGET));
			moduleList.add(module);
		}
		return moduleList;
	}

	@Override
	public int insertModule(Module module) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + MODULE_NAME + ","
				+ ENABLE + "," + PICTURE + "," + TARGET + ") VALUES(?,?,?,?)";
		Object[] obj = { module.getModuleName(), module.getEnable(),
				module.getPicture(), module.getTarget() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateModule(Module module) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + MODULE_NAME + "=?,"
				+ ENABLE + "=?," + PICTURE + "=?," + TARGET + "=? WHERE " + ID
				+ "=?";
		Object[] obj = { module.getModuleName(), module.getEnable(),
				module.getPicture(), module.getTarget(), module.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
