package test.validateafavor;

import org.junit.Test;

import logic.beans.ListAllFavorsToValidateBean;
import logic.beans.ValidateAFavorBean;
import logic.dao.DAOFactory;
import logic.dao.DAOFavor;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.dao.FavorNotFoundException;
import logic.entity.Favor;
import logic.entity.User;
import logic.misc.Order;
import logic.validateafavor.ValidateAFavorController;

import java.util.Calendar;
//import java.util.logging.Logger;

import org.junit.Assert;

public class TestValidateAFavorController {
	
//	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void validateAFavor() throws FavorNotFoundException {
		DAOUser daoUser = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		DAOFavor daoFavor = (DAOFavor) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.FAVOR);
		Calendar dateOfRequest = Calendar.getInstance();
		User[] offererThenRequester = {new User(), new User()};
		for (int i = 0; i < offererThenRequester.length; i ++) {
			offererThenRequester[i].setUsername(String.format("%d", i));
			daoUser.storeUser(offererThenRequester[i]);
		}
		Favor[] favors = {new Favor(),new Favor(),new Favor(),new Favor() };
		for (int i = 0; i < favors.length; i ++) {
			favors[i].setOfferer(offererThenRequester[0]);
			favors[i].setRequester(offererThenRequester[1]);
			dateOfRequest.set(Calendar.YEAR, dateOfRequest.get(Calendar.YEAR) + i);
			favors[i].setDateOfRequest(dateOfRequest);
			daoFavor.storeFavor(favors[i]);
		}
		favors[1].setDateOfValidation(Calendar.getInstance());
		ListAllFavorsToValidateBean listAllFavorsToValidateBean = new ListAllFavorsToValidateBean();
		listAllFavorsToValidateBean.setOrder(Order.TIME);
		listAllFavorsToValidateBean.setOffererUsername(offererThenRequester[0].getUsername());
		listAllFavorsToValidateBean.setRequesterUsername(offererThenRequester[1].getUsername());
		
		ValidateAFavorController.getReference().listAllFavorsToValidate(listAllFavorsToValidateBean);
		
		ValidateAFavorBean validateAFavorBean = new ValidateAFavorBean();
		validateAFavorBean.setOffererUsername(offererThenRequester[0].getUsername());
		validateAFavorBean.setRequesterUsername(offererThenRequester[1].getUsername());
		validateAFavorBean.setDateOfRequest(listAllFavorsToValidateBean.getAllDatesOfRequest().get(0));
		
		ValidateAFavorController.getReference().validateAFavor(validateAFavorBean);
		
		Favor favorValidated = new Favor();
		daoFavor.loadFavor(favorValidated, offererThenRequester[0], offererThenRequester[1], validateAFavorBean.getDateOfRequest());
		
		Assert.assertNotEquals(null, favorValidated.getDateOfValidation());
	}
}
