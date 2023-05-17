package com.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Abstract_components.Reusable_things;

public class CartPage extends Reusable_things{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean value= cartProducts.stream().anyMatch(cartProduct->cartProduct.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
		return value;
	}
	public CheckoutPage gotocheckoutPage() {
		checkout.click();
		return new CheckoutPage(driver);
	}
	
	

	

}
