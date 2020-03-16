package logic.view;

public enum GoNextTargets {
	INITIALSTATE(InitialState.class.getSimpleName()),
	VIEW_SIGNUP(ViewSignUp.class.getSimpleName()),
	VIEW_FLOW(ViewFlow.class.getSimpleName()),
	VIEW_LOGIN(ViewLogin.class.getSimpleName()),
	VIEW_MAKEANAD(ViewMakeAnAd.class.getSimpleName()),
	VIEW_MAP(ViewMap.class.getSimpleName()),
	VIEW_USER(ViewUser.class.getSimpleName()),
	VIEW_REGULATIONS(ViewRegulations.class.getSimpleName()),
	VIEW_CURRICULUMMAIN(ViewCurriculumMainPage.class.getSimpleName()),
	VIEW_CURRICULUM(ViewCurriculumMainPage.class.getSimpleName())
//	VIEW_VALIDATEAFAVOR(ViewValidateAFavor.class.getName())
	;
	
	private final String stateName;
	
	private GoNextTargets(String stateName) {
		this.stateName = stateName;
	}
	
	public String getStateName() {
		return this.stateName;
	}
}



