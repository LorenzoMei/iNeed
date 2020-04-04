package logic.view;

public enum MSG {
	ERROR_FORM ,
	ERROR_ALREADY_ACCEPTED,
	ERROR_ALREADY_ANSWERED,
	CONFIRM_CHANGE_ANSWER_ACCEPTED,
	INFO_CONTACTUSER,
	INFO_SUCCESS
	;
	
	public String getMsg() {
		return this.toString();
	}
}
