package com.epam.rp.com.epam.rp.utils;

import com.epam.rp.com.epam.rp.pages.BaseTestPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class TestListener extends TestListenerAdapter {
    protected static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("onTestStart method - " + iTestResult.getMethod().getConstructorOrMethod().getName() + " start");
        LOGGER.info("Instantiating WebDriver");
        try {
            WebDriver driver = createInstance(Config.getBrowser());
            initWebDriver(driver);
        } catch (Exception e) {
            LOGGER.error("Failed to instantiate WebDriver: " + e.getMessage(), e);
        }
    }

    private void initWebDriver(WebDriver driver) {
        DriverManager.setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        DriverManager.setAppName(Config.getAppName());
        driver.get(Config.getLoginPageURL());
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(Config.getChromeBetaPath());
        return options;
    }


    private WebDriver createInstance(String browserName) {
        RemoteWebDriver driver = null;
        if (browserName.toLowerCase().contains("chrome")) {
            String chromeDriver = "chromedriver-windows-32bit.exe";
            String fullPath = Paths.get(BaseTestPage.driversPath, chromeDriver).toAbsolutePath().toString();
            System.setProperty("webdriver.chrome.driver", fullPath);
            driver = new ChromeDriver(getChromeOptions());
            driver.manage().window().maximize();
            LOGGER.info("WebDriver instantiation successful");
            return driver;
        }
        return driver;
    }
}

