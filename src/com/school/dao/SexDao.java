package com.school.dao;

import java.util.List;

import com.school.domain.Sex;

public interface SexDao {
	public List<Sex> getAllSex();

	public int insertSex(Sex sex);

	public int deleteSexById(int id);

	public int updateSex(Sex sex);
}
