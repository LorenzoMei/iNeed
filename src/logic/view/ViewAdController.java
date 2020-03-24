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
import logic.beans.OrderedAdsBean;
import logic.viewanad.ViewAnAdController;

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
    	OrderedAdsBean beanAd = new OrderedAdsBean();

		 String status = "My location " + url + " my resoursources: " + resourceBundle;
		 logger.log(Level.INFO, status);
		 beanAd.setOrderByTime();
		 ViewAnAdController.getReference().listAllAds(beanAd);

		 
		 logger.log(Level.INFO, "the Title is: " +  beanAd.getTitle(1));

		 textTtile.setText(beanAd.getTitle(0));
		 textType.setText(beanAd.getType(0));
		 textDateStart.setText(DateFormat.getDateInstance().format(beanAd.getDateOfPublication(0).getTime()));
		 textBody.setText(beanAd.getBody(0));
		 hLAuthor.setText(beanAd.getOwner(0));
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
