package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdatePlayerController extends ScreenController{

	@FXML private Button cancelButton;
	@FXML private TextField addressTextBox;
	@FXML private TextField postalCodeTextBox;
    @FXML private TextField phoneNumberTextBox;
    @FXML private ComboBox<Integer> selectPlayerComboBox;
    @FXML private Label ProvinceLabel;
    @FXML private TextField ProvinceTextBox;    
    @FXML private Button updateButton;  
    @FXML private TextField lastNameTextBox; 
    @FXML private TextField firstNameTextBox;
    
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
    	Parent parent;
    	parent = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
		super.setScreen(event, parent, "Game Application");    	
    }
   
    @FXML
    void updateButtonHandler(ActionEvent event) {

    }

}
