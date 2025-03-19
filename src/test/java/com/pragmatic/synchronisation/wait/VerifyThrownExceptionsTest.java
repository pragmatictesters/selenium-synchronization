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

public class VerifyThrownExceptionsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (using ChromeDriver as an example)
        driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://pragmatictesters.github.io/selenium-synchronization/buttons.html");

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close the browser if the driver is not null
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test to verify the NoSuchElementException is thrown when an element is not found.
     */
    @Test
    public void testNoSuchElementException() {
        // Click the "Start" button
        driver.findElement(By.id("easy00")).click();

        // Assert that a NoSuchElementException is thrown when attempting to find a non-existent element
        Assert.assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id("easy011")).click();
        });
    }

    /**
     * Test to verify the ElementNotInteractableException is thrown when an element is not interactable.
     */
    @Test
    public void testElementNotInteractableException() {
        // Assert that an ElementNotInteractableException is thrown when trying to interact with a hidden element
        Assert.assertThrows(ElementNotInteractableException.class, () -> {
            driver.findElement(By.id("easy01")).click();
        });
    }
}