package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.ClassDao;
import com.school.domain.Class;
import com.school.publicSomething.PublicSTH;

@Component
public class ClassDaoImpl implements ClassDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String ID = "id";
	private final String CLASS_NAME = "class_name";
	private final String GRADE_ID = "grade_id";
//	private final String SCHOOL_ID = "school_id";
	private final String TABLE_NAME = "t_class";
	private final String CLASS_TEACHER_ID = "class_teacher_id";

	@Override
	public int deleteClassById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " where " + ID + "=?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Class> getAllClass() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + CLASS_NAME + "," + GRADE_ID
				+ "," + CLASS_TEACHER_ID + " from "
				+ TABLE_NAME;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<Class> classList = new ArrayList<Class>();
		while (rs.next()) {
			Class c = new Class();
			c.setClassName(rs.getString(CLASS_NAME));
			c.setId(rs.getInt(ID));
			c.setGradeId(rs.getInt(GRADE_ID));
			c.setTeacherId(rs.getInt(CLASS_TEACHER_ID));
			classList.add(c);
		}
		return classList;
	}

	private List<Class> getClassListByPropertyAndValue(String property,
			Object value) {
		List<Class> classList = new ArrayList<Class>();
		String sqlStr = "select " + ID + "," + CLASS_NAME + "," + GRADE_ID
				+ "," + CLASS_TEACHER_ID + " from "
				+ TABLE_NAME + " where " + property + "=? ";
		Object[] obj = { value };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			Class c = new Class();
			c.setClassName(rs.getString(CLASS_NAME));
			c.setId(rs.getInt(ID));
			c.setGradeId(rs.getInt(GRADE_ID));
			c.setTeacherId(rs.getInt(CLASS_TEACHER_ID));
			classList.add(c);
		}

		return classList;
	}

	@Override
	public Class getClassById(int id) {
		// TODO Auto-generated method stub
		List<Class> classList = this.getClassListByPropertyAndValue(ID, id);
		return classList.size() > 0 ? classList.get(0) : null;
	}

	@Override
	public List<Class> getClassListByGradeId(int gradeId) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + CLASS_NAME + "," + GRADE_ID
				+ "," + CLASS_TEACHER_ID + " from "
				+ TABLE_NAME + " where " + GRADE_ID + "=?";
		Object[] obj = { gradeId };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		List<Class> classList = new ArrayList<Class>();
		while (rs.next()) {
			Class c = new Class();
			c.setClassName(rs.getString(CLASS_NAME));
			c.setId(rs.getInt(ID));
			c.setGradeId(rs.getInt(GRADE_ID));
			c.setTeacherId(rs.getInt(CLASS_TEACHER_ID));
			classList.add(c);
		}
		return classList;
	}

	@Override
	public int insertClass(Class c) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + CLASS_NAME + ","
				+ GRADE_ID + "," + CLASS_TEACHER_ID + ") values (?,?,?)";
		Object[] obj = { c.getClassName(), c.getGradeId(), c.getTeacherId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int updateClass(Class c) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " set " + CLASS_NAME + "= ?,"
				+ GRADE_ID + "=?," + CLASS_TEACHER_ID + "=? where " + ID + "=?";
		Object[] obj = { c.getClassName(), c.getGradeId(), c.getTeacherId(),
				c.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Class> getClassListByGradeId(int gradeId, int page, int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + CLASS_NAME + "," + GRADE_ID
				+ "," + CLASS_TEACHER_ID + " from "
				+ TABLE_NAME + " where " + GRADE_ID + "=? limit ?,?";
		Object[] obj = { gradeId, (page - 1) * pageSize, pageSize };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		List<Class> classList = new ArrayList<Class>();
		while (rs.next()) {
			Class c = new Class();
			c.setClassName(rs.getString(CLASS_NAME));
			c.setId(rs.getInt(ID));
			c.setGradeId(rs.getInt(GRADE_ID));
			c.setTeacherId(rs.getInt(CLASS_TEACHER_ID));
			classList.add(c);
		}
		return classList;
	}

	@Override
	public int getClassPageTotalForGrade(int gradeId, int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select count(*) from " + TABLE_NAME + " WHERE "
				+ GRADE_ID + "=?";
		Object[] obj = { gradeId };
		int totalRow = jdbcTemplate.queryForInt(sqlStr, obj);
		int pageTotal = totalRow / pageSize;
		pageTotal = totalRow % pageSize > 0 ? pageTotal + 1 : pageTotal;
		return pageTotal;
	}

	@Override
	public List<Class> getClassListByTeacherId(int teacherId) {
		// TODO Auto-generated method stub
		return this.getClassListByPropertyAndValue(CLASS_TEACHER_ID, teacherId);
	}

	@Override
	public int updateClassTeacherIdToDefault(Integer[] teacherId) {
		// TODO Auto-generated method stub
		StringBuffer id = new StringBuffer();
		for (int tId : teacherId) {
			id.append(String.valueOf(tId) + ",");
		}
		if (id != null && id.length() > 0) {
			id.deleteCharAt(id.lastIndexOf(","));
		}
		String sqlStr = "update " + TABLE_NAME + " SET " + CLASS_TEACHER_ID
				+ " = " + PublicSTH.DEFAULT_ID + " WHERE " + CLASS_TEACHER_ID
				+ " IN (" + id + ")";
		return jdbcTemplate.update(sqlStr);
	}

}
