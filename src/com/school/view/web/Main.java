package com.school.view.web;

import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.BloodGroup;
import com.school.domain.Nation;
import com.school.domain.PoliticalOrientation;
import com.school.domain.School;
import com.school.domain.Sex;
import com.school.domain.Student;
import com.school.domain.StudentStatus;
import com.school.domain.UserLoginDetails;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.view.domain.MainDomain;

public class Main extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6572310583016563170L;
	private MainDomain mainDomain = new MainDomain();
	@Autowired
	private ServiceDao serviceDao;
	
	private Student student;
	private List<Sex> sexList;
	private List<BloodGroup> bloodGroupList;
	private List<Nation> nationList;
	private List<PoliticalOrientation> politicalOrientationList;
	private List<StudentStatus> studentStatusList;
	private int classId;
	private String id;
	private String errorInfo;

	public String getErrorInfo() {
		return errorInfo;
	}

	public Student getStudent() {
		return student;
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

	public int getClassId() {
		return classId;
	}

	public String getId() {
		return id;
	}

	public MainDomain getMainDomain() {
		return mainDomain;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		UserLoginDetails user = (UserLoginDetails) auth.getPrincipal();
		if(user.getNextPage().equals(PublicSTH.PAGE_LOGINED[1])){
			mainDomain.setLoginUser(user.getUsername());
//			School school = serviceDao.getLocalSchoolInfo();
			com.school.domain.SystemConfig systemConfig=serviceDao.getSystemCofnig();
			if (systemConfig.getSystemName() == null || "".equals(systemConfig.getSystemName())) {
				mainDomain.setTitleName(PublicSTH.TITLE);
//				school.setSchoolName(PublicSTH.TITLE);
			} else {
				//mainDomain.setTitleName(school.getSchoolName()+PublicSTH.TITLE);
				mainDomain.setTitleName(PublicSTH.TITLE);
			}
			//mainDomain.setLocalSchool(school);
			mainDomain.setSystemName(systemConfig.getSystemName());
			mainDomain.setModuleList(serviceDao.getEnableModule());
		}else if(user.getNextPage().equals(PublicSTH.PAGE_LOGINED[0])){
			//学生录入信息
			student=serviceDao.getStudentByStudentId(user.getUsername());
			sexList = serviceDao.getAllSex();
			bloodGroupList = serviceDao.getAllBloodGroup();
			nationList = serviceDao.getAllNation();
			politicalOrientationList = serviceDao.getAllPoliticalOrientation();
			studentStatusList = serviceDao.getAllStudentStatus();
		}else if(user.getNextPage().equals(PublicSTH.PAGE_LOGINED[2])){
			errorInfo=user.getErrorInfo();
		}
		return user.getNextPage();
	}

}
