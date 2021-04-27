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

public class MainController {
	
	
	@FXML
	private AnchorPane mainPane;
	
	//handles button to take you to Add Scene
	@FXML
	public void handleAdd(ActionEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("Add.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	public void handleFinish(MouseEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("Finish.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
	
	
}
