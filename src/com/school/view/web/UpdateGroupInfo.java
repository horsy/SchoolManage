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

public class UpdateGroupInfo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7286020812082403711L;
	@Autowired
	private ServiceDao serviceDao;
	private int id;
	private int typeId;
	private int parentId;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String info = "";
		GroupType pgt = new GroupType();
		pgt.setTypeCode(PublicSTH.NODE_TYPE_ROOT);
		GroupType gt = this.checkGroupType(pgt);
		String success = "修改成功";
		if (gt == null) {
			info = "参数错误，ID丢失";
		} else {
			success = gt.getName() + success;
			info = this.checkSubmit();
		}
		if (info == null || "".equals(info)) {
			// 验证通过
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.setName(name);
			groupInfo.setTypeId(typeId);
			groupInfo.setParentId(parentId);
			groupInfo.setId(id);
			serviceDao.updateGroupInfo(groupInfo);
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
					"parent.getGroupList('" + pgt.getTypeCode() + "','"
							+ parentId + "');");
			response.getWriter().append("parent.dialog_close('bgDiv_2');");
		} else {

		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();

		return null;
	}

	private GroupType checkGroupType(GroupType pgt) {
		GroupType gt = null;
		List<GroupType> groupTypeList = serviceDao.getAllGroupType();
		for (GroupType groupType : groupTypeList) {
			if (groupType.getId() == typeId) {
				gt = groupType;
				break;
			}
		}
		if (gt != null) {
			if (gt.getParentId() != PublicSTH.DEFAULT_ID) {
				for (GroupType groupType : groupTypeList) {
					if (groupType.getId() == gt.getParentId()) {
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
		return gt;
	}

	private String checkSubmit() {
		StringBuffer info = new StringBuffer();
		if (name == null || "".equals(name)) {
			info.append("名称不能为空\\n");
		} else if (name.length() > 16) {
			info.append("名称太长\\n");
		} else if (PublicSTH.hasSpecialChar(name, PublicSTH.SPECIAL_CHAR_REGEX)) {
			info.append("名称不能有特殊字符\\n");
		} else {
			List<GroupInfo> groupInfoList = serviceDao.getAllGroupInfo();
			for (GroupInfo groupInfo : groupInfoList) {
				if (groupInfo.getName().equals(name)) {
					info.append("名称已存在\\n");
					break;
				}
			}
		}
		return info.toString();
	}

}
