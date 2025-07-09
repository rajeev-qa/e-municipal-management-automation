package com.aadrika.egovernance.utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aadrika.egovernance.enums.BrowserType;
import com.aadrika.egovernance.utils.constants.FrameworkConstants;
import java.time.Duration;

/**
 * Factory class for creating WebDriver instances
 */
public final class DriverFactory {
    
    private DriverFactory() {}
    
    /**
     * Creates WebDriver instance based on browser type
     * @param browserType Type of browser to create
     * @return WebDriver instance
     */
    public static WebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        
        switch (browserType) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            case EDGE:
                driver = createEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }
        
        configureDriver(driver);
        return driver;
    }
    
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        return new ChromeDriver(options);
    }
    
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
    
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    
    private static void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FrameworkConstants.IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_TIMEOUT));
    }
}