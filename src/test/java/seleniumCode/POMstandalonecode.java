package seleniumCode;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TestComponents.BaseTest;
import com.page_objects.CartPage;
import com.page_objects.CheckoutPage;
import com.page_objects.ConfirmationPage;
import com.page_objects.LandingPage;
import com.page_objects.OrderPage;
import com.page_objects.ProductCantalouge;

import io.github.bonigarcia.wdm.WebDriverManager;

public class POMstandalonecode extends BaseTest {
	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups = {"purchase"})
	public void submitorder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		//driver.get("https://rahulshettyacademy.com/client");
		
		//LandingPage page= launchApplication();
		ProductCantalouge productCatalouge= page.applicationLogin(input.get("email"),input.get("password"));
		//ProductCantalouge productCatalouge=new ProductCantalouge(driver);
		List<WebElement>products= productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("productName"));
		CartPage cart= productCatalouge.goToCart();
		//CartPage cart=new CartPage(driver);
		Boolean value= cart.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(value);
		CheckoutPage checkoutPage= cart.gotocheckoutPage();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmPage= checkoutPage.submitOrder();
		String confirmMessage= confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//		WebElement element = driver.findElement(By.cssSelector(".action__submit"));
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", element);
		//driver.close();
	}
	
	@Test(dependsOnMethods={"submitorder"})
	public void OrderHistoryTest() {
		ProductCantalouge productCatalouge= page.applicationLogin("naveen123@gmail.com", "Naveen@123");
		OrderPage orderPage= (OrderPage) productCatalouge.goToOrdersPage();
		//orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() {
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "naveen123@gmail.com");
		map.put("password", "Naveen@123");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "Nikhil123@gmail.com");
		map1.put("password", "Nikhil@123");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}
	
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"naveen123@gmail.com","Naveen@123","ZARA COAT 3"},{"Nikhil123@gmail.com","Nikhil@123","ADIDAS ORIGINAL"}};
//	}
	

}
