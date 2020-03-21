package logic.view.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class FrameComponent extends ViewComponent{
	
 	 Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public Node buildComponent() {
		String toPrintFrame = "In frame component ";
		String pathFrame = "src/logic/viewcomponents/fxml_frame.fxml";
		FXMLLoader frameLoader = new FXMLLoader();
		BorderPane frame = new BorderPane();
		frameLoader.setRoot(frame);
		try (FileInputStream src = new FileInputStream(pathFrame)){
			logger.log(Level.INFO, toPrintFrame + "I'm Openining "+ pathFrame);

			frameLoader.load(src);
		} catch (IOException e) {
        	logger.log(Level.SEVERE, toPrintFrame + e.toString());
			return null;
		} 
		return frame;
	}
}
