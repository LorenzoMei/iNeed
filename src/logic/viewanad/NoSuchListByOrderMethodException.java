package logic.viewanad;

public class NoSuchListByOrderMethodException extends Exception {
	
	private final String order;
	
	public NoSuchListByOrderMethodException(String order) {
		super("unsupported order " + order);
		this.order = order;
	}
	
	public String getOrder() {
		return this.getOrder();
	}

}
