package com.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Abstract_components.Reusable_things;

public class ProductCantalouge extends Reusable_things {
	
	WebDriver driver;
	
	public ProductCantalouge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	By productsBy=By.cssSelector(".mb-3");
	By addCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement invisible;
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod= getProductList().stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod=getProductByName(productName);
		prod.findElement(addCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementDisAppear(invisible);
		//here its takes more time to load to go for fast use thrread.sleep(2000) and comment above 2 waits..
	}
}
