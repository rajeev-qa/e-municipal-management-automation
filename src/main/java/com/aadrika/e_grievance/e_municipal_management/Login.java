package com.aadrika.e_grievance.e_municipal_management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * Login page class for handling user authentication
 * Provides methods to perform login operations and verify login status
 */
public class Login {
    // Logger instance for logging events and errors
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    // WebDriver instance for browser interactions
    private final WebDriver driver;
    // WebDriverWait instance for explicit waits
    private final WebDriverWait wait;

    /**
     * Constructor to initialize Login page with WebDriver
     * @param driver WebDriver instance for browser operations
     */
    public Login(WebDriver driver) {
        this.driver = driver;
        // Initialize wait with 10 seconds timeout
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Performs login operation with provided credentials
     * @param username User's login username
     * @param password User's login password
     * @throws RuntimeException if login process fails
     */
    public void performLogin(String username, String password) {
        try {
            // Wait for username field to be visible and enter username
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.clear();
            usernameField.sendKeys(username);

            // Wait for password field to be visible and enter password
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.clear();
            passwordField.sendKeys(password);

            // Wait for login button to be clickable and click it
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
            loginButton.click();
            
            logger.info("Login attempt completed for user: {}", username);
        } catch (Exception e) {
            logger.error("Error during login: {}", e.getMessage());
            throw new RuntimeException("Login failed", e);
        }
    }

    /**
     * Verifies if login was successful by checking current URL
     * @param expectedUrl Expected URL after successful login
     * @return true if current URL matches expected URL, false otherwise
     */
    public boolean isLoginSuccessful(String expectedUrl) {
        try {
            return driver.getCurrentUrl().equals(expectedUrl);
        } catch (Exception e) {
            logger.error("Error checking login status: {}", e.getMessage());
            return false;
        }
    }
}
