package com.school.view.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Log;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class LogList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9190544278246559845L;
	@Autowired
	private ServiceDao serviceDao;
	private int page;
	private int pageSize;
	private int pageTotal;
	private List<Log> logList = new ArrayList<Log>();
	private String conditionObj;
	private String conditionValue;
	private String startTime;
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public List<Log> getLogList() {
		return logList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		page = page > 0 ? page : 1;
		pageSize = pageSize > 0 ? pageSize : PublicSTH.PAGE_SIZE;
		logList = serviceDao.getLogList(page, pageSize, startTime, endTime,
				conditionObj, conditionValue);
		pageTotal = serviceDao.getLogPageTotal(pageSize, startTime, endTime,
				conditionObj, conditionValue);
		return SUCCESS;
	}

}
