package logic.view;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Window;



public class ViewFlow extends View implements Initializable {
	
	public ViewFlow() {
		this.setFXMLPath("fxml_flow.fxml");
	}
	
	 String formError = "FORM ERROR!";
	 @FXML private Text actionCancel;
	 @FXML private TextField searchTextField;
	 @FXML private PasswordField passwordTextField;
	 @FXML private GridPane grid;
	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 searchTextField.setPromptText("*Es. Meccanico auto");
		}
	 
	
	 @FXML protected void handleSubmitButtonViewMap(ActionEvent event) {
	 		 actionCancel.setText("");
		     actionCancel.setText("vado su map");
          	 logger.log(Level.SEVERE, "Print this when viewMap is clicked " + getNext());
	    	 Context.getReference().goNext("logic.view.ViewMap");

		 }
	 
	 @FXML protected void handleSubmitButtonUpdate(ActionEvent event) {
 		Context.getReference().goNext("logic.view.ViewFlow");

 		 actionCancel.setText("");
	     actionCancel.setText("pagina aggiornata");
	 }

	@FXML protected void handleSubmitButtonUser(ActionEvent event) {
	        actionCancel.setText("");
	        actionCancel.setText("Utente");
	        
	}
    
    
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    
    @Override
	public void goNext(String viewName) {
		View nextView;
		try {
//			p
			System.out.println("ViewFlow: attempting to set nextState as "+viewName);
			nextView = (View) Class.forName(viewName).newInstance();
//			p
			System.out.println("ViewFlow: nextView is "+nextView.getClass().getSimpleName());
			nextView.setPrevious(this);
			
			Context.getReference().setCurrentView(nextView);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.toString() + " Error in goNext");

		}

	}
    
}
