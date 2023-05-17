package seleniumCode;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestComponents.BaseTest;
import com.page_objects.CartPage;
import com.page_objects.ProductCantalouge;

public class ErrorValidations extends BaseTest {
	
	@Test(groups= {"errorHandling"})
	public void LoginerrValidation() {
		page.applicationLogin("naveen123@gmail.com", "Naveen@12");
		
		Assert.assertEquals("Incorrect email  password.",page.geterrmessage());
	}
	@Test
	public void ProductErrValidation() {
		String productName="ZARA COAT 3";
		ProductCantalouge productCatalouge= page.applicationLogin("naveen123@gmail.com", "Naveen@123");
		//ProductCantalouge productCatalouge=new ProductCantalouge(driver);
		List<WebElement>products= productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
		CartPage cart= productCatalouge.goToCart();
		//CartPage cart=new CartPage(driver);
		Boolean value= cart.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(value);
	}
	
	
}
