package logic.view;

import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.beans.ViewAdBean;
import logic.view.components.AdComponent;
import logic.view.components.ToolBarComponent;

public class ViewAd extends View {
	Logger loggerAd = Logger.getLogger(this.getClass().getName()); 
	private ViewAdBean viewAdBean;

	public ViewAd (ViewAdBean viewAdBean) {
		this.setViewAdBean(viewAdBean);
	}
	
	public ViewAdBean getViewAdBean() {
		return this.viewAdBean;
	}
	public void setViewAdBean(ViewAdBean viewAdBean) {
		this.viewAdBean = viewAdBean;
	}
	
	@Override
	public Scene buildScene() {
		
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane ad = (ScrollPane) (new AdComponent()).buildComponent();
		
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(ad);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);

	}
}
