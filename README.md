# Login-Page-Automation-Testing
Automated login functionality using Selenium

Project Structure:
LoginAutomationProject/
│── src/
│   ├── main/
│   │   └── pages/
│   │       └── LoginPage.java
│   ├── test/
│   │   └── tests/
│   │       └── LoginTest.java
│   ├── utils/
│   │       └── BaseTest.java
│
│── pom.xml
│── testng.xml
│── README.md

package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By username = By.id("username");
    By password = By.id("password");
    By loginBtn = By.cssSelector("button[type='submit']");
    By message = By.id("flash");

    // Actions
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public String getMessage() {
        return driver.findElement(message).getText();
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage login = new LoginPage(driver);

        login.enterUsername("tomsmith");
        login.enterPassword("SuperSecretPassword!");
        login.clickLogin();

        String msg = login.getMessage();
        Assert.assertTrue(msg.contains("You logged into a secure area!"));
    }

    @Test
    public void invalidLoginTest() {
        LoginPage login = new LoginPage(driver);

        login.enterUsername("wrongUser");
        login.enterPassword("wrongPass");
        login.clickLogin();

        String msg = login.getMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }

    @Test
    public void emptyLoginTest() {
        LoginPage login = new LoginPage(driver);

        login.enterUsername("");
        login.enterPassword("");
        login.clickLogin();

        String msg = login.getMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"));
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
