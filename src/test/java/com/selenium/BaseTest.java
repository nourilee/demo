package com.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {

        // Set up WebDriverManager to automatically handle the browser driver binaries
        WebDriverManager.chromedriver().clearDriverCache().setup();

        // Set Chrome options
        ChromeOptions chromeoption = new ChromeOptions();
        chromeoption.addArguments("--remote-allow-origins=*");

        // Initialize ChromeDriver
        driver = new ChromeDriver(chromeoption);

        // Set implicit wait time and maximize the browser window
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(60));
        driver.manage().window().maximize();

        // Open the test application
        driver.get("https://basic-crud-app-production.up.railway.app");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
