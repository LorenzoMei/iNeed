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
		FXMLLoader frameLoader = new FXMLLoader();
		BorderPane frame = new BorderPane();
		frameLoader.setRoot(frame);
		try (FileInputStream src = new FileInputStream("src/logic/viewcomponents/fxml_frame.fxml")){
			frameLoader.load(src);
		} catch (IOException e) {
        	logger.log(Level.SEVERE,"In frame component " + e.toString());
			return null;
		} 
		return frame;
	}
}
