package logic.beans;

public enum MessageInfos {
	SENDER("getUserSenderUsername"),
	RECEIVER("getUserReceiverUsername"),
	BODY("getText")
	;
	
	private MessageInfos(String memberName) {
		this.getterName = memberName;
	}
	
	private String getterName;
	
	public String getGetterName() {
		return this.getterName;
	}
	
}


