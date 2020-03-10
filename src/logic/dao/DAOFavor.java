package logic.dao;

import java.util.Calendar;
import java.util.List;

import logic.entity.Favor;
import logic.entity.User;

public interface DAOFavor {
	public void storeFavor(Favor favor);
	public List<Favor> loadFavors(User offerer, User requester, Calendar dateOfRequest);
}
