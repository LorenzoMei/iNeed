package logic.view;

public enum MSGError {
	ERROR_FORM ("FORM ERROR!"),
	;
	
	private final String msg;
	
	private MSGError(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
