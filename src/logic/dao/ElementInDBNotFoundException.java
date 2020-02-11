package logic.dao;

public class ElementInDBNotFoundException extends Exception {
	
	final private String path;
	
	public ElementInDBNotFoundException(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}

}
