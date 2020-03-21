package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class LoginComponent implements ViewComponent{

	Logger loggerL = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
		String toPrintL = "In LoginComponenet ";
		String pathL = "src/logic/view/fxml_login.fxml";
		FXMLLoader loginLoader = new FXMLLoader();
		ScrollPane login = new ScrollPane();
		
		loginLoader.setRoot(login);
		
		try (FileInputStream src = new FileInputStream(pathL)){
			loginLoader.load(src);
		} catch (IOException e) {
        	loggerL.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintL, e.toString()));

			return null;
		} 
		
		return login;
	}
}
