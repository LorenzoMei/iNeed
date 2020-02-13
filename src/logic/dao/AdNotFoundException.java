package logic.dao;

public class AdNotFoundException extends Exception{
	
	public AdNotFoundException(ElementInDBNotFoundException cause) {
		super("Ad not found at location " + cause.getPath(), cause);
	}
}