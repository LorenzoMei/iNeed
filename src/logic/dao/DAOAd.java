package logic.dao;

import logic.entity.Ad;
import logic.entity.AdId;

public interface DAOAd {
	public void storeAd(Ad ad);
	public void loadAd(Ad ad, int id) throws AdNotFoundException;
	public void loadId(AdId id);
}
