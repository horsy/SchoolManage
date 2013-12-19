package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.GroupInfo;
import com.school.domain.GroupType;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class GroupList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6850875127002353767L;
	@Autowired
	private ServiceDao serviceDao;
	private String type;

	public void setType(String type) {
		this.type = type.trim();
	}
	List<GroupInfo> returnList=new ArrayList<GroupInfo>();

	public List<GroupInfo> getReturnList() {
		return returnList;
	}
	private GroupType groupType;

	public GroupType getGroupType() {
		return groupType;
	}
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if (type == null) {
			// 参数错误
		} else {
			List<GroupType> groupTypeList = serviceDao.getAllGroupType();
			List<GroupInfo>groupInfoList=serviceDao.getAllGroupInfo();
			if (type.equals(PublicSTH.NODE_TYPE_ROOT)) {
				for(GroupType groupType:groupTypeList){
					if(groupType.getParentId()==0){
						for(GroupInfo groupInfo:groupInfoList){
							if(groupInfo.getTypeId()==groupType.getId()){
								returnList.add(groupInfo);
							}
						}
						this.groupType=groupType;
						break;
					}
				}
			} else {
				for(GroupType groupType:groupTypeList){
					if(groupType.getTypeCode().equals(type)){
						for(GroupType gt:groupTypeList){
							if(gt.getParentId()==groupType.getId()){
								for(GroupInfo groupInfo:groupInfoList){
									if(groupInfo.getParentId()==id){
										returnList.add(groupInfo);
									}
								}
								this.groupType=gt;
								break;
							}
						}
						break;
					}
				}
			}
		}
		return SUCCESS;
	}

}
