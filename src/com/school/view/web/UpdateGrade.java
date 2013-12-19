package com.school.view.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.school.domain.Grade;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.view.domain.GradeDomain;

public class UpdateGrade extends ActionSupport implements
		ModelDriven<GradeDomain> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4904500023371330243L;
	@Autowired
	private ServiceDao serviceDao;
	private GradeDomain gradeDomain=new GradeDomain();
	private Grade grade = new Grade();

	@Override
	public GradeDomain getModel() {
		// TODO Auto-generated method stub
		return gradeDomain;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "年级修改成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			grade.setGroupId(Integer.parseInt(gradeDomain.getGroupId()));
			grade.setId(Integer.parseInt(gradeDomain.getId()));
			serviceDao.updateGrade(grade);
			info = success;
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		if (info.equals(success)) {
			response.getWriter().append("parent.getGradeList('1','"+grade.getGroupId()+"');");
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
		this.checkGradeName(gradeDomain.getGradeName(), info);
		this.checkInSchoolYear(gradeDomain.getInSchoolYear(), info);
		this.checkLevelId(gradeDomain.getLevelId(), info);
		return info.toString();
	}

	private void checkLevelId(String levelId, StringBuffer info) {
		levelId = levelId.trim();
		if (PublicSTH.isNumber(levelId)) {
			grade.setLevelId(Integer.parseInt(levelId));
		} else {
			info.append("请选择级别\\n");
		}
	}

	private void checkInSchoolYear(String inSchoolYear, StringBuffer info) {
		inSchoolYear = inSchoolYear.trim();
		if (PublicSTH.isNumber(inSchoolYear)) {
			grade.setInSchoolYear(Integer.parseInt(inSchoolYear));
		} else {
			info.append("入学年份输入有误\\n");
		}
	}

	private void checkGradeName(String gradeName, StringBuffer info) {
		gradeName = gradeName.trim();
		if (gradeName == null || "".equals(gradeDomain.getGradeName())) {
			info.append("年级名不能为空\\n");
		} else if (gradeName.length() > 16) {
			info.append("年级名太长\\n");
		} else if (PublicSTH.hasSpecialChar(gradeName,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("年级名不能有特殊字符\\n");
		} else if (serviceDao.hasGradeNameForGrade(gradeName, Integer
				.parseInt(gradeDomain.getId()))) {
			info.append("年级名已存在\\n");
		} else {
			grade.setGradeName(gradeName);
		}
	}
}
