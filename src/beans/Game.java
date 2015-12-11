package beans;

/*
 * Game Model : Database Schema
 */

public class Game {

	//Private instance variables
	private int _game_id;
	private String _game_title;
	
	//Getters and Setters
	public int getGame_id() {
		return this._game_id;
	}
	public void setGame_id(int game_id) {
		this._game_id = game_id;
	}
	
	public String getGame_title() {
		return this._game_title;
	}
	public void setGame_title(String game_title) {
		this._game_title = game_title;
	}

}
