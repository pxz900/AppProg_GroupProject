package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CMAppModel;

/* This class is the controller for the add scene
 */
public class AddController {
ObservableList<String> types = FXCollections.observableArrayList("Test", "Quiz", "Homework", "Project", "Other");
	
	@FXML
	private TextField workTypes;
	
	@FXML
	private TextField courseNum;
	
	@FXML
	private TextField courseName;
	
	@FXML
	private DatePicker dueDate;
	
	@FXML
	private AnchorPane mainPane;
	
    /* Loads the menu FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
	@FXML
	public void handleMain(ActionEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	/* Control function that fetches user input and adds course work 
	 */
	@FXML
	public void operateAdd(ActionEvent event) throws IOException{
		// the alert
    	Alert a = new Alert(AlertType.NONE);
    	
		// checks the course number is in the correct format
    	int num;
		try {
			num = Integer.parseInt(courseNum.getText().toString());			
		} catch (NumberFormatException e) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Please enter a valid course number");
			a.show();
			return;
		}
    	
    	// checks the due date is valid
    	LocalDate due = dueDate.getValue();
    	if (due == null) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Please enter a valid date");
			a.show();
			return;
    	}
    	
    	// text fields
    	String name = courseName.getText().toString();
    	String type = workTypes.getText().toString();
    	
		// checks that all fields were filled out
		if (name == "" || type == "") {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Please fill out all fields to make a request. Thank you!");
			a.show();
			return;
		}
    	
    	// adds the work
    	CMAppModel.addWork(num, name, type, due);
    	
		//set alert type
		a.setAlertType(AlertType.CONFIRMATION);
		//sets dialogue
		a.setContentText("Successfully added course work to inventory!");
		//show dialogue
		a.show();
    	
    	//clears textfields and dueDate
    	courseNum.clear();
    	courseName.clear();
    	workTypes.clear();
    	dueDate.setValue(null);
	}
}
