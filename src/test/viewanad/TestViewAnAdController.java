package test.viewanad;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import logic.beans.OrderedAdsBean;
import logic.dao.DAOAd;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.entity.Ad;
import logic.entity.OfferAd;
import logic.entity.RequestAd;
import logic.viewanad.ViewAnAdController;

public class TestViewAnAdController {
	
	@Test
	public void testGetAllAdsSortedByTime() {
	
		Builder cBuilder = new Calendar.Builder();
		cBuilder.setDate(2018, 0, 13);
		Calendar calendar1 = cBuilder.build();
		cBuilder.setDate(2019, 3, 5);
		Calendar calendar2 = cBuilder.build();
		cBuilder.setDate(2020, 11, 2);
		Calendar calendar3 = cBuilder.build();
		cBuilder.setDate(2021, 11, 2);
		Calendar calendar4 = cBuilder.build();
		
		Ad ad1 = new RequestAd();
		Ad ad2 = new RequestAd();
		Ad ad3 = new OfferAd();
		Ad ad4 = new OfferAd();
		
		ad1.setData(calendar1);
		ad2.setData(calendar2);
		ad3.setData(calendar3);
		ad4.setData(calendar4);

		
		DAOAd dao = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
		dao.storeNewAd(ad1);
		dao.storeNewAd(ad2);
		dao.storeNewAd(ad3);
		dao.storeNewAd(ad4);
		
		OrderedAdsBean bean = new OrderedAdsBean();
		bean.setOrderByTime();
		
		ViewAnAdController.getReference().listAllAds(bean);
		
		Boolean isOrdered = true;
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,"getAllDatesOfPublication size is: " + bean.getAllDatesOfPublication().size());
		for (int i = 0; i < bean.getAllDatesOfPublication().size() - 1; i ++) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "asserting couple: (" + i + ", " + (new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")).format(bean.getAllDatesOfPublication().get(i).getTime()) + "), (" + Integer.valueOf(i+1) + ", " + (new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")).format(bean.getAllDatesOfPublication().get(i+1).getTime()) + ")");
			if (bean.getAllDatesOfPublication().get(i).compareTo(bean.getAllDatesOfPublication().get(i + 1)) <= 0) {
				isOrdered = false;
			}
		}
		
		Assert.assertEquals(true, isOrdered);
	}
	
}
