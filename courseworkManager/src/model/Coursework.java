package model;

import java.time.LocalDate;

public class Coursework {

	private int courseNum;
	private String courseName;
	private String workType;
	private LocalDate dueDate;
	private String gradeNum;
	
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
	
	public String getGradeNum() {
		return this.gradeNum;
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
	
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.courseNum) + "," + this.courseName + "," + this.workType + "," + this.dueDate + "," + this.gradeNum;
	}

	public void fromString(String input) {
		if (!input.isEmpty()) {
			String[] split = input.split(",");
			this.courseNum = Integer.parseInt(split[0]);
			this.courseName = split[1];
			this.workType = split[2];
			this.dueDate = LocalDate.parse(split[3]);
			this.gradeNum = split[4];
		} else {
			System.out.println("No data on line");
		}
	}
}
