package logic.view;

public enum MSGError {
	ERROR_FORM ("FORM ERROR!"),
	ERROR_ALREADY_ANSWERED("ERROR: Already Answered")
	;
	
	private final String msg;
	
	private MSGError(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
