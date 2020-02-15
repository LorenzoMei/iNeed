package logic.beans;


public class PublishAnAdBean {
	private String username;
	private String title;
	private String body;
	private String type;
	private String category;
	
	public PublishAnAdBean() {
		this.username = "";
		this.title = "";
		this.body = "";
		this.type = "";
		this.category = "";
	}
	 //sets
	public void setUsername(String user) {
		this.username = user;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	//get
	public String getUsername() {
		return this.username;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getCategory() {
		return this.category;
	}
	
}
