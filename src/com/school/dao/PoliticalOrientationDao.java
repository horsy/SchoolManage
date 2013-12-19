package com.school.dao;

import java.util.List;

import com.school.domain.PoliticalOrientation;

public interface PoliticalOrientationDao {

	public List<PoliticalOrientation> getAllPoliticalOrientation();

	public int addPoliticalOrientation(PoliticalOrientation politicalOrientation);

	public int deletePoliticalOrientionById(int id);
}
