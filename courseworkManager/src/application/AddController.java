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
import model.THAppModel;

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
		//THAppModel model;
		
		int num = Integer.parseInt(courseNum.getText().toString());
    	String name = courseName.getText().toString();
    	String type = workTypes.getText().toString();
    	LocalDate due = dueDate.getValue();
    	
    	THAppModel.addWork(num, name, type, due);
    	
    	Alert add = new Alert(AlertType.NONE);
		//set alert type
		add.setAlertType(AlertType.CONFIRMATION);
		//sets dialogue
		add.setContentText("Successfully added course work to inventory!");
		//show dialogue
		add.show();
    	
    	//clears textfields and dueDate
    	courseNum.clear();
    	courseName.clear();
    	workTypes.clear();
    	dueDate.setValue(null);
	}
}
