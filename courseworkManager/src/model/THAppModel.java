package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Path;

public class THAppModel {

    public static void addWork(int courseNum, String courseName, String workType, LocalDate dueDate) throws IOException {

        //'Coursework' object 'cw' created
        Coursework cw = new Coursework();
        //'buf' created to write lines to the file
        BufferedWriter bWriter = new BufferedWriter(new FileWriter("courseList.txt", true));
        PrintWriter pWriter = new PrintWriter(bWriter);
        //Sets up course work user would like to add
        String gradeNum = "_";
        cw.setCourseNum(courseNum);
        cw.setCourseName(courseName);
        cw.setWorkType(workType);
        cw.setDueDate(dueDate);
        cw.setGradeNum(gradeNum);

        String toFile = cw.toString();

        pWriter.println(toFile);

        pWriter.close();
        bWriter.close();
    }

    public static int alreadyRan = 0;
    public static int lineNum = 0;
    public static int totalLineCount = 0;
    static ArrayList<String> entry = new ArrayList<String>();
    static ArrayList<String> finished = new ArrayList<String>();


    public static void finishWork(int courseNum, String workType, String gradeNum) throws IOException {

        // Turn courseNum int into String
        String courseNumber = String.valueOf(courseNum);

        // Create two file's, courseList + coursesFinished

        File file = new File("courseList.txt");
        File output = new File("coursesFinished.txt");

        // If/else statement to check if coursesFinished is created or not

        if (output.exists()) {

        } else {
            output.createNewFile();
        }

        // Creating writer for coursesFinished.txt

        FileWriter myWriter = new FileWriter(output);

        // Try/catch statement to try to find the course the user entered to then remove from the file

        try {
            Scanner scanner = new Scanner(file);

            // Reading coursesList.txt line by line

            while (scanner.hasNextLine()) {
                // Input's single line into line1
                String line1 = scanner.nextLine();
                // Counters to verify that the courses are being found properly
                totalLineCount++;
                lineNum++;

                // If it is found with the proper course number, work type, and a "_" denoting it has not been finished yet it will write it to the coursesFinished.txt file
                if (line1.contains(courseNumber) && line1.contains(workType) && line1.contains("_")) {

                    // Creating String that replaces the "_" with the grade the user entered and then writing it to coursesFinished.txt
                    String replaceWith = "" + gradeNum;
                    String replacement = line1.replaceAll("_", replaceWith);

                    myWriter.write("\n" + replacement);


                }
                // If the line1 does not contain the proper courseNum, workType and "_" it will add it to an ArrayList so it can be re-printed in courseList.txt later
                else if (alreadyRan == 0) {
                    entry.add(line1);
                }


            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Your file was not found. Please make sure it is proper");
        }
        myWriter.close();


        // Creating a new buffered/and print writer to courseList.txt to update the removed course
        BufferedWriter bWriter = new BufferedWriter(new FileWriter("courseList.txt", true));
        PrintWriter pWriter = new PrintWriter(bWriter);

        //FileWriter writer = new FileWriter("courseList.txt");

        // For loop will iterate through the arrayList entry and print it back to courseList.txt

        for (String string : entry) {


            pWriter.println(string);

        }

        // Closing/clearing no-longer needed items
        pWriter.close();
        entry.clear();
    }

	public static ObservableList<Coursework> getCoursesObservableList() {
        File file = new File("courseList.txt");
        ObservableList<Coursework> courses = FXCollections.observableArrayList();
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String courseInfo = scanner.nextLine();
                if (!courseInfo.isEmpty()) {
                    System.out.println("Loading course info: " + courseInfo);
                    Coursework coursework = new Coursework();
                    coursework.fromString(courseInfo);
                    courses.add(coursework);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Your file was not found. Please make sure it is proper");
        }
        return courses;
	}
}


