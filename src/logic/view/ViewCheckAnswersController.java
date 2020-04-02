package logic.view;

import java.net.URL;
import java.text.DateFormat;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import logic.beans.CheckAnswersBean;
import logic.checkanswersofanad.CheckAnswersController;

public class ViewCheckAnswersController implements Initializable {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@FXML private TableView<AnswerDetailsBean> tvAnswers;
	@FXML private TableColumn<AnswerDetailsBean, String> tcFrom;
	@FXML private TableColumn<AnswerDetailsBean, String> tcDate;
	@FXML private TableColumn<AnswerDetailsBean, Boolean> tcButton;
	@FXML private Button bUpdate;
	
	public class ButtonCell extends TableCell<AnswerDetailsBean, Boolean>{
	
		public class BHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {
				Button bClicked = (Button) event.getSource();
				logger.log(Level.INFO, String.format("Button '%s' clicked", bClicked.getText()));
				BLabels bLabel;
				try {
					bLabel = BLabels.getConstant(bClicked.getText());
					switch (bLabel) {
					case CONTACTUSER:
						this.bContactUserHandler(event);
						break;
					case DENY:
						this.bDenyHandler(event);
						break;
					case ACCEPT:
						this.bAcceptHandler(event);
						break;
					default:
						break;
					}
				} catch (NoSuchLabelException e) {
					logger.log(Level.SEVERE, e.getMessage());
				}
			}
			
			private void bContactUserHandler(ActionEvent event) {
				// TODO: ContactUser UC
			}
			private void bAcceptHandler(ActionEvent event) {
				// TODO: Accept the answer
			}
			private void bDenyHandler(ActionEvent event) {
				// TODO: Deny the answer
			}
		}
		
		final Button bContactUser = new Button(BLabels.CONTACTUSER.getLabel());
		final Button bDeny = new Button(BLabels.DENY.getLabel());
		final Button bAccept = new Button(BLabels.ACCEPT.getLabel());
		final HBox hBox = new HBox();
		final StackPane spPaddedButton = new StackPane();
		
		ButtonCell(){
			spPaddedButton.setPadding(new Insets(3));
			this.bAccept.setMaxWidth(Double.MAX_VALUE);
			this.bContactUser.setMaxWidth(Double.MAX_VALUE);
			this.bDeny.setMaxWidth(Double.MAX_VALUE);
			hBox.getChildren().addAll(bContactUser, bAccept, bDeny);
			HBox.setHgrow(bAccept, Priority.ALWAYS);
			HBox.setHgrow(bDeny, Priority.ALWAYS);
			HBox.setHgrow(bContactUser, Priority.ALWAYS);
			spPaddedButton.getChildren().add(hBox);
			this.bContactUser.setOnAction(new BHandler());
			this.bDeny.setOnAction(new BHandler());
			this.bAccept.setOnAction(new BHandler());
			
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
	
	public class IsRowEmptyCallback implements Callback<TableColumn.CellDataFeatures<AnswerDetailsBean, Boolean>, ObservableValue<Boolean>>{

		@Override
		public ObservableValue<Boolean> call(CellDataFeatures<AnswerDetailsBean, Boolean> features) {
			return new SimpleBooleanProperty(features.getValue() != null);
		}
	 }
	public class AddButtonToRowCallback implements Callback<TableColumn<AnswerDetailsBean, Boolean>, TableCell<AnswerDetailsBean, Boolean>>{

		@Override
		public TableCell<AnswerDetailsBean, Boolean> call(TableColumn<AnswerDetailsBean, Boolean> tvFlowRowBooleanTableColumn) {
			return new ButtonCell();
		}
		 
	 }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tcFrom.setCellValueFactory(new PropertyValueFactory<>("answerer"));
		this.tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.tcButton.setCellValueFactory(new IsRowEmptyCallback());
		this.tcButton.setCellFactory(new AddButtonToRowCallback());
		this.bUpdate.fire();
	}
	
	@FXML protected void handleSubmitButtonUpdate(ActionEvent event) {
		
		ViewCheckAnswers currentView = (ViewCheckAnswers) Context.getReference().getCurrentView();
		ObservableList<AnswerDetailsBean> rows = FXCollections.observableArrayList();
		CheckAnswersBean bean = new CheckAnswersBean();
		bean.setAdId(currentView.getAdId());
		bean.setAdType(currentView.getAdType());
		CheckAnswersController.getInstance().answersList(bean);
		for (int i = 0; i < bean.getNumOfAnswers(); i ++) {
			rows.add(new AnswerDetailsBean (bean.getAnswerer(i), DateFormat.getDateInstance().format(bean.getDate(i).getTime())));
		}
		this.tvAnswers.setItems(rows);
	}
	
}
