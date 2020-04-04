package logic.dao;

import java.util.Calendar;
import java.util.List;

import logic.entity.Ad;
import logic.entity.Favor;
import logic.entity.User;

public interface DAOFavor {
	public void storeFavor(Favor favor);
	public List<Favor> loadFavors(User offerer, User requester, Calendar dateOfRequest);
	public void loadFavor (Favor favor, User offerer, User requester, Calendar dateOfRequest) throws FavorNotFoundException;
	public void deleteFavor(User offerer, User requester, Calendar dateOfRequest);
	List<Favor> loadFavorOfRequestAd(User requester, Ad ad);
}
