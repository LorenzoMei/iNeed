package logic.dao;

public class FavorNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 845753718918135342L;
	
	public FavorNotFoundException(String path, Throwable cause) {
		super(path, cause);
	}
}
