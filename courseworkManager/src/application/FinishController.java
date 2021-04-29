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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.THAppModel;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;


/* This class is the main controller for the finish scene
 */
public class FinishController {

	private AnchorPane mainPane;
	
	@FXML
    private Button submitBtn;

    @FXML
    private TextArea textArea_output;

    @FXML
    private TextArea textArea_GPA;

    @FXML
    private Button homeBtn;

    @FXML
    private TextField text_courseNum;
    
    @FXML
    private TextField text_type;
    
    

    @FXML
    private TextField text_courseGrade;
    
    @FXML
    private Text AlertA_A;
    
    @FXML
    private Text AlertA_B;
   
    @FXML
    private Text AlertB_A;
	
    @FXML
    private Text AlertB_B;
    
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
		AlertA_A.setVisible(false);
		AlertA_B.setVisible(false);
		AlertB_A.setVisible(false);
		AlertB_B.setVisible(false);
		
		int cNum = Integer.parseInt(text_courseNum.getText().toString());
		String cType = text_type.getText();
		String cGrade = text_courseGrade.getText();
		// String numBoundary = "^(100|[1-9]?[0-9])$";
		
		
		THAppModel.finishWork(cNum, cType, cGrade);
			
		
		Alert add = new Alert(AlertType.NONE);
		//set alert type
		add.setAlertType(AlertType.CONFIRMATION);
		//sets dialogue
		add.setContentText("Successfully finished course work submitted!");
		//show dialogue
		add.show();
		
		text_courseNum.clear();
		text_type.clear();
		text_courseGrade.clear();
	}
	
}
