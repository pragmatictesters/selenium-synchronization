package com.pragmatic.synchronisation.wait;

import com.pragmatic.selenium.support.ui.WaitForElementFullyExpansion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CollapsibleDivTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (using ChromeDriver as an example)
        driver = new ChromeDriver();

        // Navigate to the button demonstration page
        driver.get("https://pragmatictesters.github.io/selenium-synchronization/collapsible-div.html");

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

    @Test
    public void testButtonsWithExplicitWaitElementToBeClickable() {
        // Click the button to toggle the collapsible div
        driver.findElement(By.tagName("button")).click();

        // Explicit wait to ensure the "About Pragmatic" link is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By byAboutPragmatic = By.partialLinkText("About Pragmatic");
        WebElement aboutPragmaticLink = wait.until(ExpectedConditions.elementToBeClickable(byAboutPragmatic));

        // Click the "About Pragmatic" link after it's clickable
        aboutPragmaticLink.click();

        // Validate that the URL has changed as expected
        Assert.assertEquals(driver.getCurrentUrl(), "https://pragmatictesters.github.io/selenium-synchronization/about.html");
    }


    @Test
    public void testButtonsWithExplicitWaitVisibilityOfElement() {
        // Click the button to toggle the collapsible div
        driver.findElement(By.tagName("button")).click();

        // Explicit wait to ensure the "About Pragmatic" link is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By byAboutPragmatic = By.partialLinkText("About Pragmatic");
        WebElement aboutPragmaticLink = wait.until(ExpectedConditions.visibilityOfElementLocated(byAboutPragmatic));

        // Click the "About Pragmatic" link after it's clickable
        aboutPragmaticLink.click();

        // Validate that the URL has changed as expected
        Assert.assertEquals(driver.getCurrentUrl(), "https://pragmatictesters.github.io/selenium-synchronization/about.html");
    }


    @Test
    public void testButtonsWithExplicitWaitCustomExpectedCondition() {
        // Click the button to toggle the collapsible div
        driver.findElement(By.tagName("button")).click();

        // Explicit wait to ensure the "About Pragmatic" link is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5),Duration.ofMillis(50));
        By byAboutPragmatic = By.partialLinkText("About Pragmatic");
        wait.until(new WaitForElementFullyExpansion(byAboutPragmatic));
        WebElement aboutPragmaticLink = driver.findElement(byAboutPragmatic);

        // Click the "About Pragmatic" link after it's clickable
        aboutPragmaticLink.click();

        // Validate that the URL has changed as expected
        Assert.assertEquals(driver.getCurrentUrl(), "https://pragmatictesters.github.io/selenium-synchronization/about.html");
    }



}