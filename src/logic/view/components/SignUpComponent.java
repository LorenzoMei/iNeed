package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class SignUpComponent implements ViewComponent{
	Logger loggerS = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
		String toPrintS = "In SigunUpComponenet";
		String pathS = "bin/logic/view/fxml_signup.fxml";
		FXMLLoader signupLoader = new FXMLLoader();
		ScrollPane signup = new ScrollPane();
		
		signupLoader.setRoot(signup);
		
		try (FileInputStream src = new FileInputStream(pathS)){
			signupLoader.load(src);
		} catch (IOException e) {
        	loggerS.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintS, e.toString()));

			return null;
		} 
		
		return signup;
	}
}
