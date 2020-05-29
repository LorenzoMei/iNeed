package test.web;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestValidateAFavor {

	private static final String URL = "http://localhost:8081/iNeed/";
	private static final String SUCCESS = "Favor validated successfully";
	
	private static final String DRIVER = "webdriver.gecko.driver";
	private static final Class<? extends WebDriver> DRIVER_CLASS = FirefoxDriver.class;
	private static final String DRIVER_PATH = "Drivers/geckodriver";
	
	@Test
	public void ValidateAFavor() {
		
		try {
			System.setProperty(DRIVER, DRIVER_PATH);
			WebDriver driver = DRIVER_CLASS.getConstructor((Class<?>[])null).newInstance((Object[])null);
			
			driver.get(URL);
			
			String username = "prova";
			String password = "123";
			
			//Log In to iNeed
			driver.findElement(By.xpath(XPaths.LOGIN_FUSERNAME.toString())).sendKeys(username);
			driver.findElement(By.xpath(XPaths.LOGIN_FPASSWORD.toString())).sendKeys(password);
			driver.findElement(By.xpath(XPaths.LOGIN_BLOGIN.toString())).click();
			
			//Validate a favor
			driver.findElement(By.xpath(XPaths.VALIDATEAFAVOR_BVALIDATEAFAVOR.toString())).click();
			driver.findElement(By.xpath(XPaths.VALIDATEAFAVOR_BVALIDATE.toString())).click();
			driver.findElement(By.xpath(XPaths.VALIDATEAFAVOR_BGO.toString())).click();
			
			String result = driver.findElement(By.xpath(XPaths.VALIDATEAFAVOR_TRESULT.toString())).getText();
			Assert.assertEquals(SUCCESS, result);
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}

}
