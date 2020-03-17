package logic.view;



import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.ToolBarComponent;
import logic.view.components.ViewMapComponent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewMap  extends View  {
 	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public Scene buildScene() {

		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane viewMap = (ScrollPane) (new ViewMapComponent()).buildComponent();
		logger.log(Level.INFO,"In ViewMap");
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(viewMap);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);
	}

	

}
