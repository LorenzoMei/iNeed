package logic.signup;

public class SignUpBean {
	
	private static SignUpBean instance;
	
	private String username;
	private String passw;
	private String email;
	private String city;
	
	public static SignUpBean getInstance() {
		if(instance == null)
			instance = new SignUpBean();
		return instance;
	}
	
	public SignUpBean() {
		this.username = "";
		this.passw = "";
		this.email = "";
		this.city = "";
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String passw) {
		this.passw = passw;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.passw;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getCity() {
		return this.city;
	}
}
