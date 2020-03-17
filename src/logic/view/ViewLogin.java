package logic.view;



import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.view.components.LoginComponent;



public class ViewLogin extends View{
	
	
  	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ScrollPane viewLogin = (ScrollPane) (new LoginComponent()).buildComponent();
		
		logger.log(Level.INFO, viewLogin.toString());
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(viewLogin);
		root.setCenter(vBox);
		return new Scene(root);

	}
  
}
