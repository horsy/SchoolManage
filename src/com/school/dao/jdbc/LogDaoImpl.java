package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.LogDao;
import com.school.domain.Log;
import com.school.ip2address.IPSeeker;

@Component
public class LogDaoImpl implements LogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String USER_TYPE = "user_type";
	private final String USER_ID = "user_id";
	private final String DATE_TIME = "date_time";
	private final String IP = "ip";
	private final String CONTENT = "content";
	private final String USER_NAME = "user_name";
	private final String TABLE_NAME = "t_log";

	@Override
	public int insertLog(Log log) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + USER_TYPE + ","
				+ USER_ID + "," + DATE_TIME + "," + IP + "," + CONTENT + ","
				+ USER_NAME + ") VALUES(?,?,?,?,?,?)";
		Object[] obj = { log.getUserTypeId(), log.getUserId(),
				log.getDateTime(), log.getIp(), log.getContent(),
				log.getUserName() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	private boolean isConditionObjIsCorrect(String conditionObj) {
		boolean isCorrect = false;
		if (conditionObj == null) {

		} else if (conditionObj.equals(USER_NAME)) {
			isCorrect = true;
		} else if (conditionObj.equals(CONTENT)) {
			isCorrect = true;
		} else if (conditionObj.equals(IP)) {
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public List<Log> getLogList(int page, int pageSize, String startTime,
			String endTime, String conditionObj, Object conditionValue) {
		// TODO Auto-generated method stub

		StringBuffer sqlStr = new StringBuffer();
		boolean hasWhere = false;
		sqlStr.append("select " + USER_TYPE + "," + USER_ID + "," + DATE_TIME
				+ "," + IP + "," + CONTENT + "," + USER_NAME + " FROM "
				+ TABLE_NAME);
		if (startTime!=null&&startTime.length() > 0) {
			sqlStr.append(" where ");
			sqlStr.append(DATE_TIME + ">=" + startTime);
			hasWhere = true;
		}
		if (endTime!=null&&endTime.length() > 0) {
			if (!hasWhere) {
				sqlStr.append(" where ");
			}
			sqlStr.append(" " + DATE_TIME + "<= " + endTime);
		}
		if (conditionObj!=null&&this.isConditionObjIsCorrect(conditionObj)) {
			if (!hasWhere) {
				sqlStr.append(" where ");
			}
			sqlStr.append(conditionObj + " like '%" + conditionValue + "%'");
		}
		sqlStr.append(" order by " + DATE_TIME + " DESC limit ?,?");
		Object[] obj = { (page - 1) * pageSize, pageSize };
		List<Log> logList = new ArrayList<Log>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr.toString(), obj);
		IPSeeker seeker = IPSeeker.getInstance();
		while (rs.next()) {
			Log log = new Log();
			log.setContent(rs.getString(CONTENT));
			log.setDateTime(rs.getString(DATE_TIME));
			log.setIp(rs.getString(IP));
			log.setUserId(rs.getInt(USER_ID));
			log.setUserName(rs.getString(USER_NAME));
			log.setUserTypeId(rs.getInt(USER_TYPE));
			log.setIpAddress(seeker.getAddress(log.getIp()));
			logList.add(log);
		}
		return logList;
	}

	@Override
	public int getLogPageTotal(int pageSize, String startTime, String endTime,
			String conditionObj, Object conditionValue) {
		// TODO Auto-generated method stub

		StringBuffer sqlStr = new StringBuffer();
		boolean hasWhere = false;
		sqlStr.append("select count(*) FROM " + TABLE_NAME);
		if (startTime!=null&&startTime.length() > 0) {
			sqlStr.append(" where ");
			sqlStr.append(DATE_TIME + ">=" + startTime);
			hasWhere = true;
		}
		if (endTime!=null&&endTime.length() > 0) {
			if (!hasWhere) {
				sqlStr.append(" where ");
			}
			sqlStr.append(" " + DATE_TIME + "<= " + endTime);
		}
		if (conditionObj!=null&&this.isConditionObjIsCorrect(conditionObj)) {
			if (!hasWhere) {
				sqlStr.append(" where ");
			}
			sqlStr.append(conditionObj + " like '%" + conditionValue + "%'");
		}
		int totalRow = jdbcTemplate.queryForInt(sqlStr.toString());
		int pageTotal = totalRow / pageSize;
		pageTotal = totalRow % pageSize > 0 ? pageTotal + 1 : pageTotal;
		return pageTotal;
	}

}
