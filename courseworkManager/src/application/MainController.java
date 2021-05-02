package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/* This class is the controller for the main scene
 */
public class MainController {
	
	
	@FXML
	private AnchorPane mainPane;
	
    /* Loads the Add FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
	@FXML
	public void handleAdd(ActionEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("Add.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
    /* Loads the finish FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
	public void handleFinish(MouseEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("Finish.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

    /* Loads the view outstanding courses FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
	public void handleViewOutstanding(MouseEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("ViewOutstanding.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

    /* Loads the view finished courses FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
	public void handleViewFinished(MouseEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("ViewFinished.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
