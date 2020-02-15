package logic.misc;

public enum Type {
	REQUEST ("Richiesta"), OFFER ("Offerta");
	
	private Type(String value) {
		this.value = value;
	}
	
	private final String value;
	
	public String getValue() {
		return this.value;
	}
}
