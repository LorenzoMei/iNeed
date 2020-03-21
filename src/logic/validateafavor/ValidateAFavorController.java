package logic.validateafavor;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.ListAllFavorsToValidateBean;
import logic.beans.ValidateAFavorBean;
import logic.dao.DAOFactory;
import logic.dao.DAOFavor;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.dao.FavorNotFoundException;
import logic.dao.UserNotFoundException;
import logic.entity.Favor;
import logic.entity.User;
import logic.misc.EntityComparator;
import logic.misc.NoSuchIsSortedByMethodException;
import logic.misc.Order;

public class ValidateAFavorController {
	
	private static ValidateAFavorController ref = null;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private ValidateAFavorController() {}
	
	public static ValidateAFavorController getReference() {
		if (ref == null) {
			ref = new ValidateAFavorController();
		}
		return ref;
	}
	
	public void validateAFavor(ValidateAFavorBean bean) throws FavorNotFoundException {
		DAOUser daoUser = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		DAOFavor daoFavor = (DAOFavor) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.FAVOR);
		User offerer = new User();
		User requester = new User();
		try {
			daoUser.loadUser(requester, bean.getRequesterUsername());
			logger.log(Level.INFO, "loaded requester: " + requester.getUsername());
			daoUser.loadUser(offerer, bean.getOffererUsername());
			logger.log(Level.INFO, "loaded offerer: " + offerer.getUsername());
		} catch (UserNotFoundException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		Favor favorToValidate = new Favor();
		logger.log(Level.INFO, "loading favor with requester, offerer, date: " + requester.getUsername() + " " + offerer.getUsername() + " " + bean.getDateOfRequest().getTime().toString());
		daoFavor.loadFavor(favorToValidate, offerer, requester, bean.getDateOfRequest());
		logger.log(Level.INFO, "validating favor with requester, offerer, date: " + favorToValidate.getRequester().getUsername() + " " + favorToValidate.getOfferer().getUsername() + " " + favorToValidate.getDateOfRequest().getTime().toString());
		favorToValidate.setDateOfValidation(Calendar.getInstance());
		offerer.gainAToken();
		daoFavor.storeFavor(favorToValidate);
		daoUser.storeUser(offerer);
	}
	
	public void listAllFavorsToValidate(ListAllFavorsToValidateBean bean) {
		DAOUser daoUser = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
		DAOFavor daoFavor = (DAOFavor) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.FAVOR);
		User offerer = new User();
		User requester = new User();
		List<Favor> allFavors;
		try {
			daoUser.loadUser(requester, bean.getRequesterUsername());
			logger.log(Level.INFO, "loaded requester: " + requester.getUsername());
			daoUser.loadUser(offerer, bean.getOffererUsername());
			logger.log(Level.INFO, "loaded offerer: " + offerer.getUsername());
		} catch (UserNotFoundException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		allFavors = (daoFavor.loadFavors(requester, offerer, bean.getDateOfRequest()));
		logger.log(Level.INFO, "loaded " + allFavors.size() + " favors");
		for (Favor favor : allFavors) {
			if (favor.getDateOfValidation() != null) {
				allFavors.remove(favor);
				logger.log(Level.INFO, "Favor already validated in date: " + favor.getDateOfValidation().getTime().toString());
			}
		}
		if (bean.getOrder() != Order.UNSORTED) {
			this.sortFavors(allFavors, bean.getOrder());
		}
		
		
		bean.setFavorsToValidate(allFavors);
	}
	
	private void sortFavors(List<Favor> allFavors, Order order){
		for (int i = 0; i < allFavors.size(); i ++) {
			int minPos = i;
			for (int j = i ; j < allFavors.size(); j ++) {
				try {
					if (!Boolean.TRUE.equals(((Boolean) EntityComparator.getIsSortedByMethod(order).invoke(this, allFavors.get(minPos).getDateOfRequest(), allFavors.get(j).getDateOfRequest())))) {
						minPos = j;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.log(Level.SEVERE, e.getMessage());
				} catch (NoSuchIsSortedByMethodException e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
			Favor temp = allFavors.get(i);
			allFavors.set(i, allFavors.get(minPos));
			allFavors.set(minPos, temp);
		}
	}
}
 