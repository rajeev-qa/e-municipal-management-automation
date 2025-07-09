package com.aadrika.e_grievance.e_municipal_management.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Utility class for WebDriver management using Singleton pattern
 * Provides centralized WebDriver instance creation and management
 */
public class WebDriverUtil {
    // Static WebDriver instance for singleton pattern
    private static WebDriver driver;
    
    /**
     * Gets WebDriver instance using singleton pattern
     * Creates new instance if not already initialized
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    
    /**
     * Initializes WebDriver with Chrome browser and options
     * Sets up ChromeDriver with disabled notifications and popup blocking
     */
    private static void initializeDriver() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        // Configure Chrome options for better test execution
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        // Initialize ChromeDriver with options
        driver = new ChromeDriver(options);
        // Maximize browser window
        driver.manage().window().maximize();
    }
    
    /**
     * Quits WebDriver and sets instance to null
     * Ensures proper cleanup of browser resources
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}