package com.school.view.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.school.domain.Student;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.view.domain.BeforeHandAddDomain;

public class SaveBeforeHandAdd extends ActionSupport implements
		ModelDriven<BeforeHandAddDomain> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6293720827059163240L;
	@Autowired
	private ServiceDao serviceDao;
	private BeforeHandAddDomain beforeHandAdd = new BeforeHandAddDomain();

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "预添加成功";
		String addReturn="";
		if (info == null || "".equals(info)) {
			// 验证通过
			addReturn=this.addStudent();
			info = success;
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");
		
		if(!"".equals(addReturn)){
			response.getWriter().append("alert('" + addReturn + "');");
		}
		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		
		if (info.equals(success)) {
			response.getWriter().append(
					"parent.showStudentList('" + beforeHandAdd.getClassId()
							+ "');");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	private String addStudent() {
		StringBuffer addReturn=new StringBuffer();
		Long studentIdStart = Long.parseLong(beforeHandAdd.getStudentIdStart());
		Long studentIdEnd = Long.parseLong(beforeHandAdd.getStudentIdEnd());
		for (Long i = studentIdStart; i < studentIdEnd + 1; i++) {
			if(serviceDao.getStudentByStudentId(String.valueOf(i))!=null){
				addReturn.append("学号："+i+"已经存在\\n");
				continue;
			}
			Student student = new Student();
			student.setStudentId(String.valueOf(i));
			student.setUserName("");
			student.setPassword(DigestUtils.md5Hex(beforeHandAdd.getPassword().trim()+PublicSTH.MD5_SALT_ADMIN));
			student.setClassId(Integer.parseInt(beforeHandAdd.getClassId()));
			student.setBirthday("");
			student.setSexId(PublicSTH.DEFAULT_ID);
			student.setIdentityCardId("");
			student.setNationId(PublicSTH.DEFAULT_ID);
			student.setHukou("");
			student.setTelephone("");
			student.setEmail("");
			student.setQq("");
			student.setDorm("");
			student.setBloodGroupId(PublicSTH.DEFAULT_ID);
			student.setStatusId(PublicSTH.DEFAULT_ID);
			student.setEntranceDate("");
			student.setLeaveSchoolDate("");
			student.setPoliticalOrientationId(PublicSTH.DEFAULT_ID);
			student.setGuardian("");
			student.setGuardianTelephone("");
			student.setHomeAddress("");
			student.setTouchAddress("");
			student.setSpecialSkill("");
			student.setResume("");
			student.setEnableLogin(PublicSTH.NOT_ENABLE_LOGIN);
			student.setIsReviewPass(PublicSTH.REVIEW_NOT_ADD);
			student.setAddUser("");
			student.setAddTime("");
			student.setAddIp("");
			student.setLastLoginDate("");
			student.setLastLoginIp("");
			serviceDao.addStudent(student);
		}
		return addReturn.toString();
	}

	private String checkSubmit() {
		StringBuffer info = new StringBuffer();
		this.checkStudentId(info);
		return info.toString();
	}

	private void checkStudentId(StringBuffer info) {
		beforeHandAdd.setStudentIdStart(beforeHandAdd.getStudentIdStart()
				.trim());
		beforeHandAdd.setStudentIdEnd(beforeHandAdd.getStudentIdEnd().trim());
		if (beforeHandAdd.getStudentIdStart() == null
				|| "".equals(beforeHandAdd.getStudentIdStart())) {
			info.append("起始学号不能为空\\n");
		} else if (!PublicSTH.isNumber(beforeHandAdd.getStudentIdStart())) {
			info.append("起始学号只能由数字组成\\n");
		} else if (beforeHandAdd.getStudentIdEnd() == null
				|| "".equals(beforeHandAdd.getStudentIdEnd())) {
			info.append("结束学号不能为空\\n");
		} else if (!PublicSTH.isNumber(beforeHandAdd.getStudentIdEnd())) {
			info.append("结束学号只能由数字组成\\n");
		} else if (Long.parseLong(beforeHandAdd.getStudentIdStart()) > Long
				.parseLong(beforeHandAdd.getStudentIdEnd())) {
			info.append("起始学号应不大于结束学号");
		}
	}

	@Override
	public BeforeHandAddDomain getModel() {
		// TODO Auto-generated method stub
		return beforeHandAdd;
	}

}
