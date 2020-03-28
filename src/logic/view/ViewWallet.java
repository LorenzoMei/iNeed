package logic.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.ToolBarComponent;
import logic.view.components.WalletComponent;

public class ViewWallet extends View{
	
	 Logger loggerW = Logger.getLogger(this.getClass().getName());

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane viewWallet = (ScrollPane) (new WalletComponent()).buildComponent();
		loggerView.log(Level.INFO,"In ViewWallet");
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(viewWallet);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);

	}
		
}
