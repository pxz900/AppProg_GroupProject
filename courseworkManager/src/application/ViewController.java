package application;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import model.THAppModel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ViewController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView courseworkTable;

    @FXML
    public void handleHome(MouseEvent event) throws IOException {
        mainPane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(mainPane);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void initialize(){
        System.out.println("Test");
        Coursework test = new Coursework();
        test.setCourseNum(123);
        test.setCourseName("Test");
        test.setWorkType("Quiz");
        test.setDueDate(LocalDate.now());
        test.setGradeNum("A");
        ObservableList<Coursework> courses = THAppModel.getCoursesObservableList();
        courseworkTable.setItems(courses);
    }
}