package com.school.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.ReviewDao;
import com.school.domain.Review;

@Component
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String ID = "id";
	private String CONTENT = "content";
	private String TABLE_NAME = "t_review";

	@Override
	public List<Review> getAllReview() {
		// TODO Auto-generated method stub
		String sqlStr = "select " + ID + "," + CONTENT + " FROM " + TABLE_NAME;
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
		List<Review> reviewList = new ArrayList<Review>();
		while (rs.next()) {
			Review review = new Review();
			review.setContent(rs.getString(CONTENT));
			review.setId(rs.getInt(ID));
			reviewList.add(review);
		}
		return reviewList;
	}

}
