package com.epam.rp.com.epam.rp.utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static ThreadLocal<String> appName = new ThreadLocal<>();

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static String getAppName() {
        return appName.get();
    }

    public static void setAppName(String value) {
        appName.set(value);
    }
}
