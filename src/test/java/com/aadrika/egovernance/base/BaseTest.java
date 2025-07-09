package com.aadrika.egovernance.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aadrika.egovernance.enums.BrowserType;
import com.aadrika.egovernance.utils.driver.DriverManager;
import com.aadrika.egovernance.utils.helpers.ConfigReader;

/**
 * Enhanced base test class with environment and browser management
 */
public abstract class BaseTest {
    
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    
    /**
     * Setup method with optional browser parameter
     * @param browser Browser type from TestNG parameter
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        try {
            // Determine browser type
            BrowserType browserType = getBrowserType(browser);
            
            // Initialize driver
            DriverManager.initializeDriver(browserType);
            
            // Navigate to base URL
            DriverManager.getDriver().get(ConfigReader.getBaseUrl());
            
            logger.info("Test setup completed - Environment: {}, Browser: {}", 
                    ConfigReader.getCurrentEnvironment(), browserType);
                    
        } catch (Exception e) {
            logger.error("Error during test setup: {}", e.getMessage());
            throw new RuntimeException("Failed to setup test", e);
        }
    }
    
    /**
     * Overloaded setup method without parameters
     */
    @BeforeMethod
    public void setUp() {
        setUp(ConfigReader.getBrowserType());
    }
    
    /**
     * Teardown method
     */
    @AfterMethod
    public void tearDown() {
        try {
            DriverManager.quitDriver();
            logger.info("Test teardown completed successfully");
        } catch (Exception e) {
            logger.error("Error during teardown: {}", e.getMessage());
        }
    }
    
    /**
     * Gets browser type from string parameter
     * @param browser Browser string
     * @return BrowserType enum
     */
    private BrowserType getBrowserType(String browser) {
        if (browser == null) {
            browser = ConfigReader.getBrowserType();
        }
        
        try {
            return BrowserType.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid browser type: {}. Using CHROME as default", browser);
            return BrowserType.CHROME;
        }
    }
}