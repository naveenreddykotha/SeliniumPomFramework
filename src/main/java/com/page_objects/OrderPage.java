package com.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends CartPage {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean value= productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		return value;
	}

}
