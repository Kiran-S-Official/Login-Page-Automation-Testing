# Login-Page-Automation-Testing
Automated login functionality using Selenium

package Pages;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

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
		Thread.sleep(10000); //wait 10s before quite

		logOut();

		driver.close();
		driver.quit();
	}
}


<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Login Test Suite">
    <test name="Login Tests">
        <classes>
            <class name="tests.LoginTest"/>
        </classes>
    </test>
</suite>

<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>LoginAutomation</groupId>
    <artifactId>LoginAutomation</artifactId>
    <version>1.0</version>

    <dependencies>
        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.18.1</version>
        </dependency>

        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.8.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>




# Login Automation Testing Project

Overview
This project automates login functionality using Selenium WebDriver and TestNG.

Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven

Test Scenarios
- Valid Login
- Invalid Login
- Empty Credentials

Concepts Used
- Page Object Model (POM)
- Explicit Waits 
- TestNG Annotations

How to Run
1. Clone the repo
2. Run testng.xml

Author
Kiran S
