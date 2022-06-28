package com.dale.login.data;

public class LoadedData {
	private String u_id = null;
	private String password = null;
	private String name = null;
	private String gender = null;
	private int phoneNumber;
	private String date = null;
	
	public LoadedData(String u_id, String password, String name, String gender, int phoneNumber, String date) {
		this.u_id = u_id;
		this.password = password;
		this.name = name; 
		this.gender = gender;
		this.phoneNumber = phoneNumber; 
		this.date = date;
	}
}
