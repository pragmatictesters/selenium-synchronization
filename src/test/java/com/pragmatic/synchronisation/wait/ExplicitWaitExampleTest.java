package com.pragmatic.synchronisation.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExplicitWaitExampleTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (ChromeDriver used as an example)
        driver = new ChromeDriver();

        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the button synchronization page
        driver.get("https://pragmatictesters.github.io/selenium-synchronization/buttons.html");

        // Maximize the browser window
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
    public void testButtonsWithExplicitWait() {
        // Demonstrating explicit waits using WebDriverWait and ExpectedConditions

        // Wait for the "Start" button to be clickable and click it
        log("Waiting for the 'Start' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("easy00"))).click();

        // Wait for the "One" button to be clickable and click it
        log("Waiting for the 'One' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("easy01"))).click();

        // Wait for the "Two" button to be clickable and click it
        log("Waiting for the 'Two' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("easy02"))).click();

        // Wait for the "Three" button to be clickable and click it
        log("Waiting for the 'Three' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("easy03"))).click();

        // Verify that the message "All Buttons Clicked" is displayed
        log("Verifying the final message...");
        Assert.assertEquals(driver.findElement(By.id("easybuttonmessage")).getText(), "All Buttons Clicked");
    }

    @Test
    public void testButtonsWithRefactoredWaitMethods() {
        // Using refactored methods to improve code readability

        log("Starting refactored wait method test...");
        waitAndClick(By.id("easy00"), "Start");
        waitAndClick(By.id("easy01"), "One");
        waitAndClick(By.id("easy02"), "Two");
        waitAndClick(By.id("easy03"), "Three");

        // Wait and verify the final message
        waitForTextToBe(By.id("easybuttonmessage"), "All Buttons Clicked");
    }

    private void waitAndClick(By elementBy, String buttonName) {
        log("Waiting for the '" + buttonName + "' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(elementBy)).click();
        log("Clicked the '" + buttonName + "' button.");
    }

    private void waitForTextToBe(By elementBy, String expectedText) {
        log("Waiting for text to be '" + expectedText + "'...");
        wait.until(ExpectedConditions.textToBe(elementBy, expectedText));
        log("Text verified: '" + expectedText + "'.");
    }

    private void log(String message) {
        System.out.println("[INFO] " + message);
    }
}