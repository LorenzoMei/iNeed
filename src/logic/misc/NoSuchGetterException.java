package logic.misc;

public class NoSuchGetterException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String attrName;
	
	public NoSuchGetterException(String attrName) {
		super("no such getter for attribute " + attrName);
		this.attrName = attrName;
	}
	
	public String getAttrName() {
		return this.attrName;
	}
}
