package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.domain.Class;
import com.school.domain.Grade;
import com.school.domain.School;
import com.school.domain.Teacher;

public class ClassList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4493752176153669239L;
	@Autowired
	private ServiceDao serviceDao;
	private int id;
	private int page;
	private int pageSize;
	private int pageTotal;

	private List<Classes> classesList = new ArrayList<Classes>();

	public int getPageTotal() {
		return pageTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Classes> getClassesList() {
		return classesList;
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
		Grade grade = serviceDao.getGradeByGradeId(id);
		if(grade==null){
			errorInfo="该年级已删除，请刷新列表";
			return "errorInfo";
		}else{
//			School school = serviceDao.getSchoolById(grade.getSchoolId());
			List<Teacher> teacherList = serviceDao.getAllTeacher();
			List<Class> classList = serviceDao.getClassListByGradeId(id,
					 page, pageSize);
			for (Class c : classList) {
				Classes classes = new Classes();
				classes.setClas(c);
				classes.setGrade(grade);
//				classes.setSchool(school);
				for (Teacher teacher : teacherList) {
					if (teacher.getId() == c.getTeacherId()) {
						classes.setTeacher(teacher);
						break;
					}
				}

				classesList.add(classes);
			}
			pageTotal=serviceDao.getClassPageTotalByGradeIdAndPageSize(id, pageSize);
			return SUCCESS;
		}
	}

	private class Classes {
		private Class clas;
		private Teacher teacher;
		private Grade grade;
		private School school;

		public Class getClas() {
			return clas;
		}

		public void setClas(Class clas) {
			this.clas = clas;
		}

		public Teacher getTeacher() {
			return teacher;
		}

		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}

		public Grade getGrade() {
			return grade;
		}

		public void setGrade(Grade grade) {
			this.grade = grade;
		}

		public School getSchool() {
			return school;
		}

		public void setSchool(School school) {
			this.school = school;
		}
	}

}
