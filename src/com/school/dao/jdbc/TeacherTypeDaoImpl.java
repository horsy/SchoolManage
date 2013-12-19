package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.TeacherTypeDao;
import com.school.domain.TeacherType;

@Component
public class TeacherTypeDaoImpl implements TeacherTypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String TYPE_NAME = "type_name";
	private final String TABLE_NAME = "t_teacher_type";

	@Override
	public List<TeacherType> getAllTeacherType() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + TYPE_NAME + " from "
				+ TABLE_NAME + " order by " + ID;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<TeacherType> teacherTypeList = new ArrayList<TeacherType>();
		while (rs.next()) {
			TeacherType teacherType = new TeacherType();
			teacherType.setId(rs.getInt(ID));
			teacherType.setTypeName(rs.getString(TYPE_NAME));
			teacherTypeList.add(teacherType);
		}
		return teacherTypeList;
	}

	@Override
	public int insertTeacherType(TeacherType teacherType) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + TYPE_NAME
				+ ") VALUES (?)";
		Object[] obj = { teacherType.getTypeName() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int deleteTeacherTypeById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
