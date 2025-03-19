package com.pragmatic.synchronisation.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ThreadSleepExampleTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver (using ChromeDriver as an example)
        driver = new ChromeDriver();

        // Navigate to the button demonstration page
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
     * Demonstrates the use of Thread.sleep for synchronization.
     *
     * WARNING!: Thread.sleep is not recommended in real-world scenarios
     * as it introduces unnecessary delays and can make tests brittle.
     * Use Explicit Waits instead for more reliable and efficient synchronization.
     */
    @Test
    public void testButtonsWithThreadSleep() throws InterruptedException {
        // Click the "Start" button and wait for 1 second
        driver.findElement(By.id("easy00")).click();
        Thread.sleep(1000);

        // Click the "One" button and wait for 2 seconds
        driver.findElement(By.id("easy01")).click();
        Thread.sleep(2000);

        // Click the "Two" button and wait for 4 seconds
        driver.findElement(By.id("easy02")).click();
        Thread.sleep(4000);

        // Click the "Three" button and verify the success message
        driver.findElement(By.id("easy03")).click();
        String message = driver.findElement(By.id("easybuttonmessage")).getText();
        Assert.assertEquals(message, "All Buttons Clicked");
    }
}