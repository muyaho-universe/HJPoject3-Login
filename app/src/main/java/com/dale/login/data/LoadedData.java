package com.dale.login.data;

public class LoadedData {
	private String u_id = null;
	private String password = null;
	private String name = null;
	private String gender = null;
	private String phoneNumber;
	private String date = null;
	
	public LoadedData(String u_id, String password, String name, String gender, String phoneNumber, String date) {
		this.u_id = u_id;
		this.password = password;
		this.name = name; 
		this.gender = gender;
		this.phoneNumber = phoneNumber; 
		this.date = date;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
