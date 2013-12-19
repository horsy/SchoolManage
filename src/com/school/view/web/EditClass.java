package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.domain.Class;
import com.school.domain.Grade;
import com.school.domain.Teacher;

public class EditClass extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -732653124907997506L;
	@Autowired
	private ServiceDao serviceDao;
	private String id;
	private Class clas;
	private List<Teacher> teacherList;
	private List<Grade> gradeList;

	public Class getClas() {
		return clas;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = "";
		if (PublicSTH.isNumber(id)) {
			clas = serviceDao.getClassById(Integer.parseInt(id));
			if (clas == null) {
				info = "年级不存在";
			}
		} else {
			info = "参数输入有误";
		}
		if (info.length() > 0) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append("<script type='text/javascript'>");
			response.getWriter().append("alert('" + info + "');");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
			response.getWriter().append("</script>");
			response.getWriter().flush();
			response.getWriter().close();
			return null;
		} else {
			teacherList = serviceDao.getAllTeacher();
			Grade gradeInfo=serviceDao.getGradeByGradeId(clas.getGradeId());
			gradeList=serviceDao.getGradeListByGroupId(gradeInfo.getGroupId(), 1, PublicSTH.ALL_ROW);
			return SUCCESS;
		}
	}

}
