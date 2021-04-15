package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddController {
ObservableList<String> types = FXCollections.observableArrayList("Test", "Quiz", "Homework", "Project", "Other");
	
	@FXML
	private ChoiceBox<String> workTypes;
	
	@FXML
	private TextField courseNum;
	
	@FXML
	private TextField courseName;
	
	@FXML
	private DatePicker dueDate;
	
	
	@FXML
	private void initialize() {
		
		workTypes.setValue("Test");
		
		workTypes.setItems(types);
		
	}
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	public void handleMain(ActionEvent event) throws IOException{
		mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(mainPane);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
