package logic.PublishARequestAd;

public class PublishARequestAdBean {
	private String title;
	private String body;
	private static PublishARequestAdBean instance;
	
	public static PublishARequestAdBean getInstance() {
		if(instance == null) 
			instance = new PublishARequestAdBean();
		return instance;
	}
	
	private PublishARequestAdBean() {
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
