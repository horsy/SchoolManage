package com.school.view.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.school.domain.Student;

import com.school.domain.UserLoginDetails;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.view.domain.StudentDomain;

/**
 *保存学生信息，如果id为空，则添加，如果id不为空，则修改
 * 
 * @author horsy
 * 
 */
public class SaveStudentInfo extends ActionSupport implements
		ModelDriven<StudentDomain> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6547726919863724963L;
	private StudentDomain student = new StudentDomain();
	private Student studentA = new Student();
	@Autowired
	private ServiceDao serviceDao;
	private boolean isAdd = true;
	private String photoName;

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.checkIsAdd();
		String info = this.checkSubmit();
		String success = "信息保存成功\\n";
		if (info == null || "".equals(info)) {
			// 验证通过
			// grade.setSchoolId(serviceDao.getLocalSchoolInfo().getId());
			// serviceDao.addGrade(grade);
			if (isAdd) {
				studentA.setPassword(DigestUtils
						.md5Hex(PublicSTH.DEFAULT_PASSWORD
								+ PublicSTH.MD5_SALT_ADMIN));
				serviceDao.addStudent(studentA);
			} else {
				serviceDao.updateStudent(studentA);
			}

			info = success;
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		if (info.equals(success)) {
			response.getWriter().append(
					"parent.showStudentList('" + studentA.getClassId() + "');");
			// response.getWriter().append("parent.dialog_close('bgDiv_2');");
			
			File photo=new File(ServletActionContext.getServletContext().getRealPath("/photo/")+"/"+photoName);
			if(photo.exists()){
				photo.delete();
			}
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	private void checkIsAdd() {
		if (student.getId() != null) {
			if (PublicSTH.isNumber(student.getId())) {
				if (serviceDao
						.getStudentById(Integer.parseInt(student.getId())) != null) {
					isAdd = false;
					studentA.setId(Integer.parseInt(student.getId()));
				}
			}
		}
	}

	private String checkSubmit() {
		StringBuffer info = new StringBuffer();
		if (isAdd) {
			this.checkStudentId_add(student.getStudentId(), info);
		} else {
			this.checkStudentId_update(student.getStudentId(), info);
		}

		this.checkUserName(student.getUserName(), info);
		this.checkBirthday(student.getBirthday(), info);
		this.checkIdentityCard(student.getIdentityCardId(), info);
		studentA.setTelephone(this.getObjectValue(student.getTelephone()));
		studentA.setEmail(this.getObjectValue(student.getEmail()));
		studentA.setQq(this.getObjectValue(student.getQq()));
		studentA.setDorm(this.getObjectValue(student.getDorm()));
		studentA.setHukou(this.getObjectValue(student.getHukou()));
		studentA
				.setEntranceDate(this.getObjectValue(student.getEntranceDate()));
		studentA.setLeaveSchoolDate(this.getObjectValue(student
				.getLeaveSchoolDate()));
		studentA.setGuardian(this.getObjectValue(student.getGuardian()));
		studentA.setGuardianTelephone(this.getObjectValue(student
				.getGuardianTelephone()));
		studentA.setHomeAddress(this.getObjectValue(student.getHomeAddress()));
		studentA
				.setTouchAddress(this.getObjectValue(student.getTouchAddress()));
		studentA
				.setSpecialSkill(this.getObjectValue(student.getSpecialSkill()));
		studentA.setResume("");
		studentA.setEnableLogin(PublicSTH.NOT_ENABLE_LOGIN);
		studentA.setIsReviewPass(PublicSTH.REVIEW_NOT);
		this.getLoginUserInfo();
		studentA.setLastLoginDate("");
		studentA.setLastLoginIp("");
		this.checkMULId(info);
		if(isAdd||(photoName!=null&&photoName.length()>0)){
			this.setPhoto();
		}else{
			studentA.setPhoto(student.getPhoto());
		}
		
		return info.toString();
	}
	
	private void setPhoto(){
		String realpath = "";
		if(photoName!=null&&photoName.length()>0){
			realpath=ServletActionContext.getServletContext().getRealPath("/photo/")+"/"+photoName;
			studentA.setPhoto(PublicSTH.byteToBase64(PublicSTH.imageToByte(realpath)));
		}else{
			studentA.setPhoto("");
		}
	}

	private int checkId(String obj, String name, StringBuffer info) {
		int id = 0;
		obj = obj.trim();
		if (obj == null) {
			info.append("请选择" + name + "\\n");
		} else if (PublicSTH.isNumber(obj)) {
			id = Integer.parseInt(obj);
		} else {
			info.append(name + "信息有误，非ID\\n");
		}
		return id;
	}

	private void checkClassId(String classId, StringBuffer info) {
		classId = classId.trim();
		if (classId == null || "".equals(classId)) {
			info.append("参数错误，请返回重新点击添加\\n");
		} else if (PublicSTH.isNumber(classId)) {
			studentA.setClassId(Integer.parseInt(classId));
		} else {
			info.append("参数错误，请返回重新点击添加\\n");
		}
	}

	private void checkMULId(StringBuffer info) {
		studentA.setSexId(this.checkId(student.getSexId(), "性别", info));
		studentA.setBloodGroupId(this.checkId(student.getBloodGroupId(), "血型",
				info));
		studentA.setNationId(this.checkId(student.getNationId(), "民族", info));
		studentA.setPoliticalOrientationId(this.checkId(student
				.getPoliticalOrientationId(), "政治面貌", info));
		studentA.setStatusId(this.checkId(student.getStatusId(), "状态", info));
		this.checkClassId(student.getClassId(), info);
	}

	private void getLoginUserInfo() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		UserLoginDetails user = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		studentA.setAddTime(sdf.format(new Date()));

		if (auth == null) {
			studentA.setAddUser(student.getUserName());
			HttpServletRequest request = ServletActionContext.getRequest();
			studentA.setAddIp(request.getRemoteAddr());
		} else {
			user = (UserLoginDetails) auth.getPrincipal();
			studentA.setAddUser(user.getUsername());
			studentA.setAddIp(user.getLastLoginIp());
		}
	}

	private String getObjectValue(String obj) {
		String returnStr = "";
		obj = obj.trim();
		if (obj == null) {

		} else {
			returnStr = obj;
		}

		return returnStr;
	}

	private void checkIdentityCard(String identityCard, StringBuffer info) {
		identityCard = identityCard.trim();
		if (identityCard != null) {
			int len = identityCard.length();
			if (len > 0) {
				if (len == 18) {
					if (PublicSTH.isCorrentIdentityCard(identityCard)) {
						studentA.setIdentityCardId(identityCard);
					} else {
						info.append("身份证号码输入有误");
					}
				} else {
					info.append("身份证号码输入有误");
				}
			} else {
				studentA.setIdentityCardId("");
			}
		} else {
			studentA.setIdentityCardId("");
		}
	}

	private void checkBirthday(String birthday, StringBuffer info) {
		birthday = birthday.trim();
		if (birthday == null || "".equals(birthday)) {
			info.append("出生日期不能为空\\n");
		} else if (birthday.length() > 10) {
			info.append("出生日期太长\\n");
		} else {
			studentA.setBirthday(birthday);
		}
	}

	private void checkUserName(String userName, StringBuffer info) {
		userName = userName.trim();
		if (userName == null || "".equals(userName)) {
			info.append("姓名不能为空\\n");
		} else if (userName.length() > 16) {
			info.append("姓名太长\\n");
		} else if (PublicSTH.hasSpecialChar(userName,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("姓名不能有特殊字符\\n");
		} else {
			studentA.setUserName(userName);
		}
	}

	private void checkStudentId_update(String studentId, StringBuffer info) {
		studentId = studentId.trim();
		if (studentId == null || "".equals(studentId)) {
			info.append("学号不能为空\\n");
		} else if (!PublicSTH.isNumber(studentId)) {
			info.append("学号只能由数字组成\\n");
		} else if (studentId.length() > 32) {
			info.append("学号超过了32位，请与班主任或管理员联系\\n");
		} else {
			Student student = serviceDao.getStudentByStudentId(studentId);
			if (student != null
					&& student.getId() != Integer
							.parseInt(this.student.getId())) {
				info.append("学号：" + studentId + "重复\\n");
			} else {
				studentA.setStudentId(studentId);
			}
		}
	}

	private void checkStudentId_add(String studentId, StringBuffer info) {
		studentId = studentId.trim();
		if (studentId == null || "".equals(studentId)) {
			info.append("学号不能为空\\n");
		} else if (!PublicSTH.isNumber(studentId)) {
			info.append("学号只能由数字组成\\n");
		} else if (studentId.length() > 32) {
			info.append("学号超过了32位，请与班主任或管理员联系\\n");
		} else {
			Student student = serviceDao.getStudentByStudentId(studentId);
			if (student != null && student.getAddTime() != null
					&& student.getAddTime().length() > 0) {
				info.append("学号：" + studentId + "的信息已经录入\\n");
			} else {
				studentA.setStudentId(studentId);
			}
		}
	}

	@Override
	public StudentDomain getModel() {
		// TODO Auto-generated method stub
		return student;
	}

}
