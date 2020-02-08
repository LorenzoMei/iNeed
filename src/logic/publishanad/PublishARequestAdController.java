package logic.publishanad;

import logic.dao.DAOFactory;
import logic.entity.Ad;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	
	public void createAd(PublishAnAdBean publishAdBean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<Object> valueAttributesBean = new ArrayList<>();
		
		Method[] methodsOfAdBean = publishAdBean.getClass().getMethods();
		
		//TODO same functionality as getGetterOrSetter in DAOSerialize
		
		for(int i = 0; i < publishAdBean.getClass().getDeclaredFields().length; i++) {
			if(methodsOfAdBean[i].getName().contains("get")) 
				valueAttributesBean.add(methodsOfAdBean[i].invoke(publishAdBean, (Object[]) null));
		}
		
		valueAttributesBean.add(LocalDate.now());
		valueAttributesBean.add("Request");
				
		Ad ad = new Ad();
		Field[] attributes = ad.getClass().getDeclaredFields();
		Method[] methodsOfAd = ad.getClass().getMethods();
		
		for(int i = 0; i < attributes.length; i++) {
			if(methodsOfAd[i].getName().contains("set"))
				methodsOfAd[i].invoke(ad, valueAttributesBean.get(i));
		}
		
		DAOAd dao = (DAOAd) DAOFactory.getReference("Ad").getDAOReference();
		dao.storeAd(ad);		
	}
}
