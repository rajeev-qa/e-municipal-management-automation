package com.aadrika.egovernance.pages.grievance;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aadrika.egovernance.pages.common.BasePage;

/**
 * Grievance registration page with PageFactory pattern
 */
public class GrievanceRegistrationPage extends BasePage {
    
    @FindBy(id = "radix-:rp:")
    private WebElement dialogBox;
    
    @FindBy(xpath = "//*[@id='radix-:rl:']/div[2]/div[1]/button")
    private WebElement registerButton;
    
    @FindBy(xpath = "//*[@id='terms']")
    private WebElement termsCheckbox;
    
    @FindBy(xpath = "//*[@id='radix-:rt:']/div/form[1]/div/div[1]/div/div[1]/textarea")
    private WebElement descriptionTextArea;
    
    /**
     * Constructor for GrievanceRegistrationPage
     * @param driver WebDriver instance
     */
    public GrievanceRegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Verifies dialog box is displayed
     * @return true if dialog box is visible
     */
    public boolean verifyDialogBoxDisplay() {
        try {
            waitForElementToBeVisible(dialogBox);
            logger.info("Dialog box is displayed");
            return dialogBox.isDisplayed();
        } catch (Exception e) {
            logger.error("Dialog box not found: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Clicks register grievance button
     * @return GrievanceRegistrationPage for method chaining
     */
    public GrievanceRegistrationPage clickRegisterButton() {
        clickElement(registerButton);
        logger.info("Register button clicked");
        return this;
    }
    
    /**
     * Accepts terms and conditions
     * @return GrievanceRegistrationPage for method chaining
     */
    public GrievanceRegistrationPage acceptTerms() {
        clickElement(termsCheckbox);
        logger.info("Terms checkbox selected");
        return this;
    }
    
    /**
     * Enters grievance description
     * @param description Description text
     * @return GrievanceRegistrationPage for method chaining
     */
    public GrievanceRegistrationPage enterDescription(String description) {
        enterText(descriptionTextArea, description);
        logger.info("Grievance description entered");
        return this;
    }
}