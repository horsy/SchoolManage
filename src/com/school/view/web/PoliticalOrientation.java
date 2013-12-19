package com.school.view.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class PoliticalOrientation extends ActionSupport {
	
	@Autowired
	private ServiceDao serviceDao;
	private List<com.school.domain.PoliticalOrientation>politicalOrientation;
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		politicalOrientation=serviceDao.getAllPoliticalOrientation();
		for (int i = 0, len = politicalOrientation.size(); i < len; i++) {
			if (politicalOrientation.get(i).getId() == PublicSTH.DEFAULT_ID) {
				politicalOrientation.remove(i);
				len--;
				i--;
			}
		}
		return SUCCESS;
	}

	public List<com.school.domain.PoliticalOrientation> getPoliticalOrientation() {
		return politicalOrientation;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1887176131355714481L;

}
