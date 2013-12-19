package com.school.view.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.School;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveSchoolInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9075134060178951358L;
	@Autowired
	private ServiceDao serviceDao;
	private String schoolName;

	// private String id;

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName.trim();
	}

	// public void setId(String id) {
	// this.id = id.trim();
	// }

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = "";
		String success = "信息修改成功";
		// if (PublicSTH.isNumber(id)) {
		if (schoolName != null && schoolName.length() > 3) {
			if (PublicSTH.hasSpecialChar(schoolName,
					PublicSTH.SPECIAL_CHAR_REGEX)) {
				info = "学校名称不能有特殊字符";
			} else {
				com.school.domain.SystemConfig systemConfig = serviceDao
						.getSystemCofnig();
				systemConfig.setSystemName(schoolName);
				serviceDao.setSystemConfig(systemConfig);
				info = success;
				//					
				// if (school != null) {
				// school.setSchoolName(schoolName);
				// serviceDao.updateSchool(school);
				// info = success;
				// } else {
				// info = "参数错误，ID丢失\\n";
				// }
			}
		} else {
			info = "请输入学校名称\\n";
		}
		// } else {
		// info = "参数错误，ID丢失\\n";
		// }

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

}
