package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class SignUpComponent extends ViewComponent{
	Logger loggerS = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
	
		FXMLLoader signupLoader = new FXMLLoader();
		ScrollPane signup = new ScrollPane();
		
		signupLoader.setRoot(signup);
		
		try (FileInputStream src = new FileInputStream("src/logic/view/fxml_signup.fxml")){
			signupLoader.load(src);
		} catch (IOException e) {
        	loggerS.log(Level.SEVERE,"In SignUpComponenet " + e.toString());

			return null;
		} 
		
		return signup;
	}
}
