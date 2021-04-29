package application;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Coursework;
import model.CMAppModel;

import java.io.IOException;


/* This class is for the outstanding view where you can view uncompleted coursework
 */
public class ViewOutstandingController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<Coursework> courseworkTable;
   
    /* Loads the menu FXML file and updates the scene
     * Takes event to set the scene from the button press
     */
    @FXML
    public void handleHome(MouseEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(mainPane);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /* Builtin method that populates the table view with the outstanding courses
     */
    @FXML
    public void initialize(){
    	ObservableList<Coursework> courses;
        try {
        	courses = CMAppModel.getOutstandingObservableList();
        } catch (IOException e) {
            courses = null;
        }

        // sets the data
        courseworkTable.setItems(courses);
    }
}