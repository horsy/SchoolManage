package com.school.view.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Student;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveReview extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1735815437709109036L;
	private String id;
	private String reviewValue;
	@Autowired
	private ServiceDao serviceDao;

	public void setId(String id) {
		this.id = id;
	}

	public void setReviewValue(String reviewValue) {
		this.reviewValue = reviewValue;
	}

	private Student student;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "设置审核成功";
		if (info == null || "".equals(info)) {
			serviceDao.setReviewByIdAndReviewValue(Integer.parseInt(id),
					Integer.parseInt(reviewValue));
			info=success;
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		if (info.equals(success)) {
			response.getWriter().append(
					"parent.showStudentList('" + student.getClassId() + "');");
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
		id = id.trim();
		if (PublicSTH.isNumber(id)) {
			student = serviceDao.getStudentById(Integer.parseInt(id));
			if (student == null) {
				info.append("该学生不存在\\n");
			} else if (PublicSTH.isNumber(reviewValue)) {
				student.setIsReviewPass(Integer.parseInt(reviewValue));
			} else {
				info.append("参数错误，审核标识丢失\\n");
			}
		} else {
			info.append("参数错误，ID号丢失,请取消重新审核\\n");
		}

		return info.toString();
	}

}
