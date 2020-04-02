package logic.view;

public enum MSG {
	ERROR_FORM ("FORM ERROR!"),
	ERROR_ALREADY_ANSWERED("ERROR: Already Answered"),
	INFO_CONTACTUSER ("INFO: Contact User Informations"),
	INFO_SUCCESS ("SUCCESS")
	;
	
	private final String msg;
	
	private MSG(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
