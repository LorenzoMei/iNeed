package logic.entity;

public class User {
	
	private String username;
	private String passw;
	private String city;

	
	
	public User() {
		this.username = "";
		this.passw = "";
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassw() {
		return this.passw;
	}
}