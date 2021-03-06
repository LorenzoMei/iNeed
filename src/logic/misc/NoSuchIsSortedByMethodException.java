package logic.misc;

public class NoSuchIsSortedByMethodException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Order order;
	
	public NoSuchIsSortedByMethodException(Order order) {
		super("unsupported order " + order.getValue());
		this.order = order;
	}
	
	public Order getOrder() {
		return this.order;
	}

}
