package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class LoginComponent extends ViewComponent{

	Logger loggerL = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
	
		FXMLLoader loginLoader = new FXMLLoader();
		ScrollPane login = new ScrollPane();
		
		loginLoader.setRoot(login);
		
		try (FileInputStream src = new FileInputStream("src/logic/view/fxml_login.fxml")){
			loginLoader.load(src);
		} catch (IOException e) {
        	loggerL.log(Level.SEVERE,"In LoginComponenet " + e.toString());

			return null;
		} 
		
		return login;
	}
}
