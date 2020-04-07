package logic.view;


import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.ViewMakeAnAdComponent;
import logic.view.components.ToolBarComponent;



public class ViewMakeAnAd extends View  {

	Logger loggerMAA = Logger.getLogger(this.getClass().getName()); 

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane makeAnAd = (ScrollPane) (new ViewMakeAnAdComponent()).buildComponent();
		
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(makeAnAd);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);

	}
	

	 
	
}