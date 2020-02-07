package logic.entity;

public class User {
	
	private String username;
	private String passw;
	private String city;
	private String email;

	
	
	public User() {
		this.username = "";
		this.passw = "";
		this.city = "";
		this.email = "";
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
}