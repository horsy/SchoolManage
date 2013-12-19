package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.GradeDao;
import com.school.domain.Grade;
import com.school.publicSomething.PublicSTH;

@Component
public class GradeDaoImpl implements GradeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String ID = "id";
	private final String GRADE_NAME = "grade_name";
	private final String IN_SCHOOL_YEAR = "in_school_year";
	private final String GROUP_ID = "group_id";
	private final String LEVEL_ID = "level_id";
	private final String TABLE_NAME = "t_grade";

	@Override
	public List<Grade> getGradeListByGroupId(int groupId, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + GRADE_NAME + ","
				+ IN_SCHOOL_YEAR + "," + LEVEL_ID + " FROM " + TABLE_NAME
				+ " WHERE " + GROUP_ID + "=? order by " + IN_SCHOOL_YEAR
				+ " DESC limit ?,?";
		Object[] obj = { groupId, (page - 1) * pageSize, pageSize };
		List<Grade> gradeList = new ArrayList<Grade>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			Grade grade = new Grade();
			grade.setGradeName(rs.getString(GRADE_NAME));
			grade.setId(rs.getInt(ID));
			grade.setInSchoolYear(rs.getInt(IN_SCHOOL_YEAR));
			grade.setLevelId(rs.getInt(LEVEL_ID));
			grade.setGroupId(groupId);
			gradeList.add(grade);
		}
		return gradeList;
	}

	@Override
	public int getGradeListPageTotal(int groupId, int pageSize) {
		// TODO Auto-generated method stub
		String sqlStr;
		if (groupId == PublicSTH.NO_ID) {
			sqlStr = "select count(*) from " + TABLE_NAME;
		} else {
			sqlStr = "select count(*) from " + TABLE_NAME + " WHERE "
					+ GROUP_ID + "=" + groupId;
		}
		int totalRow = jdbcTemplate.queryForInt(sqlStr);
		int pageTotal = totalRow / pageSize;
		pageTotal = totalRow % pageSize > 0 ? pageTotal + 1 : pageTotal;
		return pageTotal;
	}

	@Override
	public int insertGrade(Grade grade) {
		// TODO Auto-generated method stub
		String sqlStr = "insert into " + TABLE_NAME + " (" + GRADE_NAME + ","
				+ IN_SCHOOL_YEAR + "," + GROUP_ID + "," + LEVEL_ID
				+ ") VALUES	(?,?,?,?)";
		Object[] obj = { grade.getGradeName(), grade.getInSchoolYear(),
				grade.getGroupId(), grade.getLevelId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public List<Grade> getGradeListByGradeName(String gradeName) {
		// TODO Auto-generated method stub
		return this.getGradeListByPropertyAndValue(GRADE_NAME, gradeName);
	}

	private List<Grade> getGradeListByPropertyAndValue(String property,
			Object value) {
		List<Grade> gradeList = new ArrayList<Grade>();
		String sqlStr = "select " + ID + "," + GRADE_NAME + ","
				+ IN_SCHOOL_YEAR + "," + GROUP_ID + "," + LEVEL_ID + " FROM "
				+ TABLE_NAME + " WHERE " + property + "=?";
		Object[] obj = { value };
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			Grade grade = new Grade();
			grade.setGradeName(rs.getString(GRADE_NAME));
			grade.setId(rs.getInt(ID));
			grade.setInSchoolYear(rs.getInt(IN_SCHOOL_YEAR));
			grade.setLevelId(rs.getInt(LEVEL_ID));
			grade.setGroupId(rs.getInt(GROUP_ID));
			gradeList.add(grade);
		}
		return gradeList;
	}

	@Override
	public Grade getGradeByGradeId(int gradeId) {
		// TODO Auto-generated method stub
		List<Grade> gradeList = this
				.getGradeListByPropertyAndValue(ID, gradeId);

		return gradeList.size() > 0 ? gradeList.get(0) : null;
	}

	@Override
	public List<Grade> getAllGradeById(int groupId) {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + GRADE_NAME + ","
				+ IN_SCHOOL_YEAR + "," + LEVEL_ID + " FROM " + TABLE_NAME
				+ " WHERE " + GROUP_ID + "=? order by " + IN_SCHOOL_YEAR
				+ " DESC";
		Object[] obj = { groupId };
		List<Grade> gradeList = new ArrayList<Grade>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr, obj);
		while (rs.next()) {
			Grade grade = new Grade();
			grade.setGradeName(rs.getString(GRADE_NAME));
			grade.setId(rs.getInt(ID));
			grade.setInSchoolYear(rs.getInt(IN_SCHOOL_YEAR));
			grade.setLevelId(rs.getInt(LEVEL_ID));
			grade.setGroupId(groupId);
			gradeList.add(grade);
		}
		return gradeList;
	}

	@Override
	public int updateGrade(Grade grade) {
		// TODO Auto-generated method stub
		String sqlStr = "update " + TABLE_NAME + " SET " + GRADE_NAME + "=?,"
				+ IN_SCHOOL_YEAR + "=?," + GROUP_ID + "=?," + LEVEL_ID
				+ "=? WHERE " + ID + "=?";
		Object[] obj = { grade.getGradeName(), grade.getInSchoolYear(),
				grade.getGroupId(), grade.getLevelId(), grade.getId() };
		return jdbcTemplate.update(sqlStr, obj);
	}

	@Override
	public int deleteGradeById(int id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from " + TABLE_NAME + " WHERE " + ID + " =?";
		Object[] obj = { id };
		return jdbcTemplate.update(sqlStr, obj);
	}

}
