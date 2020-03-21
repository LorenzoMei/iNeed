package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class MakeAnAdComponent extends ViewComponent {

	 Logger loggerMAA = Logger.getLogger(this.getClass().getName());

		
		@Override
		public Node buildComponent() {
			String toPrintMAA = "In MakeAnAdComponenet " ;
			String pathMAA = "src/logic/view/fxml_makeanad.fxml";
			FXMLLoader makeAnAdLoader = new FXMLLoader();
			ScrollPane makeAnAd = new ScrollPane();
			
			makeAnAdLoader.setRoot(makeAnAd);
			
			try (FileInputStream src = new FileInputStream(pathMAA)){
				makeAnAdLoader.load(src);
			} catch (IOException e) {
	        	loggerMAA.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintMAA, e.toString()));

				return null;
			} 
			
			return makeAnAd;
		}
}
