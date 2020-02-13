package logic.misc;

public class NoSuchSetterException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7969732435826776901L;
	private final String attrName;
	
	public NoSuchSetterException(String attrName) {
		this.attrName = attrName;
	}
	
	public String getAttrName() {
		return this.attrName;
	}
}
