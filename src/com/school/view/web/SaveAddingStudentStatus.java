package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.StudentStatus;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveAddingStudentStatus extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -330934247808776406L;
	@Autowired
	private ServiceDao serviceDao;
	private String statusName;

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "学生状态添加成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			StudentStatus status = new StudentStatus();
			status.setStatusName(statusName);
			serviceDao.addStudentStatus(status);
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
					"parent.systemSet('studentStatus');");
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
		if (statusName == null || statusName.length() == 0) {
			info.append("请输入学生状态名称\\n");
		} else if (PublicSTH.hasSpecialChar(statusName,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("学生状态名称不能有特殊字符\\n");
		}
		if (statusName != null || statusName.length() > 0) {
			List<StudentStatus> statusList = serviceDao.getAllStudentStatus();
			for (StudentStatus status : statusList) {
				if (status.getStatusName().equals(statusName)) {
					info.append("该学生状态已经存在\\n");
					break;
				}
			}
		}

		return info.toString();
	}

}
