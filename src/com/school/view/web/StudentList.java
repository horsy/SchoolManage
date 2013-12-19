package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Review;
import com.school.domain.Sex;
import com.school.domain.Student;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.domain.Class;

public class StudentList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5882021328069854822L;

	@Autowired
	private ServiceDao serviceDao;
	private int classID;
	private int page;
	private int pageSize;
	private List<Students> studentList=new ArrayList<Students>();
	private int pageTotal;
	private Class clas;

	public Class getClas() {
		return clas;
	}

	public int getPage() {
		return page;
	}

	public List<Students> getStudentList() {
		return studentList;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public int getClassID() {
		return classID;
	}
	private String errorInfo;
	

	public String getErrorInfo() {
		return errorInfo;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		page = page > 0 ? page : 1;
		pageSize = pageSize > 0 ? pageSize : PublicSTH.PAGE_SIZE;
		Class clas=serviceDao.getClassById(classID);
		if(clas==null){
			errorInfo="该班级不存在，请刷新列表";
			return "errorInfo";
		}
		List<Student> sList = serviceDao.getStudentListByClassId(classID, page,
				pageSize);
		List<Sex> sexList = serviceDao.getAllSex();
		List<Review> reviewList = serviceDao.getAllReview();
		for (Student s : sList) {
			Students sts = new Students();
			sts.setStudent(s);
			for (Sex sex : sexList) {
				if (sex.getId() == s.getSexId()) {
					sts.setSex(sex);
					break;
				}
			}
			for (Review review : reviewList) {
				if (review.getId() == s.getIsReviewPass()) {
					sts.setReview(review);
					break;
				}
			}
			studentList.add(sts);
		}
		this.clas = clas;
		pageTotal = serviceDao.getStudentPageTotalForClass(classID, pageSize);
		return SUCCESS;
	}

	private class Students {
		private Student student;
		private Sex sex;
		private Review review;

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
