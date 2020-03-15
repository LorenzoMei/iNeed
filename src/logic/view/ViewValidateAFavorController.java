package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;


public class ViewValidateAFavorController  implements Initializable {
	

	
	Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void initialize(URL urlVAF, ResourceBundle resourceBundleVAF) {
		 String status = "My location " + urlVAF + " my resoursources: " + resourceBundleVAF;
		 logger.log(Level.INFO, status);
		 


    }

}
