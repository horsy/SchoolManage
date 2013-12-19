package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class BloodGroup extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4721788846924195649L;
	@Autowired
	private ServiceDao serviceDao;
	List<com.school.domain.BloodGroup> bloodGroupList;

	public List<com.school.domain.BloodGroup> getBloodGroupList() {
		return bloodGroupList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		bloodGroupList = serviceDao.getAllBloodGroup();
		for (int i = 0, len = bloodGroupList.size(); i < len; i++) {
			if (bloodGroupList.get(i).getId() == PublicSTH.DEFAULT_ID) {
				bloodGroupList.remove(i);
				len--;
				i--;
			}
		}
		return SUCCESS;
	}

}
