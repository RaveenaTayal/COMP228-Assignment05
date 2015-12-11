package beans;

/*
 * Player Model : Database Schema 
 */

public class Player {

	// Private instance variables
	private int _player_id;
	private String _first_name;
	private String _last_name;
	private String _address;
	private String _postal_code;
	private String _province;
	private String _phone_number;
	
	//Getters and Setters
	public int getPlayer_id() {
		return this._player_id;
	}
	public void setPlayer_id(int player_id) {
		this._player_id = player_id;
	}
	
	public String getFirst_name() {
		return this._first_name;
	}
	public void setFirst_name(String first_name) {
		this._first_name = first_name;
	}
	
	public String getLast_name() {
		return this._last_name;
	}
	public void setLast_name(String last_name) {
		this._last_name = last_name;
	}
	
	public String getAddress() {
		return this._address;
	}
	public void setAddress(String address) {
		this._address = address;
	}
	
	public String getPostal_code() {
		return this._postal_code;
	}
	public void setPostal_code(String postal_code) {
		this._postal_code = postal_code;
	}
	
	public String getProvince() {
		return this._province;
	}
	public void setProvince(String province) {
		this._province = province;
	}
	
	public String getPhone_number() {
		return this._phone_number;
	}
	public void setPhone_number(String phone_number) {
		this._phone_number = phone_number;
	}

}
