package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddPlayerController extends ScreenController{

	@FXML private Button cancelButton;
	@FXML private TextField addressTextBox;
	@FXML private TextField postalCodeTextBox;
	@FXML private TextField phoneNumberTextBox;
	@FXML private Button addButton;
	@FXML private TextField lastNameTextBox;
    @FXML private TextField provinceTextBox;
    @FXML private TextField firstNameTextBox;
    @FXML private Label statusLabel;
    ArrayList<String> textFieldList;
    boolean hasNullField=false;
    boolean isIncorrectFormat=false;
    
	@FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
    	Parent parent=FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
    	super.setScreen(event, parent, "Game Application");
    }
    
	@FXML
    void addButtonHandler(ActionEvent event) {

		populateTextFieldsList();
		addPlayer();
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
		
	}
	public void addPlayer()
	{
		
		if (hasNullField) {			
			
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
			Player insertPlayer=new Player();
			
			insertPlayer.setFirst_name(firstNameTextBox.getText());
			insertPlayer.setLast_name(lastNameTextBox.getText());
			insertPlayer.setAddress(addressTextBox.getText());
			insertPlayer.setPostal_code(postalCodeTextBox.getText());
			insertPlayer.setProvince(provinceTextBox.getText());
			insertPlayer.setPhone_number(phoneNumberTextBox.getText());
			
			String SQLQuery = "INSERT into player " +
						   	  "(first_name, last_name, address, postal_code, province, phone_number)"+
						   	  "VALUES (?,?,?,?,?,?)";
			try(
				Connection connection = DbConfig.getConnection();
	  			PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);
				) {
				
				statement.setString(1, insertPlayer.getFirst_name());
				statement.setString(2, insertPlayer.getLast_name());
				statement.setString(3, insertPlayer.getAddress());
				statement.setString(4, insertPlayer.getPostal_code());
				statement.setString(5, insertPlayer.getProvince());
				statement.setString(6, insertPlayer.getPhone_number());

				int affected=statement.executeUpdate();
				statusLabel.setText("Player successfully added!");
				
			} catch (SQLException exception) {
				statusLabel.setText("Invalid player info");
				DbConfig.DisplayException(exception);
			}			
		}
	}

}
