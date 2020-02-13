package logic.dao;

public class AdNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6450808616704868433L;

	public AdNotFoundException(ElementInDBNotFoundException cause) {
		super("Ad not found at location " + cause.getPath(), cause);
	}
}