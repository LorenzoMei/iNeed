package logic.publishanad;

import java.lang.reflect.InvocationTargetException;

public interface PublishAnAdInterface {

	public void createAd(PublishAnAdBean publishAdBean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}
