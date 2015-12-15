package controllers;

import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ScreenController {
	
	void setScreen(ActionEvent event,Parent parent,String title ) {
		
		Scene scene=new Scene(parent,400,400);
		Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
	}

}
