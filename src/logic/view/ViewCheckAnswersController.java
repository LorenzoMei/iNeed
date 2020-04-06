package logic.view;

import java.net.URL;
import java.text.DateFormat;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import logic.beans.ActionOnAnswerBean;
import logic.beans.CheckAnswersBean;
import logic.beans.ContactInfo;
import logic.beans.RetrieveContactInfoBean;
import logic.checkanswersofanad.AnswerAlreadyAcceptedException;
import logic.checkanswersofanad.CheckAnswersController;
import logic.checkanswersofanad.RequestAdHasAlreadyAnAnswerAcceptedException;
import logic.contactuser.ContactUserController;
import logic.dao.AnswerNotFoundException;

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
				AnswerDetailsBean selected = tvAnswers.getItems().get(getIndex());
				RetrieveContactInfoBean retrieveContactInfoBean = new RetrieveContactInfoBean();
				retrieveContactInfoBean.setUsername(selected.getAnswerer());
				ContactUserController.getInstance().retrieveContactUserInfo(retrieveContactInfoBean);
				ImageView iv = new ImageView();
				iv.setImage(new Image(Media.DIALOG_INFO_CONTACTUSER.getPath()));
				Dialog<Boolean> dialog = new Dialog<>();
				dialog.setGraphic(iv);
				dialog.setTitle(MSG.INFO_CONTACTUSER.getMsg());
				DialogPane dpContactUser = new DialogPane();
				VBox vb = new VBox();
				HBox hb = new HBox();
				Label lContentText = new Label(String.format("You can contact %s using these info: ",
						selected.getAnswerer()
							));
				TextField tfMailAddress = new TextField();
				tfMailAddress.setText(retrieveContactInfoBean.getInfo(ContactInfo.MAIL));
				tfMailAddress.setEditable(false);
				vb.getChildren().addAll(lContentText, tfMailAddress);
				hb.getChildren().addAll(vb, iv);
				dpContactUser.setContent(hb);
				dpContactUser.getButtonTypes().add(ButtonType.OK);
				dialog.setDialogPane(dpContactUser);
				dialog.showAndWait();
				
			}
			private void bAcceptHandler(ActionEvent event) {
				ViewCheckAnswers currentView = (ViewCheckAnswers) Context.getReference().getCurrentView();
				ActionOnAnswerBean bean = new ActionOnAnswerBean();
				bean.setAnswererUsername((tvAnswers.getItems().get(getIndex()).getAnswerer()));
				bean.setAnsweredUsername(currentView.getProfileName());
				bean.setAdId(currentView.getAdId());
				bean.setAdType(currentView.getAdType());
				try {
					CheckAnswersController.getInstance().acceptAnswer(bean);
					Alert aSuccess = new Alert(AlertType.INFORMATION);
					aSuccess.setTitle(MSG.INFO_SUCCESS.getMsg());
					aSuccess.setHeaderText("You succesfully accepted this answer, nice!");
					aSuccess.setContentText(String.format(
							"After %s has done the favor, don't forget to validate it\nin Validate a Favor section.",
							bean.getAnswererUsername()
							));
					aSuccess.setGraphic(new ImageView(Media.DIALOG_INFO_COMPLETEDTASK.getPath()));
					aSuccess.showAndWait();
					
				} catch (RequestAdHasAlreadyAnAnswerAcceptedException e) {
					logger.log(Level.WARNING, e.toString());
					Alert aConfirm = new Alert(AlertType.CONFIRMATION);
					aConfirm.setTitle(MSG.CONFIRM_CHANGE_ANSWER_ACCEPTED.getMsg());
					aConfirm.setHeaderText("You already accepted another answer for this Request Ad!");
					aConfirm.setContentText(
							String.format("You already accepted anwer from user %s in date %s,\nwould you like to accept this one instead?", 
									e.getOffererUsername(), 
									DateFormat.getDateInstance().format(e.getDate().getTime())
									)
							);
					ButtonType btYes = new ButtonType("Yes", ButtonData.YES);
					ButtonType btNo = new ButtonType("No", ButtonData.NO);
					aConfirm.getButtonTypes().setAll(btYes, btNo);
					Optional<ButtonType> result = aConfirm.showAndWait();
					if (ButtonData.YES.equals(result.get().getButtonData())){
						try {
							bean.setConfirmed(true);
							CheckAnswersController.getInstance().acceptAnswer(bean);
						} catch (RequestAdHasAlreadyAnAnswerAcceptedException | AnswerAlreadyAcceptedException e1) {
							logger.log(Level.SEVERE, e.toString());
						}
					}
					else {
						return;
					}
					
				} catch (AnswerAlreadyAcceptedException e) {
					logger.log(Level.WARNING, e.toString());
					Alert aError = new Alert(AlertType.ERROR);
					aError.setTitle(MSG.ERROR_ALREADY_ACCEPTED.getMsg());
					aError.setHeaderText("You can't accept more than one time the same answer!");
					aError.setContentText(String.format(
							"You already accepted this answer in date: %s",
							DateFormat.getDateInstance().format(e.getDateOfRequest().getTime())
							));
					aError.showAndWait();
				}
				
			}
			private void bDenyHandler(ActionEvent event) {
				ViewCheckAnswers currentView = (ViewCheckAnswers) Context.getReference().getCurrentView();
				ActionOnAnswerBean bean = new ActionOnAnswerBean();
				bean.setAnswererUsername((tvAnswers.getItems().get(getIndex()).getAnswerer()));
				bean.setAnsweredUsername(currentView.getProfileName());
				bean.setAdId(currentView.getAdId());
				bean.setAdType(currentView.getAdType());
				
				Alert dConfirm = new Alert(AlertType.CONFIRMATION);
				dConfirm.setTitle(MSG.CONFIRM_DENIED_ANSWER_USER.getMsg());
				dConfirm.setHeaderText("Are you sure you want to Deny this Answer?");
				dConfirm.setContentText("By confirming you will no longer be able to see this user\nin the answers list.\nIf this answer was first accepted, the respective Favor\nwill be deleted, so that you can accept another\nanswer.\nAre you sure?");
				
				ButtonType btnConfirm = new ButtonType("Confirm", ButtonData.YES);
				ButtonType btnCancel = new ButtonType("Cancel", ButtonData.NO);
				dConfirm.getButtonTypes().setAll(btnConfirm, btnCancel);
				Optional<ButtonType> result = dConfirm.showAndWait();
				
				if (ButtonData.YES.equals(result.get().getButtonData())){
					try {
						CheckAnswersController.getInstance().denyAnswer(bean);
						bUpdate.fire();
					} catch (AnswerNotFoundException e) {
						logger.log(Level.SEVERE, e.toString());
					}
				}
				else {
					return;
				}
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
