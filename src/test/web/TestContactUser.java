package test.web;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import logic.dao.DAOFactory;
import logic.dao.DAOSupportedEntities;
import logic.dao.DAOUser;
import logic.dao.UserNotFoundException;
import logic.entity.User;

public class TestContactUser {

	// Change this constants to use the test with different browsers.
	private static final String DRIVER = "webdriver.gecko.driver";
	private static final Class<? extends WebDriver> DRIVER_CLASS = FirefoxDriver.class;
	private static final String DRIVER_PATH = "/home/daniele/ISPW/ISPW_project/geckodriver-v0.26.0-linux64/geckodriver";
	private static final String TARGET_URL = "http://localhost:8080/iNeed/";
	// Credentials of test user
	private static final String CREDENTIALS_USERNAME = "daniele";
	private static final String CREDENTIALS_PASSWORD = "daniele";
	// XPath of target ad (PLEASE NOTE: In order to enter checkAnswers UC, test user must own the ad)
	private static final String XPATH_AD = "/html/body/div[2]/table/tbody/tr[1]/td[2]/a";
	// XPaths of answer (at least one answer must exist for chosen ad)
	private static final String XPATH_ANSWER_ANSWERER = "//*[@id=\"authorCol\"]";
	private static final String XPATH_ANSWER_BCONTACTUSER = "//*[@id=\"contactUser\"]";
	@Test
	public void testContactUserAlert() {

		try {
			String answererUsernameScraped, contactInfoScraped;
			// Sets up driver
			System.setProperty(DRIVER, DRIVER_PATH);
			WebDriver driver = DRIVER_CLASS.getConstructor((Class<?>[])null).newInstance((Object[])null);
			// Navigates to ViewAd page
			driver.get(TARGET_URL);
			driver.findElement(By.xpath(XPaths.LOGIN_FUSERNAME.toString())).sendKeys(CREDENTIALS_USERNAME);
			driver.findElement(By.xpath(XPaths.LOGIN_FPASSWORD.toString())).sendKeys(CREDENTIALS_PASSWORD);
			driver.findElement(By.xpath(XPaths.LOGIN_BLOGIN.toString())).click();
			driver.findElement(By.xpath(XPATH_AD)).click();
			// Checks if test user is owner of target ad
			String ownerScraped = driver.findElement(By.xpath(XPaths.VIEWAD_LOWNER.toString())).getText();			
			Assert.assertEquals(CREDENTIALS_USERNAME, ownerScraped);
			// Proceeds to CheckAnswers page
			driver.findElement(By.xpath(XPaths.VIEWAD_BCHECKANSWERS.toString())).click();
			answererUsernameScraped = driver.findElement(By.xpath(XPATH_ANSWER_ANSWERER)).getText();
			driver.findElement(By.xpath(XPATH_ANSWER_BCONTACTUSER)).click();
			contactInfoScraped = driver.findElement(By.xpath(XPaths.CHECKANSWERS_CONTACTINFO.toString())).getText();
			driver.close();
			// Retrieves contact info in db of user scraped
			DAOUser daoUser = (DAOUser) DAOFactory.getReference().getDAOReference(DAOSupportedEntities.USER);
			User answerer = new User();
			daoUser.loadUser(answerer, answererUsernameScraped);
			// Checks if contact infos are equals
			Assert.assertEquals(answerer.getEmail(), contactInfoScraped);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | UserNotFoundException e) {
			e.printStackTrace();
		}
	}
}
