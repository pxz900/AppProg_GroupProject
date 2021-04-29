package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.THAppModel;

public class THAppTest {

	int courseNum;
	String courseName;
	String workType;
	String gradeNum;
	LocalDate dueDate;	

	@Before
	public void setUp() {
		courseName = "Test2 Name";
		courseNum = 5678;
		workType = "Test2 Type";
		dueDate = LocalDate.parse("2050-06-20");
		gradeNum = "97";
	}
	
	@Test
	public void testAdd() throws IOException {
		THAppModel.addWork(courseNum, courseName, workType, dueDate);
	}
	
	@Test
	public void testFinish() throws IOException {
		THAppModel.finishWork(courseNum, workType, gradeNum);
	}
}
