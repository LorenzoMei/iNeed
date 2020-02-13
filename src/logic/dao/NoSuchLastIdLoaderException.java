package logic.dao;

public class NoSuchLastIdLoaderException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3167602627607022078L;
	private final String type;
	
	public NoSuchLastIdLoaderException(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
