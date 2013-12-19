package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class DelStudent extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6944211538404971343L;
	private String id;
	@Autowired
	private ServiceDao serviceDao;

	public void setId(String id) {
		this.id = id;
	}

	private String classId;

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = "";
		String success = "删除学生成功\\n";
		if (id != null && id.length() > 0) {
			if (id.indexOf(";") > -1 || id.indexOf("'") > -1) {
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
				serviceDao.delStudentByIdArray(idA);

				info = success;
			}

		} else {
			info = "请选择要删除的学生\\n";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");

		if (!"".equals(info)) {
			response.getWriter().append("alert('" + info + "');");
		}
		if (info.equals(success)) {
			response.getWriter().append(
					"parent.showStudentList('" + classId + "');");
			// response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

}
