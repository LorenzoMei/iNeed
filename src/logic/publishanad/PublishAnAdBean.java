package logic.publishanad;

public class PublishAnAdBean {
	private String title;
	private String body;
	private static PublishAnAdBean instance;
	
	public static PublishAnAdBean getInstance() {
		if(instance == null) 
			instance = new PublishAnAdBean();
		return instance;
	}
	
	private PublishAnAdBean() {
		this.title = "";
		this.body = "";
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getBody() {
		return this.body;
	}
}
