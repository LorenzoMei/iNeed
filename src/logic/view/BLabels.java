package logic.view;


public enum BLabels {
		
	ACCEPT ("Accept"),	
	ANSWER("Answer"),
	CHECKANSWERS("Check Answers"),
	CONTACTUSER ("Contact User"),
	DENY ("Deny"),
	OK("Ok"),
	SHOWDETAILS ("Show More"),
	VALIDATE ("Validate")
	;
	
	
	private String label;

	public String getLabel() {
		return label;
	}
	
	public static BLabels getConstant(String label) throws NoSuchLabelException {
		for (BLabels l : BLabels.values()) {
			if (l.getLabel().compareTo(label) == 0) {
				return l;
			}
		}
		throw new NoSuchLabelException(label);
	}

	private BLabels(String label) {
		this.label = label;
	}
}
