package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.TeacherType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveAddingPoliticalOrientation extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -10201930522745188L;
	@Autowired
	private ServiceDao serviceDao;
	private String politicalOrientation;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "政治面貌添加成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			com.school.domain.PoliticalOrientation politicalOrientation = new com.school.domain.PoliticalOrientation();
			politicalOrientation
					.setPoliticalOrientation(this.politicalOrientation);
			serviceDao.addPoliticalOrientation(politicalOrientation);
			info = success;
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		if (info.equals(success)) {
			response.getWriter().append("parent.systemSet('politicalOrientation');");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	public void setPoliticalOrientation(String politicalOrientation) {
		this.politicalOrientation = politicalOrientation.trim();
	}

	private String checkSubmit() {
		StringBuffer info = new StringBuffer();
		if (politicalOrientation == null || politicalOrientation.length() == 0) {
			info.append("请输入政治面貌名称\\n");
		} else if (PublicSTH.hasSpecialChar(politicalOrientation,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("政治面貌名称不能有特殊字符\\n");
		}
		if (politicalOrientation != null || politicalOrientation.length() > 0) {
			List<com.school.domain.PoliticalOrientation> politicalOrientationList = serviceDao
					.getAllPoliticalOrientation();
			for (com.school.domain.PoliticalOrientation politicalOrientation : politicalOrientationList) {
				if (politicalOrientation.getPoliticalOrientation().equals(
						this.politicalOrientation)) {
					info.append("该政治面貌已经存在\\n");
					break;
				}
			}
		}

		return info.toString();
	}
}
