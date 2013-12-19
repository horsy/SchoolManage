package com.school.dao;

import java.util.List;

import com.school.domain.Log;

public interface LogDao {

	public int insertLog(Log log);

	public List<Log> getLogList(int page, int pageSize, String startTime,
			String endTime, String conditionObj, Object conditionValue);

	public int getLogPageTotal(int pageSize, String startTime, String endTime,
			String conditionObj, Object conditionValue);
}
