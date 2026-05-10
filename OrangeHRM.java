package LoginPageAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRM {

	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;

	@BeforeTest
	public void setup() {
		System.out.println("Before Test execution");

		driver = new ChromeDriver();

		//maximize window
		driver.manage().window().maximize();

		//test link opened
		driver.get(baseUrl);

		//time to wait 60s (we can also reduce it)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

	}

	@Test (priority = 3)
	public void loginTestWithValidCredantials()
	{
		// find username
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		// find password
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		// login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		String pageTitle = driver.getTitle();

		/*	if(pageTitle.equals("OrangeHMR")) {
			System.out.println("Login successful!");
		} else {
			System.out.println("Login failed!");
		}*/

		Assert.assertEquals("OrangeHRM", pageTitle);

	}

	@Test (priority = 2)
	public void loginTestWithInvalidCredentials() throws Exception
	{
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123456");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		String expected_message = "Invalid credentials";
		String actual_message = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
		
		//Assert.assertTrue(actual_message.contains(expected_message));
		
		Assert.assertEquals(actual_message, expected_message);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		Thread.sleep(1500);	
	}
	
	@Test (priority = 1)
	public void loginWithEmptyFields () throws Exception
	{
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(" ");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(" ");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		
		String empty_field_actual_message = "Required";
		String empty_field_message = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getText();
		
		Assert.assertEquals(empty_field_message, empty_field_actual_message);
		
		Thread.sleep(1500);
	}

	public void logOut ()
	{
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
	}

	@AfterTest
	public void tearDown() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(10000); //wait 10s before quite

		logOut();

		driver.close();
		driver.quit();
	}


}
