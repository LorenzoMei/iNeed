package logic.view;
 
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.viewcomponents.SignUpComponent;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class ViewSignUp extends View {
	
    
    Logger logger = Logger.getLogger(this.getClass().getName());

    public  ViewSignUp() {

    }

	@Override
	public Scene buildScene() {
		BorderPane  root = new BorderPane();
		ScrollPane viewSignUp = (ScrollPane) (new SignUpComponent()).buildComponent();
		
		logger.log(Level.INFO, viewSignUp.toString());
		VBox vBox = new VBox();
		vBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vBox.getChildren().addAll(viewSignUp);
		root.setCenter(vBox);
		return new Scene(root);
	}
}