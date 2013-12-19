package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.StudentStatusDao;
import com.school.domain.StudentStatus;

@Component
public class StudentStatusDaoImpl implements StudentStatusDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String STATUS_NAME = "status_name";
	private final String TABLE_NAME = "t_student_status";

	@Override
	public List<StudentStatus> getAllStudentStatus() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + STATUS_NAME + " FROM "
				+ TABLE_NAME + " order by " + ID;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<StudentStatus> studentStatusList = new ArrayList<StudentStatus>();
		while (rs.next()) {
			StudentStatus ss = new StudentStatus();
			ss.setId(rs.getInt(ID));
			ss.setStatusName(rs.getString(STATUS_NAME));
			studentStatusList.add(ss);
		}
		return studentStatusList;
	}

	@Override
	public int deleteStudentStatusById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int insertStudentStatus(StudentStatus status) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + STATUS_NAME
				+ ") VALUES(?)";
		Object[] obj = { status.getStatusName() };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
