package com.aadrika.egovernance.tests.smoke;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aadrika.egovernance.base.BaseTest;
import com.aadrika.egovernance.pages.grievance.GrievanceRegistrationPage;
import com.aadrika.egovernance.utils.driver.DriverManager;
import com.aadrika.egovernance.utils.helpers.ConfigReader;

/**
 * Smoke tests for grievance functionality
 */
public class GrievanceSmokeTest extends BaseTest {
    
    private GrievanceRegistrationPage grievancePage;
    
    @BeforeMethod
    public void setUpTest() {
        grievancePage = new GrievanceRegistrationPage(DriverManager.getDriver());
    }
    
    /**
     * Smoke test for grievance registration dialog
     */
    @Test(groups = {"smoke", "grievance"})
    public void testGrievanceDialogDisplay() {
        try {
            grievancePage.verifyDialogBoxDisplay();
            logger.info("Grievance dialog smoke test completed successfully");
        } catch (Exception e) {
            logger.error("Grievance dialog smoke test failed: {}", e.getMessage());
            throw new RuntimeException("Smoke test failed", e);
        }
    }
}