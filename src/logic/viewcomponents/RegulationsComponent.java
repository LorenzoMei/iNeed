package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class RegulationsComponent extends ViewComponent{
	 Logger loggerR = Logger.getLogger(this.getClass().getName());

		
		@Override
		public Node buildComponent() {
		
			FXMLLoader regulationsLoader = new FXMLLoader();
			ScrollPane regulations = new ScrollPane();
			
			regulationsLoader.setRoot(regulations);
			
			try (FileInputStream src = new FileInputStream("src/logic/view/fxml_regulations.fxml")){
				regulationsLoader.load(src);
			} catch (IOException e) {
	        	loggerR.log(Level.SEVERE,"In RegulationsComponenet " + e.toString());

				return null;
			} 
			
			return regulations;
		}
}
