package test.validateafavor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import logic.beans.ListAllFavorsToValidateBean;
import logic.dao.DAOFactory;
import logic.dao.DAOFavor;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.entity.Favor;
import logic.entity.User;
import logic.misc.Order;
import logic.validateafavor.ValidateAFavorController;

public class TestListAllFavorsToValidate {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	@Test
	public void listAllFavorsToValidate() {
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
			favors[i].setOffererUsername(offererThenRequester[0].getUsername());
			favors[i].setRequesterUsername(offererThenRequester[1].getUsername());
			dateOfRequest.set(Calendar.YEAR, dateOfRequest.get(Calendar.YEAR) + i);
			favors[i].setDateOfRequest(dateOfRequest);
			daoFavor.storeFavor(favors[i]);
		}
		favors[1].setDateOfValidation(Calendar.getInstance());
		ListAllFavorsToValidateBean listAllFavorsToValidateBean = new ListAllFavorsToValidateBean();
		listAllFavorsToValidateBean.setOrder(Order.TIME);
//		listAllFavorsToValidateBean.setOffererUsername(offererThenRequester[0].getUsername());
		listAllFavorsToValidateBean.setQueriedRequesterUsername(offererThenRequester[1].getUsername());
		
		ValidateAFavorController.getReference().listAllFavorsToValidate(listAllFavorsToValidateBean);
		
		Boolean isOrdered = false;
		logger.log(Level.INFO,"getAllDatesOfValidation size is: " + listAllFavorsToValidateBean.getAllDatesOfRequest().size());
		for (int i = 0; i < listAllFavorsToValidateBean.getAllDatesOfRequest().size() - 1; i ++) {
			isOrdered = true;
			logger.log(Level.INFO, "asserting couple: (" + i + ", " + (new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")).format(listAllFavorsToValidateBean.getAllDatesOfRequest().get(i).getTime()) + "), (" + Integer.valueOf(i+1) + ", " + (new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")).format(listAllFavorsToValidateBean.getAllDatesOfRequest().get(i+1).getTime()) + ")");
			if (listAllFavorsToValidateBean.getAllDatesOfRequest().get(i).compareTo(listAllFavorsToValidateBean.getAllDatesOfRequest().get(i + 1)) <= 0) {
				isOrdered = false;
			}
			Assert.assertEquals(true, isOrdered);
		}
		Assert.assertEquals(true, isOrdered);
	}
}
