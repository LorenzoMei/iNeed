package logic.validateafavor;

public class ValidateAFavorController {
	
	private static ValidateAFavorController ref = null;
	
	public static ValidateAFavorController getReference() {
		if (ref == null) {
			ref = new ValidateAFavorController();
		}
		return ref;
	}
	
	private ValidateAFavorController() {}
	
	public void ValidateAFavor() {}
	
}
