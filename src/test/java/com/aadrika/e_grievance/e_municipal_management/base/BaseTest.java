package com.aadrika.e_grievance.e_municipal_management.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aadrika.e_grievance.e_municipal_management.utils.TestDataUtil;
import com.aadrika.e_grievance.e_municipal_management.utils.WebDriverUtil;

/**
 * Abstract base test class providing common setup and teardown functionality
 * All test classes should extend this class for consistent test execution
 */
public abstract class BaseTest {
    // Logger instance for logging test events
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    // WebDriver instance available to all extending test classes
    protected WebDriver driver;

    /**
     * Setup method executed before each test method
     * Initializes WebDriver and navigates to base URL
     */
    @BeforeMethod
    public void setUp() {
        try {
            // Get WebDriver instance from utility
            driver = WebDriverUtil.getDriver();
            // Navigate to application base URL
            driver.get(TestDataUtil.getBaseUrl());
            logger.info("Test setup completed successfully");
        } catch (Exception e) {
            logger.error("Error during test setup: {}", e.getMessage());
            throw new RuntimeException("Failed to setup test", e);
        }
    }

    /**
     * Teardown method executed after each test method
     * Closes browser and cleans up resources
     */
    @AfterMethod
    public void tearDown() {
        try {
            // Quit WebDriver and cleanup resources
            WebDriverUtil.quitDriver();
            logger.info("Browser closed successfully");
        } catch (Exception e) {
            logger.error("Error during teardown: {}", e.getMessage());
        }
    }
}