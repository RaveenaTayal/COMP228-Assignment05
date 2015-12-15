package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import beans.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddGameController extends ScreenController{
	
	@FXML private TextField gameTitleTextBox;
	@FXML private Label statusLabel;

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {

    	Parent parent=FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
    	super.setScreen(event, parent, "Game Application");
    }
    
    @FXML
    void addButtonHandler(ActionEvent event) {
    	addGame();
    }
    
	public void addGame()
	{
		String game_title=gameTitleTextBox.getText();
		
		if (game_title.equals("")) {			
			
			statusLabel.setText("Enter game title to add !!");
		}
		else
		{
			Game insertGame=new Game();	
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
