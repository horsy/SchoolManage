package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.BloodGroup;
import com.school.domain.Nation;
import com.school.domain.PoliticalOrientation;
import com.school.domain.Sex;
import com.school.domain.Student;
import com.school.domain.StudentStatus;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class StudentInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8828126696648990178L;
	@Autowired
	private ServiceDao serviceDao;
	private List<Sex> sexList;
	private List<BloodGroup> bloodGroupList;
	private List<Nation> nationList;
	private List<PoliticalOrientation> politicalOrientationList;
	private List<StudentStatus> studentStatusList;
	private int classId;
	private String id;
	private Student student;

	public Student getStudent() {
		return student;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public List<Sex> getSexList() {
		return sexList;
	}

	public List<BloodGroup> getBloodGroupList() {
		return bloodGroupList;
	}

	public List<Nation> getNationList() {
		return nationList;
	}

	public List<PoliticalOrientation> getPoliticalOrientationList() {
		return politicalOrientationList;
	}

	public List<StudentStatus> getStudentStatusList() {
		return studentStatusList;
	}

	private String errorInfo;

	public String getErrorInfo() {
		return errorInfo;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean isAddStudent = false;
		if (PublicSTH.isNumber(id)) {
			student = serviceDao.getStudentById(Integer.parseInt(id));
			if (student == null) {
				errorInfo="该学生不存在，请刷新列表";
				return "errorInfo";
			}
		} else {
			isAddStudent = true;
		}

		sexList = serviceDao.getAllSex();
		bloodGroupList = serviceDao.getAllBloodGroup();
		nationList = serviceDao.getAllNation();
		politicalOrientationList = serviceDao.getAllPoliticalOrientation();
		studentStatusList = serviceDao.getAllStudentStatus();
		if (isAddStudent) {
			return "add";
		} else {
			return "edit";
		}
	}

}
