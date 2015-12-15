/*
 * MainWindowController class to handle events for displaying various options
 */

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

public class MainWindowController extends ScreenController implements Initializable{

    @FXML
    private ComboBox<String> actionComboBox;

    @FXML
    void actionComboBoxHandler(ActionEvent event) throws IOException {

    	Parent parent;
    	int selectedIndex=actionComboBox.getSelectionModel().getSelectedIndex();
    	String title=actionComboBox.getSelectionModel().getSelectedItem();
    	
    	//Switch to a different scene based on choice selected by user
    	switch(selectedIndex)
    	{
    	case 0:
    		parent=FXMLLoader.load(getClass().getResource("/fxml/AddPlayer.fxml"));
    		break;
    	case 1:
    		parent=FXMLLoader.load(getClass().getResource("/fxml/AddGame.fxml"));
    		break;
    	case 2:
    		parent=FXMLLoader.load(getClass().getResource("/fxml/UpdatePlayer.fxml"));
    		break;
    	case 3:
    		parent=FXMLLoader.load(getClass().getResource("/fxml/DisplayPlayerGame.fxml"));
    		break;
    	default:
    		parent=FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
    		break;
    	}  
    	
    	//Set scene
    	super.setScreen(event,parent,title);
    	
    }

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		
		//Add items to comboBox
		actionComboBox.getItems().addAll(
				"Add new Player",
				"Add new Game",
				"Update Player Info",
				"Display Player Game Report"
				);
	}

}