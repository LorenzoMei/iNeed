package logic.view;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
import logic.viewanad.ViewAnAdController;


public class ViewFlowController implements Initializable{

	
	
	 private View nextViewF;
	 String formError = MSGError.ERROR_FORM.getMsg();
	 @FXML private Text actionCancelFlow;
	 @FXML private TextField searchTextField;
	 @FXML private GridPane grid;
	 @FXML private TableView<AdDetails> tvFlow;
	 @FXML private TableColumn<AdDetails, String> tcType;
	 @FXML private TableColumn<AdDetails, String> tcTitle;
	 @FXML private TableColumn<AdDetails, String> tcAuthor;
	 @FXML private TableColumn<AdDetails, String> tcDate;
	 @FXML private TableColumn<AdDetails, Boolean> tcShowMoreDetails;
	 @FXML private Button bUpdate;
	 
     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	public class ShowMoreDetailsCell extends TableCell<AdDetails, Boolean>{
		
		public class BShowMoreDetailsHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {
				
				nextViewF = (View) new ViewAd();
				Context.getReference().getCurrentView().setNextView(nextViewF);
				Context.getReference().goNext();
				
				logger.log(Level.INFO, "button bShowMoreDetails clicked");
				
			}
		}
		
		final Button bShowMoreDetails = new Button("Show More");
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
	public class isRowEmptyCallback implements Callback<TableColumn.CellDataFeatures<AdDetails, Boolean>, ObservableValue<Boolean>>{

		@Override
		public ObservableValue<Boolean> call(CellDataFeatures<AdDetails, Boolean> features) {
			return new SimpleBooleanProperty(features.getValue() != null);
		}
	 }
	 public class addButtonToRowCallback implements Callback<TableColumn<AdDetails, Boolean>, TableCell<AdDetails, Boolean>>{

		@Override
		public TableCell<AdDetails, Boolean> call(TableColumn<AdDetails, Boolean> tvFlowRowBooleanTableColumn) {
			return new ShowMoreDetailsCell();
		}
		 
	 }
	
	public class AdDetails{
		private StringProperty type = new SimpleStringProperty(this, "type");
		private StringProperty title = new SimpleStringProperty(this, "title");
		private StringProperty author = new SimpleStringProperty(this, "author");
		private StringProperty date = new SimpleStringProperty(this, "date");
		
		public AdDetails() {
			this.setAuthor("N/A");
			this.setDate("N/A");
			this.setTitle("N/A");
			this.setType("N/A");
		}
		
		public String getType() {
			return this.type.getValue();
		}
		public String getTitle() {
			return this.title.getValue();
		}
		public String getAuthor() {
			return this.author.getValue();
		}
		public String getDate() {
			return this.date.getValue();
		}
		public void setType(String type) {
			this.type.setValue(type);
		}
		public void setTitle(String title) {
			this.title.setValue(title);
		}
		public void setAuthor(String author) {
			this.author.setValue(author);
		}
		public void setDate(String date) {
			this.date.setValue(date);
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
		 tcShowMoreDetails.setCellValueFactory(new isRowEmptyCallback());
		 tcShowMoreDetails.setCellFactory(new addButtonToRowCallback());
		 String status = "My location " + locationflow + " my resoursources: " + resourcesflow;
		 logger.log(Level.INFO, status);
	     nextViewF = (View) new ViewFlow();
	     logger.log(Level.INFO, "In viewFlow the name of the user is: " + nextViewF.getProfileName());
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
		 List<AdDetails> rowsBuffer = new ArrayList<>();
		 OrderedAdsBean bean = new OrderedAdsBean();
		 bean.setOrderByTime();
		 ViewAnAdController.getReference().listAllAds(bean);
		 for (int i = 0; i < bean.getNumOfAds(); i ++) {
			 AdDetails currentRow = new AdDetails();
			 logger.log(Level.INFO, String.format("Author in AdDetails before: %s, author in bean before: %s", currentRow.getAuthor(), bean.getOwner(i)));
			 
			 currentRow.setAuthor(bean.getOwner(i));
			 
			 logger.log(Level.INFO, String.format("Author in AdDetails after: %s, author in bean after: %s", currentRow.getAuthor(), bean.getOwner(i)));
			 currentRow.setTitle(bean.getTitle(i));
			 currentRow.setType(bean.getType(i));
			 currentRow.setDate(DateFormat.getDateInstance().format(bean.getDateOfPublication(i).getTime()));
			 rowsBuffer.add(currentRow);
		 }
		 ObservableList<AdDetails> rows = FXCollections.observableList(rowsBuffer);
		 this.tvFlow.setItems(rows);
 		 actionCancelFlow.setText("");
	     actionCancelFlow.setText("pagina aggiornata");
	 }
	 

	 
	 @FXML protected void handleSubmitButtonHelp(ActionEvent event) {
	 		 actionCancelFlow.setText("");
		     actionCancelFlow.setText("Help Page!");
		 }
    
}
