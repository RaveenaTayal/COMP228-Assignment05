/*
 * ScreenController class to set the scene for the stage
 */

package controllers;

import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ScreenController {
	
	void setScreen(ActionEvent event,Parent parent,String title ) {
		
		Scene scene=new Scene(parent,400,400);
		
		//Get stage of the calling source
		Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		
		//Set title of stage
		stage.setTitle(title);
		
		//Set scene of stage
    	stage.setScene(scene);
    	stage.show();
	}

}
