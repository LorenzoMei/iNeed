package logic.view;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.ViewCheckAnswersComponent;
import logic.view.components.ToolBarComponent;

public class ViewCheckAnswers extends View {

	private int adId;
	private String adType;
	
	public int getAdId() {
		return adId;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane viewCheckAnswers = (ScrollPane) (new ViewCheckAnswersComponent()).buildComponent();
		
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(viewCheckAnswers);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);
		
	}

}
