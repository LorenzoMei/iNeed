package logic.dao;

public class ElementInDBNotFoundException extends Exception {
	
	private String path;
	
	public ElementInDBNotFoundException(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}

}
