package com.pragmatic.synchronisation.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FluentWaitExampleTest {

    WebDriver driver;
    Wait<WebDriver> wait;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (ChromeDriver used as an example)
        driver = new ChromeDriver();

        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(ElementNotInteractableException.class)
                .withTimeout(Duration.ofSeconds(10))
                .withMessage("Wait is failed");


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
    public void testButtonsWithFluentWait() {
        // Demonstrating explicit waits using WebDriverWait and ExpectedConditions

        // Wait for the "Start" button to be clickable and click it
        log("Waiting for the 'Start' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button00"))).click();

        // Wait for the "One" button to be clickable and click it
        log("Waiting for the 'One' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button01"))).click();

        // Wait for the "Two" button to be clickable and click it
        log("Waiting for the 'Two' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button02"))).click();

        // Wait for the "Three" button to be clickable and click it
        log("Waiting for the 'Three' button to be clickable...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button03"))).click();

        // Verify that the message "All Buttons Clicked" is displayed
        log("Verifying the final message...");
        wait.until(ExpectedConditions.textToBe(By.id("buttonmessage"), "All Buttons Clicked"));
        Assert.assertEquals(driver.findElement(By.id("buttonmessage")).getText(), "All Buttons Clicked");

        // Verify that the message "Click Buttons In Order" is displayed
        log("Verifying the message reset...");
        wait.until(ExpectedConditions.textToBe(By.id("buttonmessage"), "Click Buttons In Order"));
        Assert.assertEquals(driver.findElement(By.id("buttonmessage")).getText(), "Click Buttons In Order");
    }

    @Test
    public void testButtonsWithRefactoredFluentWait() {
        // Using refactored methods to improve code readability

        log("Starting refactored wait method test...");
        waitAndClick(By.id("button00"), "Start");
        waitAndClick(By.id("button01"), "One");
        waitAndClick(By.id("button02"), "Two");
        waitAndClick(By.id("button03"), "Three");

        // Wait and verify the final message
        waitForTextToBe(By.id("buttonmessage"), "All Buttons Clicked");

        // Wait and verify the final message
        waitForTextToBe(By.id("buttonmessage"), "Click Buttons In Order");
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
