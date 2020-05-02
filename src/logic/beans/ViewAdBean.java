package logic.beans;

import java.util.Calendar;

public class ViewAdBean {
	private String title;
	private String type;
	private Calendar dateOfPublication;
	private String body;
	private String author;
	private int id;
	
	public ViewAdBean(String title, String type, Calendar dateOfRequest, String body, String author, int id) {
		this.setTitle(title);
		this.setType(type);
		this.setDateOfPublication(dateOfRequest);
		this.setBody(body);
		this.setAuthor(author);
		this.setId(id);
	}
	
	public ViewAdBean() {
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}
	public String getType() {
		return this.type;
	}
	public Calendar getDateOfPublication() {
		return this.dateOfPublication;
	}
	public String getBody() {
		return this.body;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDateOfPublication(Calendar dateOfRequest) {
		this.dateOfPublication = dateOfRequest;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
