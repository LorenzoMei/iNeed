package logic.view;


public enum BLabels {
		
	ACCEPT ("Accept"),	
	CONTACTUSER ("Contact User"),
	DENY ("Deny"),
	ANSWER("Answer"),
	CHECKANSWERS("Check Answers"),
	SHOWDETAILS ("Show More")
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
