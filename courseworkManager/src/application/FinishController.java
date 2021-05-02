package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CMAppModel;
import javafx.scene.Node;


/* This class is the main controller for the finish scene
 */
public class FinishController {

	private AnchorPane mainPane;
	
	@FXML
    private Button submitBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField text_courseNum;
    
    @FXML
    private TextField text_courseGrade;
    
    @FXML
    
    /* Loads the menu FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
	public void handleMain(MouseEvent event) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	/* Control function that fetches user input and completes course work
     * Will show alert confirmation on success
	 */
	public void submitCourse(MouseEvent event) throws IOException {
		// the alert
    	Alert a = new Alert(AlertType.NONE);
		
		// checks the course number is in the correct format
    	int num;
		try {
			num = Integer.parseInt(text_courseNum.getText().toString());			
		} catch (NumberFormatException e) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Please enter a valid course number");
			a.show();
			return;
		}

		// text fields
		String grade = text_courseGrade.getText();
		
		// checks that all fields were filled out
		if (grade == "") {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Please fill out all fields to make a request. Thank you!");
			a.show();
			return;
		}
		
		// finishes course
		String finishSuccess = CMAppModel.finishWork(num, grade);
		
		//set alert type
		a.setAlertType(AlertType.CONFIRMATION);
		//sets dialogue
		a.setContentText(finishSuccess);
		//show dialogue
		a.show();
		
		// clears user fields
		text_courseNum.clear();
		text_courseGrade.clear();
	}
}
