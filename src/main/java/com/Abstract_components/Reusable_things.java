package com.Abstract_components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.page_objects.CartPage;
import com.page_objects.OrderPage;

public class Reusable_things {
	WebDriver driver;
	public Reusable_things(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	public CartPage goToCart() {
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public CartPage goToOrdersPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementDisAppear(WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
