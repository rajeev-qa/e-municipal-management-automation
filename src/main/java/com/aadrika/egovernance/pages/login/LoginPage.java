package com.aadrika.egovernance.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aadrika.egovernance.pages.common.BasePage;

/**
 * Login page class with page object model
 */
public class LoginPage extends BasePage {
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "loginButton")
    private WebElement loginButton;
    
    /**
     * Constructor for LoginPage
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Performs login with credentials
     * @param username Username to login
     * @param password Password to login
     * @return LoginPage instance for method chaining
     */
    public LoginPage performLogin(String username, String password) {
        try {
            enterText(usernameField, username);
            enterText(passwordField, password);
            clickElement(loginButton);
            logger.info("Login performed for user: {}", username);
            return this;
        } catch (Exception e) {
            logger.error("Error during login: {}", e.getMessage());
            throw new RuntimeException("Login failed", e);
        }
    }
    
    /**
     * Verifies if login was successful
     * @param expectedUrl Expected URL after login
     * @return true if login successful, false otherwise
     */
    public boolean isLoginSuccessful(String expectedUrl) {
        try {
            return getCurrentUrl().equals(expectedUrl);
        } catch (Exception e) {
            logger.error("Error checking login status: {}", e.getMessage());
            return false;
        }
    }
}