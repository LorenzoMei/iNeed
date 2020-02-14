package logic.beans;

public enum KeyAnswersAnAdBean {
	ANSWERANADBEAN_ID("id"),
	ANSWERANADBEAN_USERNAME("username"),
	ANSWERANADBEAN_TYPE("type");
	
	private final String keyName;
	
	private KeyAnswersAnAdBean(String keyName) {
		this.keyName = keyName;
	}
	
	public String getKeyName() {
		return this.keyName;
	}
}
