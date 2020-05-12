package logic.viewanad;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
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
	
	String dateFormat = "YYYY-MM-dd-HH-mm-ss";
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
		logger.log(Level.INFO, "size of allAds to be ordered is: " + allAds.size());
		for (int i = 0; i < allAds.size(); i ++) {
			int minPos = i;
			for (int j = i ; j < allAds.size(); j ++) {
				try {
					if (Boolean.TRUE.equals(!((Boolean) EntityComparator.getIsSortedByMethod(order).invoke(this, allAds.get(minPos).getData(), allAds.get(j).getData())))) {
						minPos = j;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					String message = MessageFormat.format("{0} : {1}", e, e.getMessage());
					logger.log(Level.SEVERE, message);
				} catch (NoSuchIsSortedByMethodException e) {
					logger.log(Level.WARNING, e.getMessage());
				}
			}
			Ad temp = allAds.get(i);
			logger.log(Level.INFO, "before swapping: (" + i + ", " + (new SimpleDateFormat(dateFormat)).format(allAds.get(i).getData().getTime()) + "), (" + minPos + ", " + (new SimpleDateFormat(dateFormat)).format(allAds.get(minPos).getData().getTime()) + ")");
			allAds.set(i, allAds.get(minPos));
			allAds.set(minPos, temp);
			logger.log(Level.INFO, "after swapping: (" + i + ", " + (new SimpleDateFormat(dateFormat)).format(allAds.get(i).getData().getTime()) + "), (" + minPos + ", " + (new SimpleDateFormat(dateFormat)).format(allAds.get(minPos).getData().getTime()) + ")");
		}
	}
	
	private List<Ad> getAllAds() {
		List<Ad> allAds = new ArrayList<>();
		DAOAd daoAd = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
		int numberOfRequests = daoAd.getLastRequestId() + 1;
		logger.log(Level.INFO, "numberOfRequests is: {0}", numberOfRequests);
		int numberOfOffers = daoAd.getLastOfferId() + 1;
		logger.log(Level.INFO, "numberOfOffers is: {0}", numberOfOffers);
		try {	
			for(int i = 0; i < numberOfRequests; i ++) {
				Ad request = new RequestAd();
				daoAd.loadAd(request, i);
				logger.log(Level.INFO, "adding request with id: " + request.getId());
				allAds.add(request);
			}
			for(int i = 0; i < numberOfOffers; i ++) {
				Ad offer = new OfferAd();
				daoAd.loadAd(offer, i);
				logger.log(Level.INFO, "adding offer with id: " + offer.getId());
				allAds.add(offer);
			}
			
		} catch (AdNotFoundException e) {
			this.logger.log(Level.WARNING, e.getMessage());
		}
		logger.log(Level.INFO, "size of allAds retrieved is: " + allAds.size());
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
