package com.school.dao;

import java.util.List;

import com.school.domain.Module;

public interface ModuleDao {

	public List<Module> getAllModule();

	public int updateModule(Module module);

	public int insertModule(Module module);

	public int delModuleByModuleId(int id);

}
