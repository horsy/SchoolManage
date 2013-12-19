package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Nation;
import com.school.domain.TeacherType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveAddingNation extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4298096418645843535L;
	@Autowired
	private ServiceDao serviceDao;
	private String nation;

	public void setNation(String nation) {
		this.nation = nation.trim();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = this.checkSubmit();
		String success = "民族添加成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			Nation nation=new Nation();
			nation.setNation(this.nation);
			serviceDao.addNation(nation);
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
					"parent.systemSet('nationList');");
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
		if(nation==null||nation.length()==0){
			info.append("请输入民族名称\\n");
		}else if(PublicSTH.hasSpecialChar(nation, PublicSTH.SPECIAL_CHAR_REGEX)){
			info.append("民族名称不能有特殊字符\\n");
		}
		if(nation!=null||nation.length()>0){
			List<Nation>nationList=serviceDao.getAllNation();
			for(Nation nation:nationList){
				if(nation.getNation().equals(this.nation)){
					info.append("该民族已经存在\\n");
					break;
				}
			}
		}
		
		return info.toString();
	}

}
