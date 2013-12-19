package com.school.view.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.Grade;
import com.school.domain.GroupInfo;
import com.school.domain.Level;
import com.school.domain.School;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class GradeList extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1960682045912007975L;
	@Autowired
	private ServiceDao serviceDao;
	private List<Grades> gradeList = new ArrayList<Grades>();
	private int id;
	private int page;
	private int pageSize;
	private int pageTotal;

	public int getPageTotal() {
		return pageTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Grades> getGradeList() {
		return gradeList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		page = page > 0 ? page : 1;
		pageSize = pageSize > 0 ? pageSize : PublicSTH.PAGE_SIZE;
		List<Grade> gList = serviceDao.getGradeListByGroupId(id, page,
				pageSize);
		pageTotal = serviceDao.getGradePageTotal(id, pageSize);
		List<Level> levelList = serviceDao.getAllLevel();
//		List<School> schoolList = serviceDao.getAllSchool();
		GroupInfo groupInfo=serviceDao.getGroupInfoById(id);
		for (Grade g : gList) {
			Grades gs = new Grades();

			gs.setGrade(g);
//			for(School s:schoolList){
//				if(s.getId()==g.getSchoolId()){
//					gs.setSchool(s);
//					break;
//				}
//			}
			gs.setGroupInfo(groupInfo);
//			gs.setSchool(serviceDao.getSchoolById(g.getSchoolId()));
			for (Level level : levelList) {
				if (level.getId() == g.getLevelId()) {
					gs.setLevel(level);
					break;
				}
			}
			gradeList.add(gs);
		}
		return SUCCESS;
	}

	class Grades implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4746007683010178281L;
		private Grade grade;
//		private School school;
		private Level level;
		private GroupInfo groupInfo;

		public GroupInfo getGroupInfo() {
			return groupInfo;
		}

		public void setGroupInfo(GroupInfo groupInfo) {
			this.groupInfo = groupInfo;
		}

		public Level getLevel() {
			return level;
		}

		public void setLevel(Level level) {
			this.level = level;
		}

		public Grade getGrade() {
			return grade;
		}

		public void setGrade(Grade grade) {
			this.grade = grade;
		}

//		public School getSchool() {
//			return school;
//		}
//
//		public void setSchool(School school) {
//			this.school = school;
//		}

	}

}
