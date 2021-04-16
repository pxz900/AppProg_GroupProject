package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Test;

import model.THAppModel;

public class THAppTest {
	
	THAppModel model;
	String courseName = "Test2 Name";
	int courseNum = 5678;
	String workType = "Test2 Type";
	LocalDate dueDate = LocalDate.parse("2050-06-20");
	
	@Test
	public void testAdd() throws IOException {
		THAppModel.addWork(courseNum, courseName, workType, dueDate);

	}

}
