package logic.dao;

public class NoSuchLastIdLoaderException extends Exception {
	private final String type;
	
	public NoSuchLastIdLoaderException(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
