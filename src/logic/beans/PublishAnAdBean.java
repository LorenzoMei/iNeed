package logic.beans;

import logic.entity.Ads;

public class PublishAnAdBean {
	private String ownerUsername;
	private String title;
	private String body;
	private Ads type;
	private String category;
	
	public PublishAnAdBean() {
		this.ownerUsername = "";
		this.title = "";
		this.body = "";
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
		for (Ads t : Ads.values()) {
			if (t.getName().compareTo(type) == 0) {
				this.type = t;
			}
		}
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
		return this.type.getName();
	}
	
	public String getCategory() {
		return this.category;
	}
	public void setRequestType() {
		this.type = Ads.REQUEST;
	}
	public void setOfferType() {
		this.type = Ads.OFFER;
	}
}
