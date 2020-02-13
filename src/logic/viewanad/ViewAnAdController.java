package logic.viewanad;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.OrderedAdsBean;
import logic.dao.AdNotFoundException;
import logic.dao.DAOAd;
import logic.dao.DAOFactory;
import logic.entity.Ad;
import logic.entity.OfferAd;
import logic.entity.RequestAd;

public class ViewAnAdController implements ViewAnAdControllerInterface{

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static ViewAnAdController ref;
	
	public static ViewAnAdController getReference() {
		if (ref == null) {
			ref = new ViewAnAdController();
		}
		return ref;
	}
	
	private ViewAnAdController() {}
	
	private Method getIsSortedByMethod(String order) throws NoSuchListByOrderMethodException{
		Method[] allMethods = this.getClass().getDeclaredMethods();
		for (int i = 0; i < allMethods.length; i ++) {
			if (Modifier.isPrivate(allMethods[i].getModifiers()) 
					&& allMethods[i].getName().contains("IsSorted" + order)
					&& !allMethods[i].isSynthetic()) {
				return allMethods[i];
			}
		}
		throw new NoSuchListByOrderMethodException(order);
		
	}
	
	private Boolean IsSortedByTime(Ad ad1, Ad ad2){
		if (ad1.getData().compareTo(ad2.getData()) >= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void sortAds(List<Ad> allAds, String order) throws NoSuchListByOrderMethodException {
		for (int i = 0; i < allAds.size(); i ++) {
			int minPos = i;
			for (int j = i ; j < allAds.size(); j ++) {
				try {
					if (((Boolean) this.getIsSortedByMethod(order).invoke(this, allAds.get(minPos), allAds.get(j))) == false) {
						minPos = j;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.log(Level.SEVERE, e.toString() + ": " + e.getMessage());
				}
			}
			Ad temp = allAds.get(i);
			allAds.set(i, allAds.get(minPos));
			allAds.set(minPos, temp);
		}
	}
	
	private List<Ad> getAllAds() {
		List<Ad> allAds = new ArrayList<>();
		DAOAd daoAd = (DAOAd) DAOFactory.getReference().getDAOReference(DAOFactory.ENTITYNAME_AD);
		int numberOfRequests = daoAd.getLastRequestId();
		int numberOfOffers = daoAd.getLastOfferId();
		try {	
			for(int i = 0; i < numberOfRequests; i ++) {
				Ad request = new RequestAd();
				daoAd.loadAd(request, i);
				allAds.add(request);
			}
			for(int i = 0; i < numberOfOffers; i ++) {
				Ad offer = new OfferAd();
				daoAd.loadAd(offer, i);
				allAds.add(offer);
			}
			
		} catch (AdNotFoundException e) {
			this.logger.log(Level.WARNING, e.getMessage());
		}
		return allAds;
	}

	@Override
	public void listAllAds(OrderedAdsBean bean) {
		List<Ad> allAds = getAllAds();
		if (bean.getOrder() != ViewAnAdControllerInterface.UNSORTED) {
			try {
				this.sortAds(allAds, bean.getOrder());
			}
			catch (NoSuchListByOrderMethodException e) {
				logger.log(Level.WARNING, e.getMessage());
				logger.log(Level.WARNING, "setting list order as " + ViewAnAdControllerInterface.UNSORTED);
			}
			finally {
				bean.setAds(allAds);		
			}
		}
	}

}
