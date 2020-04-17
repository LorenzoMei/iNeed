package logic.beans;

public class CredentialsBean {
	private String username;
	private String password;
	
	public CredentialsBean() {
		this.username = "";
		this.password = "";
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String passw) {
		this.password = passw;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
}
