package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class ViewValidateAFavorComponent extends ViewComponent {
	 Logger loggerV = Logger.getLogger(this.getClass().getName());

		
	@Override
	public Node buildComponent() {
	
		FXMLLoader viewValidateAFavorLoader = new FXMLLoader();
		ScrollPane viewValidateAFavor = new ScrollPane();
		
		viewValidateAFavorLoader.setRoot(viewValidateAFavor);
		
		try (FileInputStream src = new FileInputStream("src/logic/view/fxml_validateafavor.fxml")){
			viewValidateAFavorLoader.load(src);
		} catch (IOException e) {
      	loggerV.log(Level.SEVERE,"In ViewUserComponenet " + e.toString());

			return null;
		} 
		
		return viewValidateAFavor;
	}
}
