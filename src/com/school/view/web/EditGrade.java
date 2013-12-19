package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Grade;
import com.school.domain.Level;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class EditGrade extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4171462492231741748L;
	@Autowired
	private ServiceDao serviceDao;

	private Grade grade;

	private String id;
	
	private List<Level> levelList;

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Grade getGrade() {
		return grade;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		String info = "";
		if (PublicSTH.isNumber(id)) {
			grade = serviceDao.getGradeByGradeId(Integer.parseInt(id));
			if (grade == null) {
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
			levelList = serviceDao.getAllLevel();
			return INPUT;
		}
	}

}
