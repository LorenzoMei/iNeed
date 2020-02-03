package logic.publishanad;

public class Post {
	private String title;
	private String body;
	private String type;
	
	public Post() {
		this.title = "";
		this.body = "";
		this.type = "";
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
	
	public String getTitle() {
		return this.title;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public String getType() {
		return this.type;
	}
}
