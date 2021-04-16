package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class THAppModel {
	
	public static void addWork(int courseNum, String courseName, String workType, LocalDate dueDate) throws IOException {
		
		//'Coursework' object 'cw' created
		Coursework cw = new Coursework();
		//'buf' created to write lines to the file
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("courseList.txt", true));
		PrintWriter pWriter = new PrintWriter(bWriter);
		//Sets up course work user would like to add
		cw.setCourseNum(courseNum);
		cw.setCourseName(courseName);
		cw.setWorkType(workType);
		cw.setDueDate(dueDate);
		
		String toFile = cw.toString();
		
		pWriter.println(toFile);
		
		pWriter.close();
		bWriter.close();
	}

}
