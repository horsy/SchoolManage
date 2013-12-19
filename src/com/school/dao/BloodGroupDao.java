package com.school.dao;

import java.util.List;

import com.school.domain.BloodGroup;

public interface BloodGroupDao {

	public int insertBloodGroup(BloodGroup bloodGroup);

	public int updateBloodGroup(BloodGroup bloodGroup);

	public List<BloodGroup> getBloodGroupList();

	public int deleteBloodGroupById(int bloodGroupID);
}
