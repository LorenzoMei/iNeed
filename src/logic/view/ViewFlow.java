package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
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
    	this.setNext("logic.view.ViewFlow");
	}
	
	
	 @FXML private Text actionCancel;
	 @FXML private TextField searchTextField;
	 @FXML private PasswordField passwordTextField;
	 @FXML private GridPane grid;
	 
	 public void initialize(URL location, ResourceBundle resources) {
		 searchTextField.setPromptText("*Es. Meccanico auto");
		}
	 
	
	 @FXML protected void handleSubmitButtonUpdate(ActionEvent event) {
 		Context.getReference().goNext();
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
	public void goNext() {
		View nextView;
		try {
			nextView = (View) Class.forName(this.getNext()).newInstance();
			nextView.setPrevious(this);
			Context.getReference().setCurrentView(nextView);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
}
