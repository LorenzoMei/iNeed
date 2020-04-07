package logic.view.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdDetailsBean{
	private StringProperty type = new SimpleStringProperty(this, "type");
	private StringProperty title = new SimpleStringProperty(this, "title");
	private StringProperty author = new SimpleStringProperty(this, "author");
	private StringProperty date = new SimpleStringProperty(this, "date");
	private StringProperty category = new SimpleStringProperty(this, "category");

	
	public AdDetailsBean() {
		this.setAuthor("N/A");
		this.setDate("N/A");
		this.setTitle("N/A");
		this.setType("N/A");
		this.setCategory("N/A");

	}
	
	public String getType() {
		return this.type.getValue();
	}
	public String getTitle() {
		return this.title.getValue();
	}
	public String getAuthor() {
		return this.author.getValue();
	}
	public String getCategory() {
		return this.category.getValue();
	}
	public String getDate() {
		return this.date.getValue();
	}
	public void setType(String type) {
		this.type.setValue(type);
	}
	public void setTitle(String title) {
		this.title.setValue(title);
	}
	public void setAuthor(String author) {
		this.author.setValue(author);
	}
	public void setCategory(String author) {
		this.category.setValue(author);
	}
	public void setDate(String date) {
		this.date.setValue(date);
	}
	
}