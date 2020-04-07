package logic.view.components;

public enum FXMLPaths {
	AD ("ad.fxml"),
	CHECKANSWERS ("checkanswers.fxml"),
	CURRICULUM_MAIN_PAGE ("curriculummain.fxml"),
	FLOW("flow.fxml"),
	FRAME("frame.fxml"),
	LOGIN("login.fxml"),
	MAKEANAD("makeanad.fxml"),
	VIEWMAP("map.fxml"),
	REGULATIONS("regulations.fxml"),
	SIGNUP("signup.fxml"),
	TOOLBAR("toolbar.fxml"),
	PROFILE("user.fxml"),
	VALIDATEFAVOR("validateafavor.fxml"),
	WALLET("wallet.fxml")
	;
	private String path = "bin/logic/view/components/";

	private FXMLPaths(String name) {
		this.path += name;
	}
	public String getPath() {
		return path;
	}
}
