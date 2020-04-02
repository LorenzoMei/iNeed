package logic.view;

public class NoSuchLabelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6222856900900446478L;
	
	NoSuchLabelException(String text){
		super(String.format("no such label with text: %s", text));
	}

}
