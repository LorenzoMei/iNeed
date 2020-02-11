package logic.publishanad;

import logic.dao.DAOFactory;
import logic.entity.Ad;
import logic.entity.AdFactory;
import logic.entity.AdId;
import logic.entity.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import logic.dao.DAOAd;

public class PublishAnAdController implements PublishAnAdInterface{
	
	private static PublishAnAdController instance;
	
	public static PublishAnAdController getInstance() {
		if(instance == null)
			instance = new PublishAnAdController();
		return instance;
	}
	
	private PublishAnAdController() {
	}
	
	public Ad createAd(PublishAnAdBean publishAdBean) throws IllegalAccessException, InvocationTargetException {
		
		Ad ad = AdFactory.getReference().typePost(publishAdBean.getType());
		
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
		
		Data data = new Data();
		ad.setData(data.buildDate());	
		
		AdId id = new AdId();
		DAOAd dao = (DAOAd) DAOFactory.getReference().getDAOReference("Ad");
		
		dao.loadId(id);
		ad.setId(id.getId());
		
		dao.storeAd(ad);
		return ad;
	}
}
