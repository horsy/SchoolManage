package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.school.domain.Teacher;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.view.domain.TeacherDomain;

public class UpdateTeacher extends ActionSupport implements ModelDriven<TeacherDomain>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1072489205208647214L;
	private TeacherDomain teacherDomain=new TeacherDomain();
	@Autowired
	private ServiceDao serviceDao;
	private Teacher teacher = new Teacher();

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "教师信息修改成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			serviceDao.updateTeacher(teacher);
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
					"parent.teacherList_getTeacherList(1);");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	private String checkSubmit() {
		StringBuffer info = new StringBuffer();
		this.checkId(info);
		if(info.length()>0){
			
		}else{
			this.checkUserName(info);
			this.checkLoginName(info);
			teacher.setPassword(DigestUtils.md5Hex(PublicSTH.DEFAULT_PASSWORD
					+ PublicSTH.MD5_SALT_ADMIN));
			this.checkTypeId(info);
			this.checkTelephone(info);
//			teacher.setLastLoginIp("");
//			teacher.setLastLoginTime("");
			this.checkSexId(info);
		}
		
		return info.toString();
	}
	private void checkId(StringBuffer info){
		String id=teacherDomain.getId().trim();
		if(PublicSTH.isNumber(id)){
			teacher.setId(Integer.parseInt(id));
		}else{
			info.append("参数错误，ID号丢失\\n");
		}
	}
	private void checkSexId(StringBuffer info){
		String sexId=teacherDomain.getSexId();
		if(PublicSTH.isNumber(sexId)){
			teacher.setSexId(Integer.parseInt(sexId));
		}else{
			info.append("性别信息有误\\n");
		}
	}

	private void checkTelephone(StringBuffer info) {
		String telephone = teacherDomain.getTelephone().trim();
		teacher.setTelephone(telephone == null ? "" : telephone);
	}

	private void checkTypeId(StringBuffer info) {
		String typeId = teacherDomain.getTypeId().trim();
		if (PublicSTH.isNumber(typeId)) {
			teacher.setTypeId(Integer.parseInt(typeId));
		} else {
			info.append("教师职位信息有误\\n");
		}
	}

	private void checkLoginName(StringBuffer info) {
		List<Teacher> teacherList = serviceDao.getTeacherList(PublicSTH.NO_ID,
				PublicSTH.ALL_ROW);
		String loginName=teacherDomain.getLoginName().trim();
		boolean hasCorrectLoginName=false;
		for(Teacher teacher:teacherList){
			if(teacher.getId()==this.teacher.getId()&&loginName!=null&&loginName.equals(this.teacher.getLoginName())){
				hasCorrectLoginName=true;
				break;
			}
		}
		if(!hasCorrectLoginName){
			int i = 1;
			boolean hasLoginName = true;
			while (hasLoginName) {
				boolean has=false;
				for (Teacher teacher : teacherList) {
					if (teacher.getLoginName().equals(
							PublicSTH.DEFAULT_TEACHER_LOGIN_NAME + i)) {
						i++;
						has=true;
						break;
					}
				}
				if(!has){
					hasLoginName=false;
				}
			}
			teacher.setLoginName(PublicSTH.DEFAULT_TEACHER_LOGIN_NAME + i);
		}
	}

	private void checkUserName(StringBuffer info) {
		String userName = teacherDomain.getUserName().trim();
		if (userName == null || "".equals(userName)) {
			info.append("姓名不能为空\\n");
		} else if (userName.length() > 16) {
			info.append("姓名太长\\n");
		} else if (PublicSTH.hasSpecialChar(userName,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("姓名不能有特殊字符\\n");
		} else {
			teacher.setUserName(userName);
		}
	}

	@Override
	public TeacherDomain getModel() {
		// TODO Auto-generated method stub
		return teacherDomain;
	}

}
