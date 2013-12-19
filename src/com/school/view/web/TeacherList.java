package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Sex;
import com.school.domain.Teacher;
import com.school.domain.TeacherType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class TeacherList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3001332958984714208L;
	@Autowired
	private ServiceDao serviceDao;
	private List<TeacherS> teacherList=new ArrayList<TeacherS>();

	public List<TeacherS> getTeacherList() {
		return teacherList;
	}

	private int page;
	private int pageSize;
	private int pageTotal;

	public int getPage() {
		return page;
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

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		page = page > 0 ? page : 1;
		pageSize = pageSize > 0 ? pageSize : PublicSTH.PAGE_SIZE;
		List<Teacher>tList = serviceDao.getTeacherList(page, pageSize);
		List<Sex> sexList = serviceDao.getAllSex();
		List<TeacherType>teacherTypeList=serviceDao.getAllTeacherType();
		for(Teacher teacher:tList){
			if(teacher.getId()==0){
				continue;
			}
			TeacherS ts=new TeacherS();
			ts.setTeacher(teacher);
			for(Sex sex:sexList){
				if(sex.getId()==teacher.getSexId()){
					ts.setSex(sex);
					break;
				}
			}
			for(TeacherType teacherType:teacherTypeList){
				if(teacherType.getId()==teacher.getTypeId()){
					ts.setTeacherType(teacherType);
					break;
				}
			}
			if(teacher.getEnableLogin()==1){
				ts.setLoginInfo("允许");
			}else{
				ts.setLoginInfo("不允许");
			}
			teacherList.add(ts);
		}
		pageTotal = serviceDao.getTeacherPageTotal(pageSize);
		return SUCCESS;
	}

	private class TeacherS {
		private Teacher teacher;
		private Sex sex;
		private String loginInfo;
		private TeacherType teacherType;

		public TeacherType getTeacherType() {
			return teacherType;
		}

		public void setTeacherType(TeacherType teacherType) {
			this.teacherType = teacherType;
		}

		public String getLoginInfo() {
			return loginInfo;
		}

		public void setLoginInfo(String loginInfo) {
			this.loginInfo = loginInfo;
		}

		public Teacher getTeacher() {
			return teacher;
		}

		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}

		public Sex getSex() {
			return sex;
		}

		public void setSex(Sex sex) {
			this.sex = sex;
		}
	}
}
