package model;

import java.time.LocalDate;

public class Coursework {

	private int courseNum;
	private String courseName;
	private String workType;
	private LocalDate dueDate;
	
	public int getCourseNum() {
		return this.courseNum;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getWorkType() {
		return this.workType;
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public void setCourseNum(int num) {
		this.courseNum = num;
	}
	
	public void setCourseName(String name) {
		this.courseName = name;
	}
	
	public void setWorkType(String type) {
		this.workType = type;
			
	}
	
	public void setDueDate(LocalDate date) {
		this.dueDate = date;
	}
}
