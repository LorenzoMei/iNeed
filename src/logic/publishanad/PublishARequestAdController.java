package logic.publishanad;

import logic.dao.DAOFactory;
import logic.entity.Ad;
import logic.entity.AdId;

import java.lang.reflect.InvocationTargetException;

import logic.dao.DAOAd;

public class PublishARequestAdController implements PublishAnAdInterface{
	
	private static PublishARequestAdController instance;
	
	public static PublishARequestAdController getInstance() {
		if(instance == null)
			instance = new PublishARequestAdController();
		return instance;
	}
	
	private PublishARequestAdController() {
	}
	
	public Ad createAd(PublishAnAdBean publishAdBean) throws IllegalAccessException, InvocationTargetException {
		
		DAOAd dao = (DAOAd) DAOFactory.getReference().getDAOReference("Ad");
		
		Ad ad = new Ad();
		AdId id = new AdId();
		Data data = new Data();
		
		dao.loadId(id);
				
		ad.setId(id.getId());
		ad.setData(data.buildDate());
		ad.setType("Request");
		
		GetAndSetValue setEntity = new GetAndSetValue();	
		setEntity.getBeanSetEntity(publishAdBean, data);
		
		dao.storeAd(ad);
		return ad;
	}
}
