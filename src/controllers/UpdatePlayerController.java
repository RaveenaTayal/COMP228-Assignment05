/*
 * UpdatePlayerController class to handle events and SQL queries related to updating a player
 */

package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import beans.Player;
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
import javafx.scene.control.TextField;

public class UpdatePlayerController extends ScreenController implements Initializable{

	//TextFields
	@FXML private TextField addressTextBox;
	@FXML private TextField postalCodeTextBox;
    @FXML private TextField phoneNumberTextBox;
    @FXML private TextField provinceTextBox;
    @FXML private TextField lastNameTextBox; 
    @FXML private TextField firstNameTextBox;
    
    //Buttons
    @FXML private Button cancelButton;
    @FXML private Button updateButton;  
    
    //ComboBox
    @FXML private ComboBox<String> selectPlayerComboBox;
    
    //Labels
    @FXML private Label statusLabel;
    
    //List to hold text values of textfields
    ArrayList<String> textFieldList;
    
    //List to hold player IDs
    ObservableList<String> PlayerIDs;
    boolean hasNullField=false;
    boolean isIncorrectFormat=false;
    boolean isPlayerSelected=true;
    
    //Action Handler for cancelButton
    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
    	//Set scene to GameApplication.fxml
    	Parent parent;
    	parent = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
		super.setScreen(event, parent, "Game Application");    	
    }
   
  //Action Handler for updateButton
    @FXML
    void updateButtonHandler(ActionEvent event) {
    	
    	populateTextFieldsList();
    	updatePlayer();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
		populatePlayerIDs();
		
		//Add items to combo box
		selectPlayerComboBox.setItems(PlayerIDs);
	}
    
    public void populatePlayerIDs() 
    {
    	PlayerIDs=FXCollections.observableArrayList();
    	
    	//SQL query to select all player IDs from player table
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
    public void populateTextFieldsList()
	{
		textFieldList=new ArrayList<String>();
		
		textFieldList.add(firstNameTextBox.getText());
		textFieldList.add(lastNameTextBox.getText());
		textFieldList.add(addressTextBox.getText());
		textFieldList.add(postalCodeTextBox.getText());
		textFieldList.add(provinceTextBox.getText());
		textFieldList.add(phoneNumberTextBox.getText());
		
		//Check each text field whether it has null values
		for (String text : textFieldList) {
			if(text.equals(""))
	        {
	        	hasNullField=true;
	        }
		}
		
		//Check postal code and phone number format
        if((postalCodeTextBox.getText().length()!=6)||(phoneNumberTextBox.getText().length()!=14))
        {
        	isIncorrectFormat=true;
        }
		
        if(selectPlayerComboBox.getSelectionModel().getSelectedItem()==null)
        {
        	isPlayerSelected=false;
        }
	}
    
    public void updatePlayer()
	{
    	/*
		 * Check if there are empty values, incorrect postal code or phone number format
		 */
    	
		if(isPlayerSelected==false) {
			statusLabel.setText("Please Select a playeR ID");
			isPlayerSelected=true;
		}
		else if (hasNullField) {			
			
			statusLabel.setText("Enter all details !!");
			hasNullField=false;
		}
		else if(isIncorrectFormat)
		{
			statusLabel.setText("Check postal code and phone number format");
			isIncorrectFormat=false;
		}
		else
		{
			Player updatePlayer=new Player();
			
			updatePlayer.setPlayer_id(Integer.parseInt(selectPlayerComboBox.getSelectionModel().getSelectedItem()));
			updatePlayer.setFirst_name(firstNameTextBox.getText());
			updatePlayer.setLast_name(lastNameTextBox.getText());
			updatePlayer.setAddress(addressTextBox.getText());
			updatePlayer.setPostal_code(postalCodeTextBox.getText());
			updatePlayer.setProvince(provinceTextBox.getText());
			updatePlayer.setPhone_number(phoneNumberTextBox.getText());
			
			String SQLQuery = "UPDATE player SET " +
			        "first_name = ?, last_name = ?, address = ?, postal_code = ?, province=?, phone_number = ?" +
			        "WHERE player_id = ?";
			try(
				Connection connection = DbConfig.getConnection();
	  			PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
				) {
				
				statement.setString(1, updatePlayer.getFirst_name());
				statement.setString(2, updatePlayer.getLast_name());
				statement.setString(3, updatePlayer.getAddress());
				statement.setString(4, updatePlayer.getPostal_code());
				statement.setString(5, updatePlayer.getProvince());
				statement.setString(6, updatePlayer.getPhone_number());
				statement.setInt(7, updatePlayer.getPlayer_id());

				int affected=statement.executeUpdate();
				if(affected==1)
				{
					statusLabel.setText("Player info successfully updated!");
				}
				
				
			} catch (SQLException exception) {
				statusLabel.setText("Invalid player info");
				DbConfig.DisplayException(exception);
			}			
		}
	}

	

}
