package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.GroupInfo;
import com.school.domain.GroupType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveGroupInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8033746139011452204L;
	@Autowired
	private ServiceDao serviceDao;
	private String name;
	private String typeId;

	public void setName(String name) {
		this.name = name;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId.trim();
	}
	private int parentId;

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info ="";
		GroupType pgt=new GroupType();
		pgt.setTypeCode(PublicSTH.NODE_TYPE_ROOT);
		GroupType gt=this.checkGroupType(pgt);
		String success = "添加成功";
		if(gt==null){
			info="参数错误，ID丢失";
		}else{
			success=gt.getName()+success;
			info= this.checkSubmit();
		}
		if (info == null || "".equals(info)) {
			// 验证通过
			GroupInfo groupInfo=new GroupInfo();
			groupInfo.setName(name);
			groupInfo.setTypeId(Integer.parseInt(typeId));
			groupInfo.setParentId(parentId);
			serviceDao.addGroupInfo(groupInfo);
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
					"parent.getGroupList('" + pgt.getTypeCode() + "','"+parentId+"');");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();

		return null;
	}
	
	private GroupType checkGroupType(GroupType pgt){
		GroupType gt=null;
		if(PublicSTH.isNumber(typeId)){
			int id=Integer.parseInt(typeId);
			List<GroupType>groupTypeList=serviceDao.getAllGroupType();
			for(GroupType groupType:groupTypeList){
				if(groupType.getId()==id){
					gt=groupType;
					break;
				}
			}
			if(gt!=null){
				if(gt.getParentId()!=PublicSTH.DEFAULT_ID){
					for(GroupType groupType:groupTypeList){
						if(groupType.getId()==gt.getParentId()){
							pgt.setId(groupType.getId());
							pgt.setIsParent(groupType.getIsParent());
							pgt.setName(groupType.getName());
							pgt.setParentId(groupType.getParentId());
							pgt.setTypeCode(groupType.getTypeCode());
							break;
						}
					}
				}
				
			}
		}
		return gt;
	}
	private String checkSubmit(){
		StringBuffer info = new StringBuffer();
		if (name == null || "".equals(name)) {
			info.append("名称不能为空\\n");
		} else if (name.length() > 16) {
			info.append("名称太长\\n");
		} else if (PublicSTH.hasSpecialChar(name,
				PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("名称不能有特殊字符\\n");
		} else {
			List<GroupInfo> groupInfoList=serviceDao.getAllGroupInfo();
			for(GroupInfo groupInfo:groupInfoList){
				if(groupInfo.getName().equals(name)){
					info.append("名称已存在\\n");
					break;
				}
			}
		}
		return info.toString();
	}

}
