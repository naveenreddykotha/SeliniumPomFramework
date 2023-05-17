package seleniumCode;

import java.time.Duration;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		WebDriverManager.chromedriver().driverVersion("111.0.5563.64").setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("naveen123@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Naveen@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		String name=driver.findElement(By.cssSelector(".mb-3 b")).getText();
		System.out.println(name);
		WebElement prod= products.stream()
		.filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement>cartProducts= driver.findElements(By.xpath("//*[contains(@class,'cartSection')]"));
		Boolean value= cartProducts.stream().anyMatch(cartProduct->cartProduct.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
		Assert.assertTrue(value);
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		WebElement element = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", element);
		//driver.close();
	}

}
