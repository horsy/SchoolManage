package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.TeacherType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveAddingTeacherLevel extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7157540707254919720L;
	@Autowired
	private ServiceDao serviceDao;
	private String typeName;

	public void setTypeName(String typeName) {
		this.typeName = typeName.trim();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "职位添加成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			TeacherType teacherType=new TeacherType();
			teacherType.setTypeName(typeName);
			serviceDao.addTeacherType(teacherType);
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
					"parent.systemSet('teacherLevel');");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	private String checkSubmit(){
		StringBuffer info=new StringBuffer();
		if(typeName==null||typeName.length()==0){
			info.append("请输入职位名称\\n");
		}else if(PublicSTH.hasSpecialChar(typeName, PublicSTH.SPECIAL_CHAR_REGEX)){
			info.append("职位名称不能有特殊字符\\n");
		}
		if(typeName!=null||typeName.length()>0){
			List<TeacherType>teacherTypeList=serviceDao.getAllTeacherType();
			for(TeacherType teacherType:teacherTypeList){
				if(teacherType.getTypeName().equals(typeName)){
					info.append("该职位已经存在\\n");
					break;
				}
			}
		}
		
		return info.toString();
	}

}
