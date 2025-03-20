package com.pragmatic.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTemplateTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (using ChromeDriver as an example)
        driver = new ChromeDriver();

        // Navigate to the button demonstration page
        driver.get("https://pragmatictesters.github.io/selenium-synchronization/");

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
    public void testButtonsWithThreadSleep() throws InterruptedException {

    }
}