package com.school.dao;

import java.util.List;

import com.school.domain.Nation;

public interface NationDao {

	public List<Nation> getAllNation();

	public int insertNation(Nation nation);

	public int deleteNationById(int id);
}
