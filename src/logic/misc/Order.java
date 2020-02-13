package logic.misc;

public enum Order {
	TIME ("Time"), UNSORTED (null);
	
	private Order(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return this.value;
	}
}
