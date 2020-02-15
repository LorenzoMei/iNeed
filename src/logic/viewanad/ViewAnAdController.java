package logic.viewanad;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.OrderedAdsBean;
import logic.dao.AdNotFoundException;
import logic.dao.DAOAd;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Ad;
import logic.entity.OfferAd;
import logic.entity.RequestAd;
import logic.misc.EntityComparator;
import logic.misc.NoSuchIsSortedByMethodException;
import logic.misc.Order;

public class ViewAnAdController {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static ViewAnAdController ref;
	
	public static ViewAnAdController getReference() {
		if (ref == null) {
			ref = new ViewAnAdController();
		}
		return ref;
	}
	
	private ViewAnAdController() {}
	
	private void sortAds(List<Ad> allAds, Order order) throws NoSuchIsSortedByMethodException {
		for (int i = 0; i < allAds.size(); i ++) {
			int minPos = i;
			for (int j = i ; j < allAds.size(); j ++) {
				try {
					if (Boolean.TRUE.equals(((Boolean) EntityComparator.getIsSortedByMethod(order).invoke(this, allAds.get(minPos), allAds.get(j))) == false)) {
						minPos = j;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.log(Level.SEVERE, e.toString() + ": " + e.getMessage());
				} catch (NoSuchIsSortedByMethodException e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
			Ad temp = allAds.get(i);
			allAds.set(i, allAds.get(minPos));
			allAds.set(minPos, temp);
		}
	}
	
	private List<Ad> getAllAds() {
		List<Ad> allAds = new ArrayList<>();
		DAOAd daoAd = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
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

	public void listAllAds(OrderedAdsBean bean) {
		List<Ad> allAds = getAllAds();
		if (bean.getOrder() != Order.UNSORTED) {
			try {
				this.sortAds(allAds, bean.getOrder());
			}
			catch (NoSuchIsSortedByMethodException e) {
				logger.log(Level.WARNING, e.getMessage());
				logger.log(Level.WARNING, "setting list order as " + Order.UNSORTED.getValue());
				bean.setOrderUnsorted();
			}
			finally {
				bean.setAds(allAds);		
			}
		}
	}

}
