package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* This serves as the model which will execute all of the internal application logic
 */
public class CMAppModel {
    
    /* Function that adds work to be done to the database file
     * Parameters:
     * 	 input should be the path of the courseList file
     *   courseNum should be the ID of the course
     *   courseName should be the course's name
     *   workType should be what category the work belongs in (for example, Quiz)
     *   dueDate should be when the assignment should be completed by
     */
    public static String addWorkBase(File input, int courseNum, String courseName, String workType, LocalDate dueDate) throws IOException {
        // the course to be added
        Coursework cw = new Coursework();
  
        //Sets up course work user would like to add
        String gradeNum = "_";
        cw.setCourseNum(courseNum);
        cw.setCourseName(courseName);
        cw.setWorkType(workType);
        cw.setDueDate(dueDate);
        cw.setGradeNum(gradeNum);

        // appends data
        appendCourseworkToFile(input, cw);
        
        return "Successfully added course work to inventory!";
    }
    
    /* Helper function that adds work and assumes the course file path
     */
    public static String addWork(int courseNum, String courseName, String workType, LocalDate dueDate) throws IOException { 
        // finished and outstanding file paths
        File input = new File("courseList.txt");
        
        // ensures the file is created
        if (!input.exists()) {
        	input.createNewFile();
        }

        return addWorkBase(input, courseNum, courseName, workType, dueDate);
    }

    /* Function that finishes work in the database file
     * Parameters:
     * 	 input should be the path of the courseList file
     * 	 output should be the path of the coursesFinished file
     *   courseNum should be the ID of the course
     *   workType should be what category the work belongs in (for example, Quiz)
     *   gradeNum should be how well you scored on this assignment (can vary by grading system)
     */
    public static String finishWorkBase(File input, File output, int courseNum, String gradeNum) throws IOException { 
        // loads the outstanding and finished work files
        Hashtable<Integer, Coursework> outstandingCourses = loadCourseworkFromFile(input);
        Hashtable<Integer, Coursework> finishedCourses = loadCourseworkFromFile(output);
        
        // tries to get the course from the unfinished list
        Coursework course;
        if (outstandingCourses.containsKey(courseNum)) {
            course = outstandingCourses.get(courseNum);        	
        }
        // if it was not found
        else {
        	// if it is in the finished courses file
        	if (finishedCourses.containsKey(courseNum)) {
        		return "Your course work has already been completed!";
        	}
        	// else it does simply not exist
        	else {
        		return "Your course work cannot be found";
        	}
        }
        
        // checks there are not duplicates
        if (finishedCourses.containsKey(courseNum)) {
        	finishedCourses.remove(courseNum);
        }
        
        // updates the grade and date and removes it from the outstanding courses
        course.setGradeNum(gradeNum);
        course.setDueDate(LocalDate.now());
        outstandingCourses.remove(courseNum);

        // writes the two files
        exportCourseworkToFile(input, outstandingCourses);
        appendCourseworkToFile(output, course);
        
        // returns success message
        return "Your coursework was successfully completed";
    }
 
    /* Helper function that finishes work and assumes the course file path
     */
    public static String finishWork(int courseNum, String gradeNum) throws IOException { 
        // finished and outstanding file paths
        File input = new File("courseList.txt");
        File output = new File("coursesFinished.txt");
        
        // ensures files are created
        if (!input.exists()) {
        	input.createNewFile();
        }
 
        if (!output.exists()) {
        	 output.createNewFile();
        }

        return finishWorkBase(input, output, courseNum, gradeNum);
    }

    /* Function that loads coursework from a file (outstanding or finished)
     * Parameters:
     *   file should be the path to the file that contains coursework
     */
    public static Hashtable<Integer, Coursework> loadCourseworkFromFile(File file) throws IOException {
    	// instantiates the list
    	Hashtable<Integer, Coursework> courseworkList = new Hashtable<Integer, Coursework>();
    	
    	// opens the file for reading
		Scanner input = new Scanner(file);

		// processes each line
		String line;
		Coursework cw;
		while (input.hasNextLine()) {
			// gets the file input
			line = input.nextLine();

			// loads the coursework
			cw = new Coursework();
			cw.fromString(line);

			// updates hashtable
			courseworkList.put(cw.getCourseNum(), cw);
		}
		
		input.close();
    	
    	return courseworkList;
    }
    
    /* This writes a hashtable of coursework to a file
     * Parameters:
     *   file should be the file you want to write to
     *   courseworkList should be the hashtable that contains the data to be written
     */
    public static void exportCourseworkToFile(File file, Hashtable<Integer, Coursework> courseworkList) throws IOException {
		// writes the new file contents
		BufferedWriter bufOut = new BufferedWriter(new FileWriter(file));

		// iterates over key val pairs
		for (Coursework cw : courseworkList.values()) {
			bufOut.write(cw.toString());
			bufOut.newLine();
		}
		bufOut.flush();
		bufOut.close();	
    }

    /* This appends an individual coursework object to a file
     * Parameters:
     *   file should be the file you want to write to
     *   course should be the coursework object you would like appended
     */
    public static void appendCourseworkToFile(File file, Coursework course) throws IOException {
        // append writer
    	BufferedWriter bufOut = new BufferedWriter(new FileWriter(file, true));
 
    	// writes the data
    	bufOut.write(course.toString());
    	bufOut.newLine();
    	bufOut.flush();
		bufOut.close();
    }
  
    /* Function that reads the courses file and loads the list of course work
     * Parameters:
     *   file should be the file that you want to read coursework objects from
     * Returns: the list of courses inside the file
     */
	public static ObservableList<Coursework> getCoursesObservableList(File file) throws IOException {
        // the return list
        ObservableList<Coursework> courses = FXCollections.observableArrayList();
        
        // loads the list
        Hashtable<Integer, Coursework> courseworkList = loadCourseworkFromFile(file);
        
        // loads the courses into the list
		for (Coursework cw : courseworkList.values()) {
			courses.add(cw);
		}
		
        return courses;
	}

	/* Helper function that gets an observable list of outstanding courses by assuming filepath
	 */
	public static ObservableList<Coursework> getOutstandingObservableList() throws IOException {
		// input file
        File file = new File("courseList.txt");
        
        return getCoursesObservableList(file);
	}

	/* Helper function that gets an observable list of finished courses by assuming filepath
	 */
	public static ObservableList<Coursework> getFinishedObservableList() throws IOException {
		// input file
        File file = new File("coursesFinished.txt");
        
        return getCoursesObservableList(file); 
	}
}


