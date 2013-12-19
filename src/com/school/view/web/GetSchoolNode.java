package com.school.view.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Grade;
import com.school.domain.GroupInfo;
import com.school.domain.GroupType;
import com.school.domain.Student;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;
import com.school.domain.Class;

public class GetSchoolNode extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4448824543183305691L;
	@Autowired
	private ServiceDao serviceDao;
	private String id;
	private String groupId;

	public void setGroupId(String groupId) {
		this.groupId = PublicSTH.isNumber(groupId) ? groupId : "-1";
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer schoolNode = new StringBuffer();
		schoolNode.append("[");
		if (type != null) {
			if (type.equals(PublicSTH.NODE_TYPE_ROOT)) {
				//学院
				List<GroupType> groupTypeList = serviceDao.getAllGroupType();
				String groupTypeCode="";
				for(GroupType groupType:groupTypeList){
					if(groupType.getParentId()==0){
						groupTypeCode=groupType.getTypeCode();
						break;
					}
				}
				schoolNode.append(this.getGroupNode(0, groupTypeCode));
			} else {
				//系
				List<GroupType> groupTypeList = serviceDao.getAllGroupType();
				int groupTypeId=0;
				boolean isGroup=false;
				String groupTypeCode="";
				for(GroupType groupType:groupTypeList){
					if(groupType.getTypeCode().equals(type)){
						groupTypeId=groupType.getId();
						for(GroupType gt:groupTypeList){
							if(gt.getParentId()==groupTypeId){
								groupTypeCode=gt.getTypeCode();
								break;
							}
						}
						isGroup=true;
						break;
					}
				}
				if(isGroup){
					schoolNode.append(this.getGroupNode(Integer.parseInt(id), groupTypeCode));
				}else if (type.equals(PublicSTH.NODE_TYPE_GRADE)) {
					schoolNode.append(this.getClassNode());
				} else if (type.equals(PublicSTH.NODE_TYPE_CLASS)) {
					schoolNode.append(this.getStudentNode());
				}
			}
			// if (type.equals(PublicSTH.NODE_TYPE_SCHOOL)) {
			// schoolNode.append(this.getGradeNode());
			// } else if (type.equals(PublicSTH.NODE_TYPE_GRADE)) {
			// schoolNode.append(this.getClassNode());
			// } else if (type.equals(PublicSTH.NODE_TYPE_CLASS)) {
			// schoolNode.append(this.getStudentNode());
			// }
		}

		schoolNode.append("]");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append(schoolNode);
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	private String getGroupNode(int parentId, String groupTypeCode) {
		StringBuffer groupNode = new StringBuffer();
		List<GroupInfo> groupList = serviceDao.getGroupListByParentId(parentId);
		int size = groupList.size();
		if(size>0){
			for (GroupInfo groupInfo : groupList) {
				groupNode.append("{");
				groupNode.append("name:\"" + groupInfo.getName() + "\",");
				groupNode.append("id:\"" + groupInfo.getId() + "\",");
				groupNode.append("nodeType:\"" + groupTypeCode + "\",");
				groupNode.append("groupId:\"" + groupId + "\",");
				groupNode.append("isParent:true,");
				groupNode.append("},");
			}
			groupNode.deleteCharAt(groupNode.lastIndexOf(","));
		}else{
			groupNode.append(this.getGradeNode());
		}
		return groupNode.toString();
	}

	private String getStudentNode() {
		StringBuffer studentNode = new StringBuffer();
		List<Student> studentList = serviceDao.getStudentListByClassId(Integer
				.parseInt(id), 1, PublicSTH.ALL_ROW);
		int size = studentList.size();
		for (Student student : studentList) {
			studentNode.append("{");
			if (student.getUserName() == null
					|| student.getUserName().trim().length() == 0) {
				studentNode.append("name:\"" + student.getStudentId() + "\",");
			} else {
				studentNode.append("name:\"" + student.getUserName() + "\",");
			}
			studentNode.append("id:\"" + student.getId() + "\",");
			studentNode.append("nodeType:\"" + PublicSTH.NODE_TYPE_STUDENT
					+ "\",");
			studentNode.append("groupId:\"" + groupId + "\",");
			studentNode.append("isParent:false,");
			studentNode.append("},");
		}
		if (size > 0) {
			studentNode.deleteCharAt(studentNode.lastIndexOf(","));
		}
		return studentNode.toString();
	}

	private String getClassNode() {
		StringBuffer classNode = new StringBuffer();
		List<Class> classList = serviceDao.getClassListByGradeId(
				Integer.parseInt(id),  1,
				PublicSTH.ALL_ROW);
		int size = classList.size();
		for (Class clas : classList) {
			classNode.append("{");
			classNode.append("name:\"" + clas.getClassName() + "\",");
			classNode.append("id:\"" + clas.getId() + "\",");
			classNode.append("nodeType:\"" + PublicSTH.NODE_TYPE_CLASS + "\",");
			classNode.append("isParent:true,");
			classNode.append("groupId:\"" + groupId + "\"");
			classNode.append("},");
		}
		if (size > 0) {
			classNode.deleteCharAt(classNode.lastIndexOf(","));
		}
		return classNode.toString();
	}

	private String getGradeNode() {
		StringBuffer gradeNode = new StringBuffer();
		List<Grade> gradeList = serviceDao.getGradeListByGroupId(Integer
				.parseInt(id), 0, PublicSTH.ALL_ROW);
		int size = gradeList.size();
		for (Grade g : gradeList) {
			gradeNode.append("{");
			gradeNode.append("name:\"" + g.getGradeName() + "\",");
			gradeNode.append("id:\"" + g.getId() + "\",");
			gradeNode.append("nodeType:\"" + PublicSTH.NODE_TYPE_GRADE + "\",");
			gradeNode.append("isParent:true,");
			gradeNode.append("groupId:\"" + g.getGroupId() + "\"");
			gradeNode.append("},");
		}
		if (size > 0) {
			gradeNode.deleteCharAt(gradeNode.lastIndexOf(","));
		}
		return gradeNode.toString();
	}

}
