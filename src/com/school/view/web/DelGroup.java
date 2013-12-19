package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class DelGroup extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8987120674519535549L;
	@Autowired
	private ServiceDao serviceDao;
	private String id;
	private String schoolId;
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = "";
		String success = "删除成功\\n";
		if (id != null && id.length() > 0) {
			if (id.indexOf(";") > -1 || id.indexOf("'") > -1
					|| (!PublicSTH.isNumber(schoolId))) {
				info = "参数错误";
			} else {
				String[] idArray = id.split("_obj");
				List<Integer> idList = new ArrayList<Integer>();
				for (String i : idArray) {
					if (PublicSTH.isNumber(i)) {
						idList.add(Integer.parseInt(i));
					}
				}
				Integer[] idA = new Integer[idList.size()];
				for (int i = 0; i < idList.size(); i++) {
					idA[i] = idList.get(i);
				}
				serviceDao.deleteGroupInfoById(idA,Integer.parseInt(schoolId));
				info = success;
			}

		} else {
			info = "请选择要删除的内容\\n";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		if (info.equals(success)) {
			//response.getWriter().append("parent.getGradeList(1,'" + schoolId + "');");

		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

}
