package logic.answeranad;

import java.util.Calendar;

public class UserAlreadyAnsweredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8840741418995275559L;
	
	public UserAlreadyAnsweredException(Calendar date) {
		this.date = date;
	}

	private final Calendar date;

	public Calendar getDate() {
		return date;
	}
	
}
