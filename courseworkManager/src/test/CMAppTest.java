package test;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.CMAppModel;
import model.Coursework;

public class CMAppTest {
	
	File testCourseListFile, testCoursesFinishedFile, tempTestCourseListFile, tempTestCoursesFinishedFile;
	int courseNum;
	String courseName;
	String workType;
	String gradeNum;
	LocalDate dueDate;
	Coursework outstandingCourse;
	Coursework finishedCourse;

	/* This is executed before every test case. It creates temporary files to avoid corruption and creates test case data
	 */
	@Before
	public void setUp() throws Exception {
		// gets the base test data files
		this.testCourseListFile = new File(getClass().getResource("testCourseList.txt").getFile());
		this.testCoursesFinishedFile = new File(getClass().getResource("testCoursesFinished.txt").getFile());
		
		// gets the temporary test data files
		this.tempTestCourseListFile = new File("temp_testCourseList.txt");
		this.tempTestCoursesFinishedFile = new File("temp_testCoursesFinished.txt");
		
		// copies the base data to the temp data
		Files.copy(this.testCourseListFile.toPath(), this.tempTestCourseListFile.toPath(), REPLACE_EXISTING);
		Files.copy(this.testCoursesFinishedFile.toPath(), this.tempTestCoursesFinishedFile.toPath(), REPLACE_EXISTING);
		
		// test case data
		courseName = "Test Name";
		courseNum = 5678;
		workType = "Test2 Type";
		dueDate = LocalDate.parse("2050-06-20");
		gradeNum = "97";
		
		// test case (unfinished)
		outstandingCourse = new Coursework();
		outstandingCourse.setCourseNum(courseNum);
		outstandingCourse.setCourseName(courseName);
		outstandingCourse.setWorkType(workType);
		outstandingCourse.setDueDate(dueDate);
		outstandingCourse.setGradeNum("_");
		
		// test case (finished)
		finishedCourse = new Coursework();
		finishedCourse.setCourseNum(courseNum);
		finishedCourse.setCourseName(courseName);
		finishedCourse.setWorkType(workType);
		finishedCourse.setDueDate(LocalDate.now());
		finishedCourse.setGradeNum(gradeNum);
	}

	/* This is executed after each test case. Simply deletes the temporary files.
	 */
	@After
	public void tearDown() throws Exception {
		// deletes the temporary files
		this.tempTestCourseListFile.delete();
		this.tempTestCoursesFinishedFile.delete();
	}
	
	/* Tests that the add function writes to the file correctly
	 */
	@Test
	public void testAdd() throws IOException {
		// adds work
		CMAppModel.addWorkBase(this.tempTestCourseListFile, courseNum, courseName, workType, dueDate);
		
		// checks that the outstanding courses are correct
		Hashtable<Integer, Coursework> outstandingCourses = CMAppModel.loadCourseworkFromFile(this.tempTestCourseListFile);
		assertEquals(outstandingCourse, outstandingCourses.get(courseNum));
	}
	
	/* Tests that the finish function has the correct code and that the outstanding and finished files are correct
	 */
	@Test
	public void testFinish() throws IOException {
		// adds example data
		CMAppModel.addWorkBase(this.tempTestCourseListFile, courseNum, courseName, workType, dueDate);

		// finishes work
		String finishResult = CMAppModel.finishWorkBase(this.tempTestCourseListFile, this.tempTestCoursesFinishedFile, this.courseNum, this.gradeNum);

		// checks the success message
		assertEquals("Your coursework was successfully completed", finishResult);
		
		// checks that the finished courses are correct
		Hashtable<Integer, Coursework> finishedCourses = CMAppModel.loadCourseworkFromFile(this.tempTestCoursesFinishedFile);
		assertEquals(finishedCourse, finishedCourses.get(courseNum));
		
		// checks that the outstanding courses are correct
		Hashtable<Integer, Coursework> outstandingCourses = CMAppModel.loadCourseworkFromFile(this.tempTestCourseListFile);
		assertEquals(null, outstandingCourses.get(courseNum));
	}
}