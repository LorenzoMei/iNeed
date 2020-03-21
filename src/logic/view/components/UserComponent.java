package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class UserComponent extends ViewComponent{
	 Logger loggerU = Logger.getLogger(this.getClass().getName());

		
	@Override
	public Node buildComponent() {
		String toPrintU = "In ViewUserComponenet";
		String pathU = "src/logic/view/fxml_user.fxml";
		FXMLLoader viewUserLoader = new FXMLLoader();
		ScrollPane viewUser = new ScrollPane();
		
		viewUserLoader.setRoot(viewUser);
		
		try (FileInputStream src = new FileInputStream(pathU)){
			viewUserLoader.load(src);
		} catch (IOException e) {
       	loggerU.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintU, e.toString()));

			return null;
		} 
		
		return viewUser;
	}
}
