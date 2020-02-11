package logic.publishanad;

import logic.dao.DAOFactory;
import logic.entity.Ad;
import logic.support.AdId;
import logic.support.Data;
import logic.support.ValueEntity;

import java.lang.reflect.InvocationTargetException;

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
		
		ValueEntity setEntity = new ValueEntity();
		setEntity.getFromBeanAndSetEntity(publishAdBean, data);
		
		dao.storeAd(ad);
		return ad;
	}
}

