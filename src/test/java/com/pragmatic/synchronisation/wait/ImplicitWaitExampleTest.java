package com.pragmatic.synchronisation.wait;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ImplicitWaitExampleTest {

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
    public void testButtonsWithImplicitWait(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("easy00")).click();
        driver.findElement(By.id("easy01")).click();
        driver.findElement(By.id("easy02")).click();
        driver.findElement(By.id("easy03")).click();
        Assert.assertEquals(driver.findElement(By.id("easybuttonmessage")).getText(), "All Buttons Clicked");
    }

    @Test
    public void testButtonsWithDefaultImplicitWait(){
        driver.findElement(By.id("easy001")).click();
        driver.findElement(By.id("easy01")).click();
        driver.findElement(By.id("easy02")).click(); //<<===Test will fail with an exception here
        driver.findElement(By.id("easy03")).click();
        Assert.assertEquals(driver.findElement(By.id("easybuttonmessage")).getText(), "All Buttons Clicked");
    }





}