package logic.view.components;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.beans.PublishAnAdBean;
import logic.publishanad.PublishAnAdController;
import logic.view.Context;
import logic.view.MSG;
import logic.view.View;
import logic.view.ViewFlow;
import logic.view.ViewMakeAnAd;
import logic.view.ViewRegulations;


public class ViewMakeAnAdController implements Initializable {
	 @FXML private TextField typeTextField;
	 @FXML private TextField othersTextField;
	 @FXML private TextField titleTextField;
	 @FXML private MenuButton typeButton;
	 @FXML private MenuButton categoryButton;
	 @FXML private TextArea bodyTextArea;
	 String formError = MSG.ERROR_FORM.getMsg();
	 @FXML private Text actionPrinte;
	 @FXML private AnchorPane grid;
	 @FXML private MenuItem profileName;
     PublishAnAdBean adBean = new PublishAnAdBean();
     PublishAnAdController controller = PublishAnAdController.getInstance();
     private static String type;
     private static String category;
     private static boolean other = false;
     
     Image logo = new Image(getClass().getResourceAsStream("/media/Red-Logomark.png"));

 	 private View nextViewM;

	 
	 Logger logger = Logger.getLogger(this.getClass().getName());

	 public void initialize(URL locationAd, ResourceBundle resourcesAD) {
		 titleTextField.setPromptText("*Es. I need a bed this weekend...");
		 bodyTextArea.setPromptText("*Es. I need a bed this weekend after 3 pm, It's good even if room is shared... ");
		 othersTextField.setPromptText("If you choose 'Other' above, you can specifie here...");
		 othersTextField.setEditable(false);
		 String status = "My location " + locationAd + " my resoursources: " + resourcesAD;
		 logger.log(Level.SEVERE, status);
		}
	 
	 
	 public static void setType(String type) {
	 		ViewMakeAnAdController.type = type;

	 	}
	 
	 
	     public static String getType() {
	    	 return ViewMakeAnAdController.type;
	     }
	     
	     public static void setOther(boolean other) {
	    	 ViewMakeAnAdController.other = other;

		  	}
	     
	     public static boolean getOther() {
	     	 return ViewMakeAnAdController.other;
	     }
	     
	     
	     public static void setCategory(String category) {
	    	 ViewMakeAnAdController.category = category;

	  	}
	     
	     public static String getCategory() {
	     	 return ViewMakeAnAdController.category;
	    }
	     public void  setOtherField() {
	    	 othersTextField.setText("");
			 othersTextField.setEditable(false);
	     }

	 @FXML protected void handleSubmitButtonRequest(ActionEvent event) {
		 ViewMakeAnAdController.setType("Richiesta");
		 typeButton.setText("Request");
		 
	 }
	 
	 @FXML protected void handleSubmitButtonOffer(ActionEvent event) {
		 ViewMakeAnAdController.setType("Offerta");
		 typeButton.setText("Offer");

	 }
	 
	 @FXML protected void handleSubmitButtonElectronics(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Electronics");
		 categoryButton.setText("Electronics");
		 setOtherField();

	 }
	 
	 @FXML protected void handleSubmitButtonHydraulic(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Hydraulic");
		 categoryButton.setText("Hydraulic");
		 setOtherField();
	 }
	 
	 @FXML protected void handleSubmitButtonGardering(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Gardering");
		 categoryButton.setText("Gardering");
		 setOtherField();
	 }
	 
	 @FXML protected void handleSubmitButtonInformatic(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Informatic");
		 categoryButton.setText("Informatic");
		 setOtherField();
	 }
	 
	 @FXML protected void handleSubmitButtonBed(ActionEvent event) {
		 ViewMakeAnAdController.setCategory("Bed sharing");
		 categoryButton.setText("Bed sharing");
		 setOtherField();

	 }
	 
	 @FXML protected void handleSubmitButtonOther(ActionEvent event) {
		 categoryButton.setText("Other");
		 othersTextField.setEditable(true);
		 ViewMakeAnAdController.setOther(true);
	 }
	 
	 
	 @FXML protected void handleSubmitButtonSend(ActionEvent event)  {
		 actionPrinte.setText("");
		 String printThat = "il titolo e': " + ViewMakeAnAdController.getType();
		 logger.log(Level.INFO,  printThat );
		 
		 if(ViewMakeAnAdController.getType() == null || ViewMakeAnAdController.getCategory() == null) {
				logger.log(Level.INFO,  "Eh si sono proprio un null " );
	     		showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "insert a type and category please!");
	            return;
	     }

