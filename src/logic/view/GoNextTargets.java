package logic.view;

public enum GoNextTargets {
	INITIALSTATE(InitialState.class.getName()),
	VIEW_SIGNUP(ViewSignUp.class.getName()),
	VIEW_FLOW(ViewFlow.class.getName()),
	VIEW_LOGIN(ViewLogin.class.getName()),
	VIEW_MAKEANAD(ViewMakeAnAd.class.getName()),
	VIEW_MAP(ViewMap.class.getName()),
	VIEW_USER(ViewUser.class.getName())
	;
	
	private final String stateName;
	
	private GoNextTargets(String stateName) {
		this.stateName = stateName;
	}
	
	public String getStateName() {
		return this.stateName;
	}
}



