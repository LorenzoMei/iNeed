package logic.view;



import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.RegulationsComponent;
import logic.view.components.ToolBarComponent;

import java.util.logging.Logger;

public class ViewRegulations  extends View  {

	Logger loggerR = Logger.getLogger(this.getClass().getName()); 

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane regulations = (ScrollPane) (new RegulationsComponent()).buildComponent();
		
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(regulations);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);

	}
	
	
}