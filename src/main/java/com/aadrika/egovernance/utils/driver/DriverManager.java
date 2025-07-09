package com.aadrika.egovernance.utils.driver;

import org.openqa.selenium.WebDriver;
import com.aadrika.egovernance.enums.BrowserType;

/**
 * Thread-safe WebDriver manager using ThreadLocal
 */
public final class DriverManager {
    
    private DriverManager() {}
    
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    /**
     * Gets WebDriver instance for current thread
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
    
    /**
     * Sets WebDriver instance for current thread
     * @param driver WebDriver instance to set
     */
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }
    
    /**
     * Initializes WebDriver for specified browser
     * @param browserType Browser type to initialize
     */
    public static void initializeDriver(BrowserType browserType) {
        WebDriver driver = DriverFactory.createDriver(browserType);
        setDriver(driver);
    }
    
    /**
     * Quits WebDriver and removes from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}