package logic.view.components;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import logic.beans.ListAllFavorsToValidateBean;
import logic.beans.ValidateAFavorBean;
import logic.checkanswersofanad.AnswerAlreadyAcceptedException;
import logic.checkanswersofanad.CheckAnswersController;
import logic.checkanswersofanad.RequestAdHasAlreadyAnAnswerAcceptedException;
import logic.dao.FavorNotFoundException;
import logic.misc.Order;
import logic.validateafavor.ValidateAFavorController;
import logic.view.BLabels;
import logic.view.Context;
import logic.view.MSG;
import logic.view.Media;
import logic.view.ViewValidateAFavor;

public class ViewValidateAFavorController  implements Initializable {
		
	public class ValidateCell extends TableCell<FavorDetailsBean, Boolean>{
		public class BValidateHandler implements EventHandler <ActionEvent>{

			@Override
			public void handle(ActionEvent event) {
				// TODO implement bValidate logic #234
				ViewValidateAFavor currentView = (ViewValidateAFavor) Context.getReference().getCurrentView();
				Alert aConfirm = new Alert(AlertType.CONFIRMATION);
				aConfirm.setTitle(MSG.CONFIRM_VALIDATE_FAVOR.getMsg());
				aConfirm.setHeaderText("Please confirm validation for this Favor");
				aConfirm.setContentText(
						String.format(
								"You are going to validate Favor from user %s \n"
								+ "requested in date %s.\n\n"
								+ "Remember that you should validate a favor only\n"
								+ "after %s has done it.\n\n"
								+ "Are you sure?",
								currentView.getAllFavorsToValidateBean().getOffererUsername(getIndex()),
								DateFormat.getDateInstance().format(currentView.getAllFavorsToValidateBean().getDateOfRequest(getIndex()).getTime()),
								currentView.getAllFavorsToValidateBean().getOffererUsername(getIndex())
								)
						);
				ButtonType btYes = new ButtonType("Yes", ButtonData.YES);
				ButtonType btNo = new ButtonType("No", ButtonData.NO);
				aConfirm.getButtonTypes().setAll(btYes, btNo);
				Optional<ButtonType> result = aConfirm.showAndWait();
				if (ButtonData.YES.equals(result.get().getButtonData())){
					
				}
				else {
					return;
				}
				try {
					Button source = (Button) event.getSource();
					logger.log(Level.INFO, String.format("Button %s clicked", source.getText()));
					ValidateAFavorBean bean = new ValidateAFavorBean();
					bean.setOffererUsername(currentView.getAllFavorsToValidateBean().getOffererUsername(getIndex()));
					bean.setRequesterUsername(currentView.getAllFavorsToValidateBean().getRequesterUsername(getIndex()));
					bean.setDateOfRequest(currentView.getAllFavorsToValidateBean().getDateOfRequest(getIndex()));
					ValidateAFavorController.getReference().validateAFavor(bean);
					ImageView ivValidated = new ImageView();
					ivValidated.setImage(new Image(Media.DIALOG_INFO_COMPLETEDTASK.getPath()));
					Alert alertSuccess = new Alert(AlertType.INFORMATION);
					alertSuccess.setTitle(MSG.INFO_SUCCESS.getMsg());
					alertSuccess.setHeaderText("Favor validated succesfully!");
					alertSuccess.setContentText("Thank you for using iNeed!");
					alertSuccess.setGraphic(ivValidated);
					alertSuccess.showAndWait();
					bUpdate.fire();
					
				} catch (FavorNotFoundException e) {
					// TODO Auto-generated catch block
					logger.log(Level.SEVERE, e.toString());
				}
				
				
				
			}	
		}
		final Button bValidate = new Button(BLabels.VALIDATE.getLabel());
		final StackPane spPaddedButton = new StackPane();
		ValidateCell(){
			spPaddedButton.setPadding(new Insets(3));
			spPaddedButton.getChildren().add(bValidate);
			this.bValidate.setOnAction(new BValidateHandler());
			
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
	
	public class isRowEmptyCallback implements Callback<TableColumn.CellDataFeatures<FavorDetailsBean, Boolean>, ObservableValue<Boolean>>{

		@Override
		public ObservableValue<Boolean> call(CellDataFeatures<FavorDetailsBean, Boolean> features) {
			return new SimpleBooleanProperty(features.getValue() != null);
		}
	 }
	 public class addButtonToRowCallback implements Callback<TableColumn<FavorDetailsBean, Boolean>, TableCell<FavorDetailsBean, Boolean>>{

		@Override
		public TableCell<FavorDetailsBean, Boolean> call(TableColumn<FavorDetailsBean, Boolean> tvFavorDetailBeanBooleanTableColumn) {
			return new ValidateCell();
		}
		 
	 }
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	@FXML private TableView <FavorDetailsBean> tvFavors;
	@FXML private TableColumn <FavorDetailsBean, String> tcDateOfRequest;
	@FXML private TableColumn <FavorDetailsBean, String> tcOfferer;
	@FXML private TableColumn <FavorDetailsBean, Boolean> tcValidate;
	@FXML private Button bUpdate;
	
    @Override
    public void initialize(URL urlVAF, ResourceBundle resourceBundleVAF) { 
		 tcDateOfRequest.setCellValueFactory(new PropertyValueFactory<>("dateOfRequest"));
		 tcOfferer.setCellValueFactory(new PropertyValueFactory<>("offerer"));
		 tcValidate.setCellValueFactory(new isRowEmptyCallback());
		 tcValidate.setCellFactory(new addButtonToRowCallback());
		 this.bUpdate.fire();
    }
    
    @FXML protected void bUpdateHandler(ActionEvent event) {
    	Button bClicked = (Button) event.getSource();
    	logger.log(Level.INFO, String.format("button %s clicked", bClicked.getText()));
    	ViewValidateAFavor currentView = (ViewValidateAFavor) Context.getReference().getCurrentView();
    	ObservableList<FavorDetailsBean> rows = FXCollections.observableArrayList();
    	ListAllFavorsToValidateBean bean = new ListAllFavorsToValidateBean();
    	bean.setOrder(Order.TIME);
    	bean.setQueriedRequesterUsername(currentView.getProfileName());
    	ValidateAFavorController.getReference().listAllFavorsToValidate(bean);
    	currentView.setAllFavorsToValidateBean(bean);
    	for (int i = 0; i < bean.getNumOfFavors(); i ++) {
    		rows.add(new FavorDetailsBean(
    				bean.getOffererUsername(i),
    				DateFormat.getDateInstance().format(bean.getDateOfRequest(i).getTime())
    				));
    	}
    	this.tvFavors.setItems(rows);
    }

}
