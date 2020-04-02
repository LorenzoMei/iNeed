package logic.view;

import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import logic.answeranad.AnswerAnAdController;
import logic.answeranad.UserAlreadyAnsweredException;
import logic.beans.AnswerAnAdBean;
import logic.beans.ViewAdBean;

public class ViewAdController implements Initializable {
	
	
	@FXML private Text actionPrinterAd;
	@FXML private Text textDateStart;
	@FXML private Text textBody;
	@FXML private Text textTtile;
	@FXML private Hyperlink hLAuthor;
	@FXML private Text textDateExpire;
	@FXML private Text textType;
	@FXML private Button bActionOnAd;
	@FXML private Text tAboveBActionOnAd;

	
	
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
		 String actionOnAd;
		 if (Context.getReference().getCurrentView().getProfileName().compareTo(beanAd.getAuthor()) == 0) {
			 actionOnAd = BLabels.CHECKANSWERS.getLabel();
		 }
		 else {
			 actionOnAd = BLabels.ANSWER.getLabel();
		 }
		 this.bActionOnAd.setText(actionOnAd);
		 this.tAboveBActionOnAd.setText(this.tAboveBActionOnAd.getText() + actionOnAd);
    }
    
    @FXML protected void handleBActionOnAd(ActionEvent event) {
    	actionPrinterAd.setText("");
    	actionPrinterAd.setText("Clicked button: " + ((Button)event.getSource()).getText());
		ViewAd currentView = (ViewAd) Context.getReference().getCurrentView();
    	if (((Button) event.getSource()).getText().compareTo(BLabels.ANSWER.getLabel()) == 0) {
    		logger.log(Level.INFO, "answerAnAd UC initiated");
    		AnswerAnAdBean bean = new AnswerAnAdBean();
    		bean.setId(currentView.getViewAdBean().getId());
    		bean.setType(currentView.getViewAdBean().getType());
    		bean.setUsername(currentView.getProfileName());
    		try {
				AnswerAnAdController.getInstance().answer(bean);
				ImageView ivSuccess = new ImageView();
				ivSuccess.setImage(new Image(Media.DIALOG_INFO_COMPLETEDTASK.getPath()));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setGraphic(ivSuccess);
				alert.setTitle(MSG.INFO_SUCCESS.getMsg());
				alert.setHeaderText("Your answer was successfully sent. Hooray!");
				alert.setContentText(String.format("You succefully submitted your answer to the owner of the ad, \n who will contact you if he wants to trade favors with you"));
				alert.getDialogPane().getScene().getWindow().sizeToScene();
				alert.showAndWait();
			} catch (UserAlreadyAnsweredException e) {
				logger.log(Level.WARNING, String.format("user %s has already answered to ad %s%s", currentView.getProfileName(), currentView.getViewAdBean().getType(), currentView.getViewAdBean().getId()));
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(MSG.ERROR_ALREADY_ANSWERED.getMsg());
				alert.setHeaderText("You can't answer more than one time to the same ad");
				alert.setContentText(String.format("You already answered to this ad in date: %s", DateFormat.getDateInstance().format(e.getDate().getTime())));
				alert.showAndWait();
			}
    		
    	}
    	else if (((Button) event.getSource()).getText().compareTo(BLabels.CHECKANSWERS.getLabel()) == 0) {
    		logger.log(Level.INFO, "checkAnswers UC initiated");
    		ViewCheckAnswers nextView = new ViewCheckAnswers();
    		nextView.setAdId(currentView.getViewAdBean().getId());
    		nextView.setAdType(currentView.getViewAdBean().getType());
    		nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
    		Context.getReference().getCurrentView().setNextView(nextView);
    		Context.getReference().goNext();
    	}
    }
    
    @FXML protected void handleSubmitHLAuthor(ActionEvent event) {
    	actionPrinterAd.setText("");
    	actionPrinterAd.setText("Clicked Author's Name");
       }
	
}
