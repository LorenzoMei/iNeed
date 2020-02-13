package logic.viewanad;

public class NoSuchIsOrderedByMethodException extends Exception {
	
	private final Order order;
	
	public NoSuchIsOrderedByMethodException(Order order) {
		super("unsupported order " + order.getValue());
		this.order = order;
	}
	
	public Order getOrder() {
		return this.order;
	}

}
