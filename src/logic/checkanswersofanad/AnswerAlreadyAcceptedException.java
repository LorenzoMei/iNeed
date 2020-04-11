package logic.checkanswersofanad;

import java.util.Calendar;

public class AnswerAlreadyAcceptedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -281115820388559846L;
	
	public AnswerAlreadyAcceptedException(Calendar date) {
		this.dateOfRequest = date;
	}
	
	private final Calendar dateOfRequest;

	public Calendar getDateOfRequest() {
		return dateOfRequest;
	}
	
}
