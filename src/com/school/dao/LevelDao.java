package com.school.dao;

import java.util.List;

import com.school.domain.Level;

public interface LevelDao {

	public List<Level> getAllLevel();

	public Level getLevelById(int id);
	
	public int insertLevel(Level level);
	
	public int updateLevel(Level level);
	
	public int deleteLevelById(int id);
	
	public boolean hasLevel(String name);

}
