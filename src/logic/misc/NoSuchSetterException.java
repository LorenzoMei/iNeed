package logic.misc;

public class NoSuchSetterException extends Exception{

	private final String attrName;
	
	public NoSuchSetterException(String attrName) {
		this.attrName = attrName;
	}
	
	public String getAttrName() {
		return this.attrName;
	}
}
