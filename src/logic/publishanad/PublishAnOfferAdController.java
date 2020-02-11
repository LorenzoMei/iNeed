package logic.publishanad;

import logic.dao.DAOFactory;
import logic.entity.Ad;
import logic.entity.AdId;
import logic.entity.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Calendar.Builder;

import logic.dao.DAOAd;

public class PublishAnOfferAdController implements PublishAnAdInterface{
	
	private static PublishAnOfferAdController instance;
	
	public static PublishAnOfferAdController getInstance() {
		if(instance == null)
			instance = new PublishAnOfferAdController();
		return instance;
	}
	
	private PublishAnOfferAdController() {
	}
	
	public Ad createAd(PublishAnAdBean publishAdBean) throws IllegalAccessException, InvocationTargetException {
		
		DAOAd dao = (DAOAd) DAOFactory.getReference().getDAOReference("Ad");
		Ad ad = new Ad();
		AdId id = new AdId();
		Data data = new Data();
		
		dao.loadId(id);
		
		ad.setId(id.getId());
		ad.setData(data.buildDate());
		ad.setType("Offer");
		
		Method[] methodsBean = publishAdBean.getClass().getMethods();
				
		Method[] methodsEntity = ad.getClass().getMethods();
		
		for(int i = 0; i < methodsBean.length; i++) {
			if(methodsBean[i].getName().contains("get")) {
				for(int j = 0; j < methodsEntity.length; j++) {
					if(methodsEntity[j].getName().contains("set" + methodsBean[i].getName().substring(3, 4).toUpperCase() + methodsBean[i].getName().substring(4))) {
						Object value = methodsBean[i].invoke(publishAdBean, (Object[]) null);
						methodsEntity[j].invoke(ad, value);
					}
				}
			}
		}
		
		dao.storeAd(ad);
		return ad;
	}
}

