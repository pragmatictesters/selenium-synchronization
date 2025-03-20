package com.pragmatic.selenium.support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;

public class WaitForElementFullyExpansion implements ExpectedCondition<Boolean> {

    private final By byElement; // Locator for the element to be checked
    private int currentHeight = -1; // Initial value to detect height change
    private static final Logger logger = Logger.getLogger(WaitForElementFullyExpansion.class.getName()); // Logging for debugging purposes

    // Constructor to initialize the element locator
    public WaitForElementFullyExpansion(By byElement) {
        this.byElement = byElement;
    }

    /**
     * This method checks if the height of the element has fully expanded.
     * It keeps comparing the height of the element in every iteration until the height stops changing.
     * Once the height stabilizes, the expansion is considered complete.
     *
     * @param driver WebDriver instance
     * @return true if the element's height no longer changes, indicating full expansion
     */
    @Override
    public Boolean apply(WebDriver driver) {
        WebElement element = driver.findElement(byElement); // Get the element using the locator
        int newHeight = element.getSize().height; // Get the current height of the element

        // Log the current and new height values
        logger.info(String.format("Checking expansion - New Height: %d, Current Height: %d", newHeight, currentHeight));

        // If the new height is greater than the previous height, the element is still expanding
        if (newHeight > currentHeight) {
            currentHeight = newHeight; // Update the current height
            return false; // Keep waiting
        }

        // Element has finished expanding
        return true; // Expansion completed
    }
}