package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class ViewMapComponent extends ViewComponent {
	Logger loggerM = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
		String toPrintM = "In ViewMapComponent ";
		String pathM = "src/logic/view/fxml_map.fxml";
		FXMLLoader viewMapwLoader = new FXMLLoader();
		ScrollPane viewMap = new ScrollPane();
		
		viewMapwLoader.setRoot(viewMap);
		
		try (FileInputStream src = new FileInputStream(pathM)){
			loggerM.log(Level.INFO, toPrintM + "I'm Openining "+ pathM);
			viewMapwLoader.load(src);
		} catch (IOException e) {
        	loggerM.log(Level.SEVERE, toPrintM + e.toString());

			return null;
		} 
		
		return viewMap;
	}
}
