package test.web;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestValidateAFavor {

	private static final String URL = "http://localhost:8081/iNeed/";
	private static final String SUCCESS = "Favor validated successfully";
	
	@Test
	public void ValidateAFavor() {
		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
		WebDriver driver = new FirefoxDriver();
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
	}

}
