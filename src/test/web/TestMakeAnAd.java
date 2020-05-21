package test.web;

import org.junit.Assert;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestMakeAnAd {
	
	private static final String URL = "http://localhost:8081/iNeed/";

	@Test
	public void MakeAnAd() {
		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.get(URL);
		
		String username = "prova";
		String password = "123";
		String title = "TITOLO TEST SELENIUM";
		String category = "Gardering";
		String type = "OfferAd";
		String body = "BODY TEST SELENIUM";
		
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
		
		int i = 1;
		boolean find = true;
		
		//search my ad in ViewFlow
		while(find) {
			try{
				String value = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+ i +"]/td[2]/a")).getText();
				//When i found an ad with the same title and type, check if the rest of the fields are the same 
				if(value.equals(title) && type.equals(driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+ i +"]/td[1]")).getText())){
					Assert.assertEquals(category, driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+ i +"]/td[4]")).getText());
					driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+ i +"]/td[2]/a")).click();
					Assert.assertEquals(body, driver.findElement(By.xpath("//*[@id=\"bodyText\"]")).getText());
					Assert.assertEquals(username, driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div/div[2]/span")).getText());
					break;
				}
				i++;
				
			}catch(NoSuchElementException e) {
				find = false;
			}
				
		}
		
		driver.close();		
	}

}
