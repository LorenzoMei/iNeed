package logic.view;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import logic.beans.OrderedAdsBean;
import logic.beans.ViewAdBean;
import logic.viewanad.ViewAnAdController;


public class ViewFlowController implements Initializable{


	 private View nextViewF;
	 String formError = MSG.ERROR_FORM.getMsg();
	 @FXML private Text actionCancelFlow;
	 @FXML private TextField searchTextField;
	 @FXML private GridPane grid;
	 @FXML private TableView<AdDetailsBean> tvFlow;
	 @FXML private TableColumn<AdDetailsBean, String> tcType;
	 @FXML private TableColumn<AdDetailsBean, String> tcTitle;
	 @FXML private TableColumn<AdDetailsBean, String> tcAuthor;
	 @FXML private TableColumn<AdDetailsBean, String> tcCategory;
	 @FXML private TableColumn<AdDetailsBean, String> tcDate;
	 @FXML private TableColumn<AdDetailsBean, Boolean> tcShowMoreDetails;
	 @FXML private Button bUpdate;
	 
     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	public class ShowMoreDetailsCell extends TableCell<AdDetailsBean, Boolean>{
		
		public class BShowMoreDetailsHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {

				logger.log(Level.INFO, "button bContactUser clicked");
//				AdDetailsBean chosen = tvFlow.getItems().get(getIndex());
				ViewFlow currentView = (ViewFlow) Context.getReference().getCurrentView();
				logger.log(Level.INFO, 
						String.format("clicked on ad in row %d with values: %s %s %s %s %s %s",
								getIndex(),
								currentView.getOrderedAdsBean().getOwner(getIndex()),
								currentView.getOrderedAdsBean().getBody(getIndex()),
								currentView.getOrderedAdsBean().getCategory(getIndex()),
								currentView.getOrderedAdsBean().getDateOfPublication(getIndex()), 
								currentView.getOrderedAdsBean().getTitle(getIndex()),
								currentView.getOrderedAdsBean().getType(getIndex())
						)
				);
				nextViewF = (View) new ViewAd(
						new ViewAdBean(
								currentView.getOrderedAdsBean().getTitle(getIndex()),
								currentView.getOrderedAdsBean().getType(getIndex()),
								currentView.getOrderedAdsBean().getDateOfPublication(getIndex()),
								currentView.getOrderedAdsBean().getBody(getIndex()),
								currentView.getOrderedAdsBean().getOwner(getIndex()),
								currentView.getOrderedAdsBean().getId(getIndex())
						)
				);
				nextViewF.setProfileName(Context.getReference().getCurrentView().getProfileName());
				Context.getReference().getCurrentView().setNextView(nextViewF);
				Context.getReference().goNext();
			}
		}
		
		final Button bShowMoreDetails = new Button(BLabels.SHOWDETAILS.getLabel());
		final StackPane spPaddedButton = new StackPane();
		
		ShowMoreDetailsCell(){
			spPaddedButton.setPadding(new Insets(3));
			spPaddedButton.getChildren().add(bShowMoreDetails);
			this.bShowMoreDetails.setOnAction(new BShowMoreDetailsHandler());
			
		}

