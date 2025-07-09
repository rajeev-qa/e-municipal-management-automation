package com.aadrika.egovernance.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aadrika.egovernance.utils.constants.FrameworkConstants;
import java.time.Duration;

/**
 * Base page class with common page operations
 */
public abstract class BasePage {
    
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger;
    
    /**
     * Constructor for BasePage
     * @param driver WebDriver instance
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT_TIMEOUT));
        this.logger = LoggerFactory.getLogger(this.getClass());
    }
    
    /**
     * Waits for element to be visible
     * @param element WebElement to wait for
     * @return WebElement when visible
     */
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Waits for element to be clickable
     * @param element WebElement to wait for
     * @return WebElement when clickable
     */
    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Clicks element after waiting for it to be clickable
     * @param element WebElement to click
     */
    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element).click();
    }
    
    /**
     * Enters text after clearing the field
     * @param element WebElement to enter text
     * @param text Text to enter
     */
    protected void enterText(WebElement element, String text) {
        WebElement visibleElement = waitForElementToBeVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }
    
    /**
     * Gets current page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Gets current page URL
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}