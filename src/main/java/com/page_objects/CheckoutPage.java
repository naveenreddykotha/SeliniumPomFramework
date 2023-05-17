package com.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Abstract_components.Reusable_things;

public class CheckoutPage extends Reusable_things{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
		
		
		//WebElement element = driver.findElement(By.cssSelector(".action__submit"));
	}
	public ConfirmationPage submitOrder() {
		//submit.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
	}
	

}
