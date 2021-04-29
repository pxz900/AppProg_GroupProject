package model;

import java.time.LocalDate;
import java.util.Objects;

/* This is the main class for the coursework loaded to and from files
 */
public class Coursework {

	private int courseNum;
	private String courseName;
	private String workType;
	private LocalDate dueDate;
	private String gradeNum;
	
	// getters
	
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
	
	// setters

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
	
	/* Function used to export course work to a file or other storage format
	 * Returns: a syntactically valid coursework string
	 */
	@Override
	public String toString() {
		// return String.valueOf(this.courseNum) + "," + this.courseName + "," + this.workType + "," + this.dueDate + "," + this.gradeNum;
		return String.valueOf(this.courseNum) + "," + this.courseName + "," + this.workType + "," + this.dueDate + "," + this.gradeNum;
	}

	/* Function to load course work from a string (usually found inside a file)
	 * Parameters:
	 *   input should be a syntactically valid courseWork item
	 */
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
	
	/* This function is overriden for equality comparisons
	 */
    @Override
    public boolean equals(Object o) {
 
        if (o == this) {
        	return true;
        }
        if (!(o instanceof Coursework)) {
            return false;
        }
        Coursework course = (Coursework) o;
        return courseNum == course.courseNum &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(workType, course.workType) &&
                Objects.equals(dueDate, course.dueDate) &&
        		Objects.equals(gradeNum, course.gradeNum);
    }

    /* As equals is overriden, hashcode is as well to support hash-based structs
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseNum, courseName, workType, dueDate, gradeNum);
    }
}
