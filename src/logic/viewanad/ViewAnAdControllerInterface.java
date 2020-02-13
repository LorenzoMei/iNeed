package logic.viewanad;

import logic.beans.OrderedAdsBean;

public interface ViewAnAdControllerInterface {
	public static final String UNSORTED = "";
	public static final String BY_TIME = "ByTime";
	
	public void listAllAds(OrderedAdsBean bean);
}
