package com.aadrika.e_grievance.e_municipal_management;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aadrika.e_grievance.e_municipal_management.base.BaseTest;
import com.aadrika.e_grievance.e_municipal_management.utils.TestDataUtil;

/**
 * GrievanceTest class contains test cases for grievance registration functionality
 * Extends BaseTest for common setup and teardown operations
 */
public class GrievanceTest extends BaseTest {
    // Page object for grievance operations
    private GrievancePage grievancePage;
    // Utility for solving CAPTCHA
    private CaptchaSolver captchaSolver;

    /**
     * Test setup method to initialize page objects
     * Runs before each test method
     */
    @BeforeMethod
    public void setUpTest() {
        super.setUp();
        try {
            grievancePage = new GrievancePage(driver);
            captchaSolver = new CaptchaSolver(driver);
            logger.info("Grievance test setup completed");
        } catch (Exception e) {
            logger.error("Error during grievance test setup: {}", e.getMessage());
            throw new RuntimeException("Failed to setup grievance test", e);
        }
    }

    /**
     * Test case for grievance registration through dialog box
     * Tests the complete flow from dialog box to grievance submission
     */
    @Test
    public void testDialogBoxRegisterGrievance() {
        try {
            grievancePage.D_box();
            grievancePage.D_regester_grivance_wl();
            grievancePage.Grievance_Description(TestDataUtil.getGrievanceDescription());
            grievancePage.Location(TestDataUtil.getGrievanceLocation());
            grievancePage.Upload_doc();
            grievancePage.Basic_Information(
                    TestDataUtil.getUserName(),
                    TestDataUtil.getUserPhone(),
                    TestDataUtil.getUserEmail(),
                    TestDataUtil.getProperty("user.hospital"),
                    TestDataUtil.getProperty("user.aadhar"),
                    TestDataUtil.getProperty("user.saf"),
                    TestDataUtil.getProperty("user.con")
            );
            
            captchaSolver.solveCaptcha();
            grievancePage.solveCaptchaAndSubmit();
            
            logger.info("Grievance registration test completed successfully");
        } catch (Exception e) {
            logger.error("Error during grievance registration test: {}", e.getMessage());
            throw new RuntimeException("Test failed", e);
        }
    }


}