		@Override
		protected void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				this.setGraphic(spPaddedButton);
			}
			else {
				setGraphic(null);
			}
		}
		
	}
	public class isRowEmptyCallback implements Callback<TableColumn.CellDataFeatures<AdDetailsBean, Boolean>, ObservableValue<Boolean>>{

		@Override
		public ObservableValue<Boolean> call(CellDataFeatures<AdDetailsBean, Boolean> features) {
			return new SimpleBooleanProperty(features.getValue() != null);
		}
	 }
	 public class addButtonToRowCallback implements Callback<TableColumn<AdDetailsBean, Boolean>, TableCell<AdDetailsBean, Boolean>>{

		@Override
		public TableCell<AdDetailsBean, Boolean> call(TableColumn<AdDetailsBean, Boolean> tvFlowRowBooleanTableColumn) {
			return new ShowMoreDetailsCell();
		}
		 
	 }
	
	
	
	public ViewFlowController() {
		this.textInputFields = new ArrayList<>();

	}
	
	 
	 
	 public void initialize(URL locationflow, ResourceBundle resourcesflow) {
		 tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
		 tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		 tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
		 tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		 tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		 tcShowMoreDetails.setCellValueFactory(new isRowEmptyCallback());
		 tcShowMoreDetails.setCellFactory(new addButtonToRowCallback());
		 String status = "My location " + locationflow + " my resoursources: " + resourcesflow;
		 logger.log(Level.INFO, status);
	     logger.log(Level.INFO, "In viewFlow the name of the user is: " + Context.getReference().getCurrentView().getProfileName());
		 searchTextField.setPromptText("*Es. Meccanico auto");
		 this.bUpdate.fire();
		}
	 
	 
	 
	 public TextField getSearchTextField() {
			return searchTextField;
		}
		
	 
	 @FXML protected void handleSubmitButtonSearch(ActionEvent event) {
 		actionCancelFlow.setText("");
 		CheckEmptyField check = new CheckEmptyField();
    	check.populateTextInputFields(this);
    	this.textInputFields = check.getTextInputFields();
    	logger.log(Level.INFO, "Size is " + textInputFields.size());

		for(int i = 0; i < textInputFields.size(); i++) {
		        	
		        	logger.log(Level.INFO, textInputFields.get(i).getClass().getSimpleName());
		        	if(textInputFields.get(i).getText().isEmpty()) {
			        	logger.log(Level.INFO, "textfield is : '" + textInputFields.get(i).getText() + "'");
			     		actionCancelFlow.setText("No text Inserted, try again");
			            return;			        	
		
		        	}
		}
 		actionCancelFlow.setText("new search!");
	 }
	  
	 @FXML protected void handleSubmitButtonUpdate(ActionEvent event) {		 		 
		 ViewFlow currentView= (ViewFlow) Context.getReference().getCurrentView();
		 currentView.setOrderedAdsBean(new OrderedAdsBean());
		 currentView.getOrderedAdsBean().setOrderByTime();
		 ViewAnAdController.getReference().listAllAds(currentView.getOrderedAdsBean());
		 List<AdDetailsBean> rowsBuffer = new ArrayList<>();
		 for (int i = 0; i < currentView.getOrderedAdsBean().getNumOfAds(); i ++) {
			 AdDetailsBean currentRow = new AdDetailsBean();
			 
			 if (currentView.getOrderedAdsBean().getOwner(i) != null) {
				 currentRow.setAuthor(currentView.getOrderedAdsBean().getOwner(i));
			 }
			 if (currentView.getOrderedAdsBean().getTitle(i) != null) {
				 currentRow.setTitle(currentView.getOrderedAdsBean().getTitle(i));
			 }
			 if (currentView.getOrderedAdsBean().getType(i)!= null){
				 currentRow.setType(currentView.getOrderedAdsBean().getType(i));
			 }
			 if (currentView.getOrderedAdsBean().getCategory(i) != null) {
				 currentRow.setCategory(currentView.getOrderedAdsBean().getCategory(i));
			 }
			 if (currentView.getOrderedAdsBean().getDateOfPublication(i) != null) {
				 currentRow.setDate(DateFormat.getDateInstance().format(currentView.getOrderedAdsBean().getDateOfPublication(i).getTime()));
			 }
			
			 rowsBuffer.add(currentRow);
		 }
		 ObservableList<AdDetailsBean> rows = FXCollections.observableList(rowsBuffer);
		 this.tvFlow.setItems(rows);
 		 actionCancelFlow.setText("");
	     actionCancelFlow.setText("pagina aggiornata");
	 }
	 

	 
	 @FXML protected void handleSubmitButtonHelp(ActionEvent event) {
	 		 actionCancelFlow.setText("");
		     actionCancelFlow.setText("Help Page!");
		 }
    @FXML protected void cmiShowOnlyMyAdsHandler(ActionEvent event){
    	CheckMenuItem cmiShowOnlyMyAds = (CheckMenuItem) event.getSource();
    	if (cmiShowOnlyMyAds.isSelected()) {
    		ObservableList<AdDetailsBean> adsInTable = this.tvFlow.getItems();
    		logger.log(Level.INFO, String.format("ads in table: %d", adsInTable.size()));
    		for (int i = 0; i < adsInTable.size(); i ++) {
    			if (adsInTable.get(i).getAuthor().compareTo(Context.getReference().getCurrentView().getProfileName()) != 0) {
    				adsInTable.remove(i);
    				i --;
    			}
    		}
    	}
    	else {
    		this.bUpdate.fire();
    	}
    }
}
