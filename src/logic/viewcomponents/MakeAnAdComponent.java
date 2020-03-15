package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class MakeAnAdComponent extends ViewComponent {

	 Logger loggerF = Logger.getLogger(this.getClass().getName());

		
		@Override
		public Node buildComponent() {
		
			FXMLLoader makeAnAdLoader = new FXMLLoader();
			ScrollPane makeAnAd = new ScrollPane();
			
			makeAnAdLoader.setRoot(makeAnAd);
			
			try (FileInputStream src = new FileInputStream("src/logic/view/fxml_makeanad.fxml")){
				makeAnAdLoader.load(src);
			} catch (IOException e) {
	        	loggerF.log(Level.SEVERE,"In MakeAnAdComponenet " + e.toString());

				return null;
			} 
			
			return makeAnAd;
		}
}
