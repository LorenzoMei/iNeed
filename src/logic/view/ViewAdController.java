package logic.view;

import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import logic.beans.ViewAdBean;

public class ViewAdController implements Initializable {
	
	@FXML private Text actionPrinterAd;
	@FXML private Text textDateStart;
	@FXML private Text textBody;
	@FXML private Text textTtile;
	@FXML private Hyperlink hLAuthor;
	@FXML private Text textDateExpire;
	@FXML private Text textType;

	
	
	Logger logger = Logger.getLogger(this.getClass().getName());


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	 ViewAd currentView = (ViewAd) Context.getReference().getCurrentView();
    	 ViewAdBean beanAd = currentView.getViewAdBean();
		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
		 
		 logger.log(Level.INFO, "the Title is: " +  beanAd.getTitle());

		 textTtile.setText(beanAd.getTitle());
		 textType.setText(beanAd.getType());
		 textDateStart.setText(DateFormat.getDateInstance().format(beanAd.getDateOfPublication().getTime()));
		 textBody.setText(beanAd.getBody());
		 hLAuthor.setText(beanAd.getAuthor());
    }
    
    @FXML protected void handleSubmitButtonCandidate(ActionEvent event) {
    	actionPrinterAd.setText("");

    	actionPrinterAd.setText("Clicked Candidate");
       }
    
    @FXML protected void handleSubmitHLAuthor(ActionEvent event) {
    	actionPrinterAd.setText("");
    	actionPrinterAd.setText("Clicked Author's Name");
       }
	
}
