/*
 * AddGameController class to handle events and SQL queries related to adding a new game
 */

package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddGameController extends ScreenController{
	
	//TextFields
	@FXML private TextField gameTitleTextBox;
	
	//Labels
	@FXML private Label statusLabel; //Label to display status of adding a game to database

	//Action Handler for cancelButton
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {

    	//Set scene to GameApplication.fxml
    	Parent parent=FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
    	super.setScreen(event, parent, "Game Application");
    }
    
    //Action Handler for addButton
    @FXML
    void addButtonHandler(ActionEvent event) {
    	addGame();
    }
    
	public void addGame()
	{
		String game_title=gameTitleTextBox.getText();
		
		//Check if title is null
		if (game_title.equals("")) {			
			
			statusLabel.setText("Enter game title to add !!");
		}
		else
		{
			Game insertGame=new Game();
			
			//SQL Query to add new game 
			String SQLQuery = "INSERT into game (game_title) VALUES (?)";
			try(
				Connection connection = DbConfig.getConnection();
	  			PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
				) {
				
				insertGame.setGame_title(game_title);
				statement.setString(1, "'"+game_title+"'");
				int affected=statement.executeUpdate();
				statusLabel.setText("Game successfully added!");
				
			} catch (SQLException exception) {
				statusLabel.setText("Invalid Game Title");
				DbConfig.DisplayException(exception);
			}			
		}
	}

}
