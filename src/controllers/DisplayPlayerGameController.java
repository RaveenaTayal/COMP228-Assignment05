package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ResourceBundle;

import javafx.beans.property.adapter.JavaBeanBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DisplayPlayerGameController extends ScreenController implements Initializable{

	@FXML private GridPane playerGameGridPane;
    @FXML private ComboBox<String> selectPlayerComboBox;
    @FXML private Button backButton;
    @FXML private Label playerNameLabel;
    
    private GridPane dynamicGridPane;

	ObservableList<String> PlayerIDs;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		populatePlayerIDs();
		selectPlayerComboBox.setItems(PlayerIDs);
		
	}
	
	@FXML
    void backButtonHandler(ActionEvent event) throws IOException {
		Parent parent;
    	parent = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
		super.setScreen(event, parent, "Game Application");
    }
	
	@FXML
    void selectPlayerComboBoxHandler(ActionEvent event) throws SQLException {
		
		playerGameGridPane.getChildren().remove(dynamicGridPane);
		
		dynamicGridPane=new GridPane();
		dynamicGridPane.setVgap(10);
		
		int id=Integer.parseInt(selectPlayerComboBox.getSelectionModel().getSelectedItem());
		displayPlayerGame(id);

    }
	
	public void populatePlayerIDs() 
    {
    	PlayerIDs=FXCollections.observableArrayList();
    	
    	String SQLQuery = "SELECT player_id FROM player";
		
		try(
				Connection connection = DbConfig.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
				ResultSet resultSet = statement.executeQuery(SQLQuery);)
		{
			while(resultSet.next())
			{				
				
				int id = resultSet.getInt("player_id");	
				PlayerIDs.add(""+id);	
			}
		}
		catch(SQLException exception)
		{
			DbConfig.DisplayException(exception);				
		}		   	
    }
	
	public void displayPlayerGame(int id) throws SQLException
	{
		
		int count=0;
		String SQLQuery = "SELECT p.first_name, p.last_name, g.game_title, pg.playing_date, pg.score "+
				"FROM game g "+
				"INNER JOIN player_and_game pg ON g.game_id = pg.game_id "+
				"INNER JOIN player p ON p.player_id = pg.player_id "+
				"WHERE pg.player_id = ?";
		
		try(
				Connection connection = DbConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
				) {
			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next())
			{				
				String firstName = resultSet.getString("first_name");
				String lastName=resultSet.getString("last_name");
				String gameTitle = resultSet.getString("game_title");
				Date date = resultSet.getDate("playing_date");
				DateFormat dateFormat = DateFormat.getDateInstance();
				String formattedDate = dateFormat.format(date);
				int score = resultSet.getInt("score");
				
				playerNameLabel.setText("Games played by "+firstName+" "+lastName);
				
				Label label=new Label(gameTitle + " on " + formattedDate + " with score of " + score);
				label.setPrefWidth(400);
				
			    dynamicGridPane.add(label,0,count);
				count++;
			}
			playerGameGridPane.add(dynamicGridPane,0,4);
		}
		catch(SQLException exception)
		{
			DbConfig.DisplayException(exception);				
		}
	}

}
