package logic.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class WIPAlertFactory  {
	
	private static WIPAlertFactory ref;
	
	public static WIPAlertFactory getReference() {
		if (ref == null) {
			ref = new WIPAlertFactory();
		}
		return ref;
	}
	
	private WIPAlertFactory() {}
	
	public Alert getWIPAlert() {
		Alert wipAlert = new Alert(AlertType.INFORMATION);
		wipAlert.setTitle(MSG.INFO_WIP.toString());
		wipAlert.setHeaderText("Coming soon!");
		wipAlert.setContentText("This feature has not been fully\n"
				+ "implemented yet. Stay tuned for updates!");
		wipAlert.setGraphic(new ImageView(Media.DIALOG_INFO_WIP.getPath()));
		return wipAlert;
	}

}
