package com.epam.rp.com.epam.rp.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class WebDriverBrowser implements Browser {
    private WebDriver webDriver = null;

    private static WebDriverBrowser instance = null;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Switches Browser driver type depends on specified mode and returns browser instance.
     * @return WebDriverBrowser instance
     */
    public static WebDriverBrowser get() {
        if (instance == null) {
            instance = new WebDriverBrowser();
        }
        return instance;
    }
    /**
     * Executes Javascript with arguments
     * @param script  script to execute
     * @param objects arguments to call script with
     * @return result of execution (empty string if js completed with error)
     */
    @Override
    public String executeJS(String script, Object... objects) {
        try {
            Object o = ((JavascriptExecutor) webDriver).executeScript(script, objects);
            if(o != null){
                return String.valueOf(o);
            } else {
                return null;
            }
        } catch (WebDriverException  s) {
            return "";
        }
    }

    /**
     * Check if WebDriver is running.
     * @return true if WebDriver is running and false otherwise
     */
    public static boolean isRunning() {
        return instance != null && instance.getWebDriver() != null;
    }
    /**
     * Get a string representing the current URL that the browser is looking at.
     * @return - the absolute URL of the current page
     */
    @Override
    public String getCurrentUrl() {
        String currentUrl = "";
        if (isRunning()) {
            webDriver.switchTo().defaultContent();
            currentUrl = webDriver.getCurrentUrl();
        }
        return currentUrl;
    }
}
