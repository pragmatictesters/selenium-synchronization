package com.pragmatic.synchronisation.wait;

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

public class ExpectedConditionOrTest {

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
    public void testButtonsWithExpectedConditionsOr() {
        // Click the button to toggle the collapsible div
        driver.findElement(By.tagName("button")).click();

        // Wait until the "About Pragmatic" link is either present or visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By byAboutPragmatic = By.partialLinkText("About Pragmatic");

        // Apply "OR" condition - either presence or visibility of the element
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(byAboutPragmatic),
                ExpectedConditions.visibilityOfElementLocated(byAboutPragmatic)
        ));

        WebElement aboutPragmaticLink = driver.findElement(byAboutPragmatic);

        // Click the "About Pragmatic" link after it's ready
        aboutPragmaticLink.click();

        // Validate that the URL has changed as expected
        Assert.assertEquals(driver.getCurrentUrl(), "https://pragmatictesters.github.io/selenium-synchronization/about.html");
    }
}
