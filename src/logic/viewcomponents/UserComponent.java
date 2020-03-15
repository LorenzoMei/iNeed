package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class UserComponent extends ViewComponent{
	 Logger loggerF = Logger.getLogger(this.getClass().getName());

		
	@Override
	public Node buildComponent() {
	
		FXMLLoader viewUserLoader = new FXMLLoader();
		ScrollPane viewUser = new ScrollPane();
		
		viewUserLoader.setRoot(viewUser);
		
		try (FileInputStream src = new FileInputStream("src/logic/view/fxml_user.fxml")){
			viewUserLoader.load(src);
		} catch (IOException e) {
       	loggerF.log(Level.SEVERE,"In ViewUserComponenet " + e.toString());

			return null;
		} 
		
		return viewUser;
	}
}
