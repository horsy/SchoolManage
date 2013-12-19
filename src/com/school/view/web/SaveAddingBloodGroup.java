package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Nation;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.domain.BloodGroup;

public class SaveAddingBloodGroup extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5565195011106392885L;
	@Autowired
	private ServiceDao serviceDao;
	private String groupName;

	public void setGroupName(String groupName) {
		this.groupName = groupName.trim();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		String info = this.checkSubmit();
		String success = "血型添加成功";
		if (info == null || "".equals(info)) {
			// 验证通过
			BloodGroup bloodGroup=new BloodGroup();
			bloodGroup.setGroupName(groupName);
			serviceDao.addBloodGroup(bloodGroup);
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
					"parent.systemSet('bloodGroup');");
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
		if(groupName==null||groupName.length()==0){
			info.append("请输入血型名称\\n");
		}else if(PublicSTH.hasSpecialChar(groupName, PublicSTH.SPECIAL_CHAR_REGEX)){
			info.append("血型名称不能有特殊字符\\n");
		}
		if(groupName!=null||groupName.length()>0){
			List<BloodGroup>bloodGroupList=serviceDao.getAllBloodGroup();
			for(BloodGroup bloodGroup:bloodGroupList){
				if(bloodGroup.getGroupName().equals(groupName)){
					info.append("该血型已经存在\\n");
					break;
				}
			}
		}
		
		return info.toString();
	}

}
