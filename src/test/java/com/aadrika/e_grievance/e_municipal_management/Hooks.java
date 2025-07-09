package com.aadrika.e_grievance.e_municipal_management;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        driver.get().manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
