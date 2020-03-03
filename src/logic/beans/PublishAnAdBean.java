package logic.beans;


public class PublishAnAdBean {
	private String ownerUsername;
	private String title;
	private String body;
	private String type;
	private String category;
	
	public PublishAnAdBean() {
		this.ownerUsername = "";
		this.title = "";
		this.body = "";
		this.type = "";
		this.category = "";
	}
	 //sets
	public void setOwnerUsername(String user) {
		this.ownerUsername = user;
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
	public String getOwnerUsername() {
		return this.ownerUsername;
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
