package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Nation;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class NationList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2104853234574627312L;
	@Autowired
	private ServiceDao serviceDao;
	List<Nation> nationList;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		nationList=serviceDao.getAllNation();
		for (int i = 0, len = nationList.size(); i < len; i++) {
			if (nationList.get(i).getId() == PublicSTH.DEFAULT_ID) {
				nationList.remove(i);
				len--;
				i--;
			}
		}
		return SUCCESS;
	}

	public List<Nation> getNationList() {
		return nationList;
	}

}
