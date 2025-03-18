package com.pragmatic.synchronisation.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifyThrownExceptionsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (use ChromeDriver as an example)
        driver = new ChromeDriver();

        // Navigate to login page
        driver.get("https://pragmatictesters.github.io/selenium-synchronization/buttons.html");

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void testNoSuchElementException() {
        driver.findElement(By.id("easy00")).click();
        Assert.assertThrows(NoSuchElementException.class,
                () -> {
                    driver.findElement(By.id("easy011")).click();
                });
    }

    @Test
    public void testElementNotInteractableException() {
        Assert.assertThrows(ElementNotInteractableException.class,
                () -> {
                    driver.findElement(By.id("easy01")).click();
                });
    }


}