		 else if(titleTextField.getText().isEmpty() || bodyTextArea.getText().isEmpty()) {
     		showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), formError, "Insert a title and body please!");
            return;
     	}
		
	     
		 else {
			 if(!other) {
			     adBean.setCategory(ViewMakeAnAdController.getCategory());   	 
		     }
		     
		     else {
		    	 adBean.setCategory(othersTextField.getText());
		     }
			 	
			 String printThat2 = "il titolo e': " + ViewMakeAnAdController.getType();
			 logger.log(Level.INFO,  printThat2 );
	
		     
		     if (ViewMakeAnAdController.getType().compareTo("Offerta") == 0) {
				 adBean.setOfferType();

		     }
		     else if (ViewMakeAnAdController.getType().compareTo("Richiesta") == 0){
				 adBean.setRequestType();
		     }
			 logger.log(Level.INFO,  "Dopo setType" );

		     adBean.setTitle(titleTextField.getText());
			 logger.log(Level.INFO,  "Dopo setTitle" );

		     adBean.setBody(bodyTextArea.getText());
			 logger.log(Level.INFO,  "Dopo setBody" );

//		     nextViewM = (View) new ViewMakeAnAd();

		     adBean.setOwnerUsername(Context.getReference().getCurrentView().getProfileName());
			 logger.log(Level.INFO,  "Dopo setOwnerUsername" );

		    
				try {
					controller.createAd(adBean);
			        displayDialog();
					
				} catch (IllegalAccessException | InvocationTargetException e) {
					
					logger.log(Level.SEVERE, String.format(" %s  Error in ViewMakeAnAd", e.toString()) );
				}
				
				
		 }
		 
		 
		
	     
	     actionPrinte.setText("Ad Posted!");
	 }
	 
	 	
	  

	    
	 public  void displayDialog() {
	 
		 	String style = "Tahoma";
		 
	        Stage stage = new Stage();
	        
	        GridPane gridNewWindow = new GridPane();
	        gridNewWindow.setAlignment(Pos.CENTER);
	        gridNewWindow.setHgap(10);
	        gridNewWindow.setVgap(10);
	        gridNewWindow.setPadding(new Insets(10, 10, 10, 10));

	       
	        
	        stage.setTitle("Well Done!");

	        Button vFBtn = new Button("Go To ViewFlow");
	        Button mAABtn = new Button("Create a new Ad!");
	        Text textSuccess = new Text("Your Ad was successfully added!");
	        textSuccess.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
	        textSuccess.setFill(Color.CHARTREUSE);
	        textSuccess.setStrokeWidth(0.5); 
	        textSuccess.setStroke(Color.BLUE); 
	        textSuccess.setTextAlignment(TextAlignment.CENTER);
	        GridPane.setConstraints(textSuccess, 1, 0);
	        GridPane.setHalignment(textSuccess, HPos.CENTER);
	        gridNewWindow.getChildren().add(textSuccess);
	        
	        Text textOr = new Text("Or");
	        textOr.setTextAlignment(TextAlignment.CENTER);
	        textOr.setFont(Font.font(style, FontWeight.BOLD, 16));
	        GridPane.setConstraints(textOr, 1, 6);
	        GridPane.setHalignment(textOr, HPos.CENTER);
	        gridNewWindow.getChildren().add(textOr);

	        final Separator sepHoriz1 = new Separator();
	        sepHoriz1.setOrientation(Orientation.HORIZONTAL);
	        sepHoriz1.setValignment(VPos.CENTER);
	        sepHoriz1.setPrefHeight(30);
	        GridPane.setConstraints(sepHoriz1, 0, 6);
	        GridPane.setRowSpan(sepHoriz1, 2);
	        gridNewWindow.getChildren().add(sepHoriz1);
	        
	        final Separator sepHoriz2 = new Separator();
	        sepHoriz2.setOrientation(Orientation.HORIZONTAL);
	        sepHoriz2.setValignment(VPos.CENTER);
	        sepHoriz2.setPrefHeight(80);
	        GridPane.setConstraints(sepHoriz2, 2, 6);
	        GridPane.setRowSpan(sepHoriz2, 2);
	        gridNewWindow.getChildren().add(sepHoriz2);
	        
	        final Separator sepHoriz3 = new Separator();
	        sepHoriz3.setOrientation(Orientation.HORIZONTAL);
	        sepHoriz3.setValignment(VPos.CENTER);
	        sepHoriz3.setPrefHeight(80);
	        GridPane.setConstraints(sepHoriz3, 1, 6);
	        GridPane.setRowSpan(sepHoriz3, 2);
	        gridNewWindow.getChildren().add(sepHoriz3);
	        
	        final Separator sepHoriz4 = new Separator();
	        sepHoriz4.setOrientation(Orientation.HORIZONTAL);
	        sepHoriz4.setValignment(VPos.CENTER);
	        sepHoriz4.setPrefHeight(80);
	        GridPane.setConstraints(sepHoriz4, 1, 5);
	        GridPane.setRowSpan(sepHoriz4, 2);
	        gridNewWindow.getChildren().add(sepHoriz4);
	        

	        Text textVF = new Text("Check Your ad! Click below!");
	        textVF.setFont(Font.font(style, FontWeight.NORMAL, 14));
	        textVF.setTextAlignment(TextAlignment.CENTER);
	        vFBtn.setAlignment(Pos.CENTER);
	        GridPane.setConstraints(textVF, 1, 4);
	        GridPane.setHalignment(textVF, HPos.CENTER);
	        gridNewWindow.getChildren().add(textVF);
	        
	        GridPane.setConstraints(vFBtn, 1, 5);
	        GridPane.setHalignment(vFBtn, HPos.CENTER);
	        gridNewWindow.getChildren().add(vFBtn);
	        
	        
	        Text textMAA = new Text("If You want to create another one, go on!");
	        textMAA.setTextAlignment(TextAlignment.CENTER);
	        textMAA.setFont(Font.font(style, FontWeight.NORMAL, 14));
	        mAABtn.setAlignment(Pos.CENTER);
	        GridPane.setConstraints(textMAA, 1, 7);
	        GridPane.setHalignment(textMAA, HPos.CENTER);
	        gridNewWindow.getChildren().add(textMAA);
	        
	        GridPane.setConstraints(mAABtn, 1, 8);
	        GridPane.setHalignment(mAABtn, HPos.CENTER);
	        gridNewWindow.getChildren().add(mAABtn);
	        
	        final ImageView logo1 = new ImageView(logo);
	        logo1.setFitHeight(70);
	        logo1.setFitWidth(70);
	        VBox vBox = new VBox();
			vBox.setMaxSize(30, 30);
			vBox.getChildren().addAll(logo1);
	        GridPane.setHalignment(vBox, HPos.CENTER);
	        GridPane.setConstraints(vBox, 1, 10);
	        gridNewWindow.getChildren().add(vBox);
	        gridNewWindow.setStyle("-fx-background-color:  #d5e8f5");
	        
	        EventHandler<ActionEvent> handlerViewFlow = evt -> {	                
	            View nextView =  new ViewFlow();
	            nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
	            Context.getReference().getCurrentView().setNextView(nextView);
	            Context.getReference().goNext();
		        stage.close();
  
		    };
		    
		    EventHandler<ActionEvent> handlerMakeAnAd = evt -> {

                View nextView =  new ViewMakeAnAd();
            	Context.getReference().getCurrentView().setNextView(nextView);
	            nextView.setProfileName(Context.getReference().getCurrentView().getProfileName());
            	Context.getReference().goNext();
            	stage.close();

		    };
		    vFBtn.setOnAction(handlerViewFlow);
		    mAABtn.setOnAction(handlerMakeAnAd);
		    
		    Scene scene = new Scene(gridNewWindow, 500, 375);
	        stage.setScene(scene);
	        stage.show();
		}
	 
	 

	@FXML protected void handleSubmitButtonRegulations(ActionEvent event) {
		 actionPrinte.setText("");
	     actionPrinte.setText("go toRegulations");
	     nextViewM = (View) new ViewRegulations();
         nextViewM.setProfileName(Context.getReference().getCurrentView().getProfileName());
        Context.getReference().getCurrentView().setNextView(nextViewM);
    	Context.getReference().goNext();
	     
	 }
	 
	 protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
}
