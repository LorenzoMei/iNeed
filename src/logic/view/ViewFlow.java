package logic.view;



import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.beans.OrderedAdsBean;
import logic.view.components.ToolBarComponent;
import logic.view.components.ViewFlowComponent;

public class ViewFlow extends View  {
	
	Logger loggerF = Logger.getLogger(this.getClass().getName()); 
	private OrderedAdsBean orderedAdsBean;

	public void setOrderedAdsBean(OrderedAdsBean orderedAdsBean) {
		this.orderedAdsBean = orderedAdsBean;
	}
	
	public OrderedAdsBean getOrderedAdsBean() {
		return this.orderedAdsBean;
	}
	
	@Override
	public Scene buildScene() {
		
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane viewFlow = (ScrollPane) (new ViewFlowComponent()).buildComponent();
		
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(viewFlow);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);

	}
    
}
