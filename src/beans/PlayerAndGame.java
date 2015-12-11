package beans;

import java.sql.Date;

/*
 * PlayerAndGame Model : Database Schema
 */

public class PlayerAndGame {

	//Private instance variables
	private int _player_game_id;
	private int _game_id;
	private int _player_id;
	private Date _playing_date;
	private int _score;
	
	//Getters and Setters
	public int getPlayer_game_id() {
		 return this._player_game_id;
	}
	public void setPlayer_game_id(int player_game_id) {
		this._player_game_id = player_game_id;
	}
	
	public int getGame_id() {
		 return this._game_id;
	}
	public void setGame_id(int game_id) {
		this._game_id = game_id;
	}
	
	public int getPlayer_id() {
		 return this._player_id;
	}
	public void setPlayer_id(int player_id) {
		this._player_id = player_id;
	}
	
	public Date getPlaying_date() {
		 return this._playing_date;
	}
	public void setPlaying_date(Date playing_date) {
		this._playing_date = playing_date;
	}
	
	public int getScore() {
		 return this._score;
	}
	public void setScore(int score) {
		this._score = score;
	}

}
