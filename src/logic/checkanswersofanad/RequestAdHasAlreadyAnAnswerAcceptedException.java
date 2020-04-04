package logic.checkanswersofanad;

import java.util.Calendar;

import logic.entity.Favor;

public class RequestAdHasAlreadyAnAnswerAcceptedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8796311611557090519L;
	private Favor favor;
	public RequestAdHasAlreadyAnAnswerAcceptedException(Favor favor) {
		this.favor = favor;
	}
	
	public String getOfferer() {
		return this.favor.getOfferer().getUsername();
	}
	public String getRequester() {
		return this.favor.getRequester().getUsername();
	}
	public Calendar getDate() {
		return this.favor.getDateOfRequest();
	}
}
