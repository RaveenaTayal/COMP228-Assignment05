package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddPlayerController extends ScreenController{

	@FXML private Button cancelButton;
	@FXML private TextField addressTextBox;
	@FXML private TextField postalCodeTextBox;
	@FXML private TextField phoneNumberTextBox;
	@FXML private Button addButton;
	@FXML private TextField lastNameTextBox;
    @FXML private TextField provinceTextBox;
    @FXML private TextField firstNameTextBox;
    
	@FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
    	Parent parent=FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
    	super.setScreen(event, parent, "Game Application");
    }
    
	@FXML
    void addButtonHandler(ActionEvent event) {

    }

}
