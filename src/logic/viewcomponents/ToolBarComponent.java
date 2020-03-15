package logic.viewcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;


public class ToolBarComponent extends ViewComponent {
	 Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public Node buildComponent() {
		FXMLLoader toolBarLoader = new FXMLLoader();
		ToolBar toolBar = new ToolBar();
		toolBarLoader.setRoot(toolBar);
		try (FileInputStream src = new FileInputStream("src/logic/viewcomponents/fxml_toolbar.fxml")){
			toolBarLoader.load(src);
		} catch (IOException e) {
        	logger.log(Level.SEVERE,"In ToolbarComponenet " + e.toString());

			return null;
		} 
		return toolBar;
	}
}
