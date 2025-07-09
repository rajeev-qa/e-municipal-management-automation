package com.aadrika.e_grievance.e_municipal_management;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * GrievancePage class handles all grievance registration operations
 * Provides methods for dialog box handling, form filling, and submission
 */
public class GrievancePage {

    // WebDriver instance for browser interactions
    WebDriver driver;
    // CaptchaSolver instance for handling CAPTCHA operations
    CaptchaSolver solveCaptcha;

    /**
     * Constructor to initialize GrievancePage with WebDriver
     * @param driver WebDriver instance for browser operations
     */
    public GrievancePage(WebDriver driver) {
        this.driver = driver;
        this.solveCaptcha = new CaptchaSolver(driver);
    }

    /**
     * Handles dialog box display verification
     * Checks if the grievance dialog box is visible on the page
     */
    public void D_box() {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dialogBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("radix-:rp:")));
            if (dialogBox.isDisplayed()) {
                System.out.println("Dialog box is displayed.");
            } else {
                System.out.println("Dialog box is not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Dialog box not found: " + e.getMessage());
        }
    }

    /**
     * Registers grievance without login
     * Handles the registration flow including ULB selection
     */
    public void D_regester_grivance_wl() {
        try {
            WebElement regN_btn = driver.findElement(By.xpath("//*[@id=\"radix-:rl:\"]/div[2]/div[1]/button"));
            regN_btn.click();

            WebElement regN_Conf_cBox = driver.findElement(By.xpath("//*[@id=\"terms\"]"));
            regN_Conf_cBox.click();

            WebElement regN_Cont_btn = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/div/div/div/button"));
            regN_Cont_btn.click();

            WebElement dropdown = driver.findElement(By.xpath("//select[@name='ulb']"));
            dropdown.click();
            Select ulbDropdown = new Select(dropdown);
            ulbDropdown.selectByValue("66f3fdb76609dc9d192199d1");

            WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[2]/button"));
            nextBtn.click();

            System.out.println("ULB selected and Next button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error in registering grievance: " + e.getMessage());
        }
    }

    /**
     * Enters grievance description and selects details checkbox
     * @param Description The grievance description text to be entered
     */
    public void Grievance_Description(String Description) {
        try {
            WebElement Grv_disc = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div/div[1]/textarea"));
            Grv_disc.sendKeys(Description);
            System.out.println("Grievance Description entered successfully.");

            WebElement dtl_cbox = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div/div[2]/div/input"));
            dtl_cbox.click();
            System.out.println("Details checkbox selected successfully.");
        } catch (Exception e) {
            System.out.println("Error entering grievance description: " + e.getMessage());
        }
    }

    /**
     * Enters location and selects random problem type
     * @param Location The location where the grievance occurred
     */
    public void Location(String Location) {
        try {
        	
            WebElement Loc_txt = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div/div[3]/div/input"));
            Loc_txt.sendKeys(Location);
            System.out.println("Location entered successfully.");

            WebElement dropdown = driver.findElement(By.xpath("//select[@name='problemTypeId']"));
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();
            Random random = new Random();
            int randomIndex = random.nextInt(options.size() - 1) + 1; // Ensure random selection within bounds

            select.selectByIndex(randomIndex);
            System.out.println("Random option selected: " + options.get(randomIndex).getText());
        } catch (Exception e) {
            System.out.println("Error selecting problem type: " + e.getMessage());
        }
    }

    /**
     * Uploads document and proceeds to next step
     * Uses hardcoded file path for document upload
     */
    public void Upload_doc() {
        try {
            WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
            fileInput.sendKeys("C:\\Users\\aadri\\Music\\images.jpg");
            System.out.println("Document uploaded successfully.");

            WebElement nxtBtn = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[2]/button"));
            nxtBtn.click();
            System.out.println("Next button clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error uploading document: " + e.getMessage());
        }
    }

    
    /**
     * Fills basic information form with user details
     * @param fill_name Full name of the complainant
     * @param movile_no Mobile number of the complainant
     * @param e_mail Email address of the complainant
     * @param Area Area/Hospital information
     * @param Holding Holding/Aadhar number
     * @param SAF SAF number
     * @param consumer Consumer number
     */
    public void Basic_Information(String fill_name, String movile_no, String e_mail, String Area, String Holding, String SAF, String consumer) {
        try {
        	Thread.sleep(10000);
            WebElement ful_name = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[1]/div/input"));
            ful_name.sendKeys(fill_name);
            System.out.println("Name entered successfully.");

            WebElement Mobile_No = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[2]/div/input"));
            Mobile_No.sendKeys(movile_no);
            System.out.println("Mobile entered successfully.");

            WebElement Chwbox = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[3]/div/input"));
            Chwbox.click();
            System.out.println("Checkbox selected successfully.");

            WebElement Email_id = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[4]/div/input"));
            Email_id.sendKeys(e_mail);
            System.out.println("Email entered successfully.");

            WebElement ward_noElement = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[5]/select"));
            Select dropdown = new Select(ward_noElement);
            List<WebElement> options = dropdown.getOptions();
            Random random = new Random();
            int randomIndex = random.nextInt(options.size() - 1) + 1;

            dropdown.selectByIndex(randomIndex);
            WebElement selectedOption = dropdown.getFirstSelectedOption();
            System.out.println("Selected ward number: " + selectedOption.getText());

            WebElement area_Location = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[6]/div/input"));
            area_Location.sendKeys(Area);
            System.out.println("Area entered successfully.");

            WebElement Holding_No = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[7]/div/input"));
            Holding_No.sendKeys(Holding);
            System.out.println("Holding number entered successfully.");

            WebElement Saf_no = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[8]/div/input"));
            Saf_no.sendKeys(SAF);
            System.out.println("SAF number entered successfully.");

            WebElement Consumer_No = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[1]/div[2]/div[9]/div/input"));
            Consumer_No.sendKeys(consumer);
            System.out.println("Consumer number entered successfully.");

            WebElement Next_BTNN = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form[1]/div/div[2]/button"));
            Next_BTNN.click();
            System.out.println("Basic details submitted successfully.");
        } catch (Exception e) {
            System.out.println("Error submitting basic details: " + e.getMessage());
        }
    }

    /**
     * Solves CAPTCHA and submits the grievance form
     * Also handles grievance number verification and displays details
     */
    public void solveCaptchaAndSubmit() {
        String solvedCaptcha = solveCaptcha.solveCaptcha();
       String grievanceNumber = null;
        try {
            WebElement captchaInputField = driver
                    .findElement(By.xpath("/html/body/div[5]/div/form/div[1]/div/div[3]/div/div/input"));
            captchaInputField.sendKeys(solvedCaptcha);
            Thread.sleep(4000);
           WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form/div[2]/button"));
           submitButton.click();
            System.out.println("CAPTCHA solved and submitted successfully."+ solvedCaptcha);
        } catch (Exception e) {
            System.out.println("Failed to solve CAPTCHA: " + e.getMessage());
        }
        
        
        try {
        	Thread.sleep(2000);
            WebElement wrongCaptcha = driver
                    .findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div/div[2]"));
             grievanceNumber = wrongCaptcha.getText();
            if (grievanceNumber != null && grievanceNumber.startsWith("GR-")) {
                System.out.println("Grievance No generated successfully: " + grievanceNumber);
            } else {
                System.out.println("Grievance No not generated or invalid format.");
            }
        } catch (Exception e) {
            System.out.println("Error verifying Grievance No: " + e.getMessage());
        }
       
        

        try {
        	Thread.sleep(2000);
            WebElement grievanceElement = driver
                    .findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form/div/div[2]/div/div"));
             grievanceNumber = grievanceElement.getText();
            if (grievanceNumber != null && grievanceNumber.startsWith("GR-")) {
                System.out.println("Grievance No generated successfully: " + grievanceNumber);
            } else {
                System.out.println("Grievance No not generated or invalid format.");
            }
        } catch (Exception e) {
            System.out.println("Error verifying Grievance No: " + e.getMessage());
        }
        
        
        try {
        	
            WebElement View_Grv_Details = driver
                    .findElement(By.xpath("//*[@id=\"radix-:rt:\"]/div/form/div/div[5]/a/button"));
             View_Grv_Details.click();
            System.out.println("Filled Up Grivance detail displayed");
        } catch (Exception e) {
            System.out.println("Grivance detail not displayed");
        }
        
        try {
        	Thread.sleep(2000);
        	            WebElement March_grvno = driver
                    .findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[3]/h1[2]/span[2]"));
            String grievanceNumberM = March_grvno.getText();
            if (grievanceNumber != null && grievanceNumberM.startsWith("GR-")) {
                System.out.println("Grievance No Fetched successfully: " + grievanceNumberM);
            } else {
                System.out.println("Grievance No not Fetched or Not Metched.");
            }
        } catch (Exception e) {
            System.out.println("Error verifying Grievance No: " + e.getMessage());
        }

        
    }
}
