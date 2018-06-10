package com.realscores.obj;

import java.util.Date;

public class RoundFilter {
  
	public RoundFilter(Integer courseId, Date startDate, Date endDate){
		this.courseId = courseId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	private Integer courseId;
	private Date startDate;
	private Date endDate;

	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
