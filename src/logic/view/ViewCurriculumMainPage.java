package logic.view;


import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.CurriculumMainPageCompononent;
import logic.view.components.ToolBarComponent;


public class ViewCurriculumMainPage extends View {

	Logger loggerCMP = Logger.getLogger(this.getClass().getName()); 

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ToolBar toolBar = (ToolBar) (new ToolBarComponent()).buildComponent();
		ScrollPane curriculumMainPage = (ScrollPane) (new CurriculumMainPageCompononent()).buildComponent();
		
		
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(curriculumMainPage);
		root.setTop(toolBar);
		root.setCenter(vBox);
		return new Scene(root);

	}


}
