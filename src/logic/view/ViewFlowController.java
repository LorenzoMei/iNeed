package logic.view;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.beans.OrderedAdsBean;
import logic.viewanad.ViewAnAdController;


public class ViewFlowController implements Initializable{

	public class TvFlowRow{
		private StringProperty type = new SimpleStringProperty(this, "type");
		private StringProperty title = new SimpleStringProperty(this, "title");
		private StringProperty author = new SimpleStringProperty(this, "author");
		private StringProperty date = new SimpleStringProperty(this, "date");
		
		public TvFlowRow() {
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
	
	 private View nextViewF;
	 String formError = MSGError.ERROR_FORM.getMsg();
	 @FXML private Text actionCancelFlow;
	 @FXML private TextField searchTextField;
	 @FXML private GridPane grid;
	 @FXML private TableView<TvFlowRow> tvFlow;
	 @FXML private TableColumn<TvFlowRow, String> tcType;
	 @FXML private TableColumn<TvFlowRow, String> tcTitle;
	 @FXML private TableColumn<TvFlowRow, String> tcAuthor;
	 @FXML private TableColumn<TvFlowRow, String> tcDate;
	 @FXML private Button bUpdate;
	 


     private List<TextInputControl> textInputFields;

	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 public void initialize(URL locationflow, ResourceBundle resourcesflow) {
		 tcType.setCellValueFactory(new PropertyValueFactory<>("type"));
		 tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		 tcAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
		 tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
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
		 List<TvFlowRow> rowsBuffer = new ArrayList<>();
		 OrderedAdsBean bean = new OrderedAdsBean();
		 bean.setOrderByTime();
		 ViewAnAdController.getReference().listAllAds(bean);
		 for (int i = 0; i < bean.getNumOfAds(); i ++) {
			 TvFlowRow currentRow = new TvFlowRow();
			 logger.log(Level.INFO, String.format("Author in TvFlowRow before: %s, author in bean before: %s", currentRow.getAuthor(), bean.getOwner(i)));
			 
			 currentRow.setAuthor(bean.getOwner(i));
			 
			 logger.log(Level.INFO, String.format("Author in TvFlowRow after: %s, author in bean after: %s", currentRow.getAuthor(), bean.getOwner(i)));
			 currentRow.setTitle(bean.getTitle(i));
			 currentRow.setType(bean.getType(i));
			 currentRow.setDate(DateFormat.getDateInstance().format(bean.getDateOfPublication(i).getTime()));
			 rowsBuffer.add(currentRow);
		 }
		 ObservableList<TvFlowRow> rows = FXCollections.observableList(rowsBuffer);
		 this.tvFlow.setItems(rows);
 		 actionCancelFlow.setText("");
	     actionCancelFlow.setText("pagina aggiornata");
	 }
	 
	 @FXML protected void handleSubmitButtonHelp(ActionEvent event) {
	 		 actionCancelFlow.setText("");
		     actionCancelFlow.setText("Help Page!");
		 }
    
}
