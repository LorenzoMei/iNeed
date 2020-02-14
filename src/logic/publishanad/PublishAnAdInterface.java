package logic.publishanad;

import java.lang.reflect.InvocationTargetException;

import logic.beans.PublishAnAdBean;
import logic.entity.Ad;

public interface PublishAnAdInterface {

	public Ad createAd(PublishAnAdBean publishAdBean) throws IllegalAccessException, InvocationTargetException;

}