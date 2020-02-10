package logic.dao;

import logic.entity.Ad;

public interface DAOAd {
	
	public void loadAd(Ad ad, int id) throws AdNotFoundException;
	public void storeAd(Ad ad);
	
}
