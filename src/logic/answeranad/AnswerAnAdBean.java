package logic.answeranad;

public class AnswerAnAdBean {
	
	private int id;
	private String username;
	private String type;
	
	public AnswerAnAdBean() {
		this.id = -1;
		this.username = "";
		this.type = "";
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getType() {
		return this.type;
	}
}
