package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Class;
import com.school.domain.Review;
import com.school.domain.Sex;
import com.school.domain.Student;
import com.school.service.ServiceDao;

public class SearcherStudent extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2351026668208681249L;
	private String name;
	private List<Students> studentList=new ArrayList<Students>();
	public List<Students> getStudentList() {
		return studentList;
	}

	@Autowired
	private ServiceDao serviceDao;
	private Class clas;
	public Class getClas() {
		return clas;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		List<Student> sList = serviceDao.getStudentListByName(name);
		List<Sex> sexList = serviceDao.getAllSex();
//		List<Review> reviewList = serviceDao.getAllReview();
		for (Student s : sList) {
			Students sts = new Students();
			sts.setStudent(s);
			for (Sex sex : sexList) {
				if (sex.getId() == s.getSexId()) {
					sts.setSex(sex);
					break;
				}
			}
//			
//			for (Review review : reviewList) {
//				if (review.getId() == s.getIsReviewPass()) {
//					sts.setReview(review);
//					break;
//				}
//			}
			studentList.add(sts);
		}
		return SUCCESS;
	}

	private class Students {
		private Student student;
		private Sex sex;
		private Review review;

		public Class getClas() {
			return clas;
		}

		public Review getReview() {
			return review;
		}

		public void setReview(Review review) {
			this.review = review;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Sex getSex() {
			return sex;
		}

		public void setSex(Sex sex) {
			this.sex = sex;
		}

	}
}
