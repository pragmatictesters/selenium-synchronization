package com.pragmatic.synchronisation.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.time.Duration;

public class ImplicitWaitExampleTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Setup WebDriver (no need to set path for driver in the latest versions of Selenium)
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Set implicit wait to handle synchronization
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // 10 seconds of implicit wait
    }

    @Test
    public void testLoginPage() {
        // Open the login page
        driver.get("https://pragmatictesters.github.com/pragmatictesters/selenium-synchronization/index.html");

        // Find the username and password fields and verify they are visible
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        assertNotNull(usernameField);
        assertNotNull(passwordField);
        assertNotNull(loginButton);

        // Fill in the credentials (hardcoded as per the logic in login.html)
        usernameField.sendKeys("demo@pragmatic.com");
        passwordField.sendKeys("password123");

        // Click the login button
        loginButton.click();

        // Wait for the next page to load (implicitly wait will be used)
        try {
            // Wait until a product element or page content appears after login (implicit wait will handle this)
            WebElement productContainer = driver.findElement(By.className("product-container"));
            assertNotNull(productContainer, "Product page should have loaded!");
        } catch (TimeoutException e) {
            fail("Timeout: Product page did not load in time.");
        }
    }

    @Test
    public void testIncorrectLogin() {
        // Open the login page
        driver.get("https://pragmatictesters.github.com/pragmatictesters/selenium-synchronization/index.html");

        // Find the username and password fields and verify they are visible
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        // Fill in incorrect credentials
        usernameField.sendKeys("wrong@pragmatic.com");
        passwordField.sendKeys("wrongpassword");

        // Click the login button
        loginButton.click();

        // Wait for the error message to appear
        try {
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for incorrect login");
        } catch (TimeoutException e) {
            fail("Timeout: Error message did not appear in time.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the browser after the test
        }
    }
}