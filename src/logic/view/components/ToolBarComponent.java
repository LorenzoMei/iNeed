package logic.view.components;

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
		String toPrintTB = "In ToolbarComponenet ";
		String pathTB = "src/logic/view/components/fxml_toolbar.fxml";
		FXMLLoader toolBarLoader = new FXMLLoader();
		ToolBar toolBar = new ToolBar();
		toolBarLoader.setRoot(toolBar);
		try (FileInputStream src = new FileInputStream(pathTB)){
			logger.log(Level.INFO, toPrintTB + "I'm Openining "+ pathTB);
			toolBarLoader.load(src);
		} catch (IOException e) {
        	logger.log(Level.SEVERE, toPrintTB + e.toString());

			return null;
		} 
		return toolBar;
	}
}
