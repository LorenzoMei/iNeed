package logic.checkanswersofanad;

import java.util.Calendar;

import logic.entity.Favor;

public class RequestAdHasAlreadyAnAnswerAcceptedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796311611557090519L;
	private final Favor favor;
	public RequestAdHasAlreadyAnAnswerAcceptedException(Favor favor) {
		this.favor = favor;
	}
	
	public String getOffererUsername() {
		return this.favor.getOffererUsername();
	}
	public String getRequesterUsername() {
		return this.favor.getRequesterUsername();
	}
	public Calendar getDate() {
		return this.favor.getDateOfRequest();
	}
}
