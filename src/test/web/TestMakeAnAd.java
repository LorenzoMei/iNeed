package test.web;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import logic.beans.OrderedAdsBean;
import logic.dao.DAOAd;
import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.viewanad.ViewAnAdController;

public class TestMakeAnAd {
	
	private static final String URL = "http://localhost:8081/iNeed/";
	
	private static final String DRIVER = "webdriver.gecko.driver";
	private static final Class<? extends WebDriver> DRIVER_CLASS = FirefoxDriver.class;
	private static final String DRIVER_PATH = "Drivers/geckodriver";


	@Test
	public void MakeAnAd() {
		
		try {
			System.setProperty(DRIVER, DRIVER_PATH);
			WebDriver driver = DRIVER_CLASS.getConstructor((Class<?>[])null).newInstance((Object[])null);
			
			driver.get(URL);
			
			String username = "prova";
			String password = "123";
			String title = "TITOLO TEST SELENIUM";
			String type = "Request Ad";
			String body = "BODY TEST SELENIUM";
			int newId = -1;
			
			//get last id from database and add 1 because new add has this id
			DAOAd dao = (DAOAd) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.AD);
			if(type.compareTo("OfferAd") == 0){
				newId = dao.getLastOfferId() + 1;
			}
			else if(type.compareTo("RequestAd") == 0){
				newId = dao.getLastRequestId() + 1;
			}
			
			//Log In to iNeed
			driver.findElement(By.xpath(XPaths.LOGIN_FUSERNAME.toString())).sendKeys(username);
			driver.findElement(By.xpath(XPaths.LOGIN_FPASSWORD.toString())).sendKeys(password);
			driver.findElement(By.xpath(XPaths.LOGIN_BLOGIN.toString())).click();
			
			driver.findElement(By.xpath(XPaths.NAVBAR_BMAKEANAD.toString())).click();
			
			//Creation of an ad
			if(type.equals("OfferAd")){
				driver.findElement(By.xpath(XPaths.MAKEANAD_STYPE.toString())).click();
				driver.findElement(By.xpath(XPaths.MAKEANAD_OFFERTYPE.toString())).click();
			}
			else if(type.equals("RequestAd")) {
				driver.findElement(By.xpath(XPaths.MAKEANAD_STYPE.toString())).click();
				driver.findElement(By.xpath(XPaths.MAKEANAD_REQUESTTYPE.toString())).click();
			}
			
			driver.findElement(By.xpath(XPaths.MAKEANAD_FTITLE.toString())).sendKeys(title);
			
			driver.findElement(By.xpath(XPaths.MAKEANAD_SCATEGORY.toString())).click();
			driver.findElement(By.xpath(XPaths.MAKEANAD_GARDERINGCATEGORY.toString())).click();
			driver.findElement(By.xpath(XPaths.MAKEANAD_FBODY.toString())).sendKeys(body);
			
			driver.findElement(By.xpath(XPaths.MAKEANAD_BPUBLISH.toString())).click();
			
			driver.close();
			
			//Compare id
			ViewAnAdController controller = ViewAnAdController.getReference();
			OrderedAdsBean bean = new OrderedAdsBean();
			controller.listAllAds(bean);
			
			for(int i = 0; i < bean.getNumOfAds(); i++) {
				Assert.assertEquals(newId, bean.getId(i));
			}
		
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}

}
