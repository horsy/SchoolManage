package com.school.view.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.view.domain.ClassDomain;
import com.school.domain.Class;

public class UpdateClass extends ActionSupport implements
		ModelDriven<ClassDomain> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3363681992351004302L;
	private ClassDomain classDomain=new ClassDomain();
	@Autowired
	private ServiceDao serviceDao;
	private Class clas=new Class();

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "班级修改成功";
		if (info == null || "".equals(info)) {
			// 验证通过
//			clas.setSchoolId(Integer.parseInt(classDomain.getSchoolId()));
			clas.setId(Integer.parseInt(classDomain.getId()));
			serviceDao.updateClass(clas);
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
					"parent.showClassList('" + clas.getGradeId() + "');");
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
		this.checkGradeId(info);
		this.checkClassTeacher(info);
		this.checkClassName(info);
		return info.toString();
	}

	private void checkClassName(StringBuffer info) {
		String className = classDomain.getClassName().trim();
		if (className == null || "".equals(className)) {
			info.append("年级名不能为空\\n");
		} else if (className.length() > 16) {
			info.append("年级名太长\\n");
		} else if (PublicSTH.hasSpecialChar(className,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("年级名不能有特殊字符\\n");
		} else {
			clas.setClassName(className);
		}
	}

	private void checkClassTeacher(StringBuffer info) {
		String teacherId = classDomain.getTeacherId().trim();
		if (PublicSTH.isNumber(teacherId)) {
			clas.setTeacherId(Integer.parseInt(teacherId));
		} else {
			info.append("请选择班主任\\n");
		}
	}

	private void checkGradeId(StringBuffer info) {
		String gradeId = classDomain.getGradeId().trim();
		if (PublicSTH.isNumber(gradeId)) {
			clas.setGradeId(Integer.parseInt(gradeId));
		} else {
			info.append("请选择年级\\n");
		}
	}

	@Override
	public ClassDomain getModel() {
		// TODO Auto-generated method stub
		return classDomain;
	}

}
