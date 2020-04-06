package test.dao;

import org.junit.Test;

import logic.dao.DAOFactory;
import logic.dao.DAOFavor;
import logic.dao.DAOSupportedEntities;
import logic.entity.Favor;
import logic.entity.User;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;

public class TestDaoFavor {

	@Test
	public void TestStoreFavor() {
		Favor favor = new Favor();
		User offerer = new User();
		User requester = new User();
		Calendar.Builder dateOfRequestBuilder = new Calendar.Builder();
		dateOfRequestBuilder.setInstant(Calendar.getInstance().getTime());
		Calendar dateOfRequest = dateOfRequestBuilder.build();
		
		offerer.setUsername("tizio");
		requester.setUsername("caio");
		
		favor.setOffererUsername(offerer.getUsername());
		favor.setRequesterUsername(requester.getUsername());
		favor.setDateOfRequest(dateOfRequest);
		System.out.println("date request in test is: " + favor.getDateOfRequest());
		
		DAOFavor dao = (DAOFavor) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.FAVOR);
		dao.storeFavor(favor);
		List<Favor> favors = dao.loadFavors(requester, offerer, dateOfRequest);
		
		Assert.assertEquals(offerer.getUsername(), favors.get(0).getOffererUsername());
		Assert.assertEquals(requester.getUsername(), favors.get(0).getRequesterUsername());
		Assert.assertEquals(dateOfRequest.get(Calendar.YEAR), favors.get(0).getDateOfRequest().get(Calendar.YEAR));
		Assert.assertEquals(dateOfRequest.get(Calendar.MONTH), favors.get(0).getDateOfRequest().get(Calendar.MONTH));
		Assert.assertEquals(dateOfRequest.get(Calendar.DAY_OF_YEAR), favors.get(0).getDateOfRequest().get(Calendar.DAY_OF_YEAR));

	}
}
