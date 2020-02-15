package logic.entity;

import java.util.Calendar;

public class User {
	
	private String username;
	private String passw;
	private String city;
	private String email;
	private String name;
	private String surname;
	private Calendar bDate;
	private int tokens;
	
	//all the sets on attributes
	
	public void setTokens(int val) {
		this.tokens = val;
	}
	
	public void setBDate(Calendar val) {
		this.bDate = val;		
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surName) {
		this.surname = surName;
	}
	
	//all the gets on attributes
	
	public int getTokens() {
		return this.tokens;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassw() {
		return this.passw;
	}
	public String getSurname() {
		return this.surname;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Calendar getBDate() {
		return this.bDate;
	}
	
//	other methods
	
	public int gainAToken() {
		return ++ this.tokens;
	}
	public int payAToken() {
		return -- this.tokens;
	}
}