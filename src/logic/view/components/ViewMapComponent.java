package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class ViewMapComponent implements ViewComponent {
	Logger loggerM = Logger.getLogger(this.getClass().getName());

	
	@Override
	public Node buildComponent() {
		String toPrintM = "In ViewMapComponent ";
		String pathM = "src/logic/view/fxml_map.fxml";
		FXMLLoader viewMapwLoader = new FXMLLoader();
		ScrollPane viewMap = new ScrollPane();
		
		viewMapwLoader.setRoot(viewMap);
		
		try (FileInputStream src = new FileInputStream(pathM)){
			viewMapwLoader.load(src);
		} catch (IOException e) {
        	loggerM.log(Level.SEVERE, String.format(" %s The erro says:  %s", toPrintM, e.toString()));

			return null;
		} 
		
		return viewMap;
	}
}
