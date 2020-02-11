package logic.dao;

import logic.entity.RequestAd;
import logic.entity.AdId;

public interface DAOAd {
	
	public void loadAd(RequestAd ad, int id) throws AdNotFoundException;
	public void storeAd(RequestAd ad);
	public void loadId(AdId id);
}
