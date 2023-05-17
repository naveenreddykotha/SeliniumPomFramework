package com.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Abstract_components.Reusable_things;

public class LandingPage extends Reusable_things {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//using page factory we can reduce syntax of webelements
	//page factory exclusively for driver. constructing elements...
	
	//WebElement userEmail=driver.findElement(By.xpath("//input[@id='userEmail']"));
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement userPassword;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errMessage;
	
	//Actions methods
	public ProductCantalouge applicationLogin(String username,String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCantalouge(driver);
	}
	
	public String geterrmessage() {
		waitForWebElementToAppear(errMessage);
		return errMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
