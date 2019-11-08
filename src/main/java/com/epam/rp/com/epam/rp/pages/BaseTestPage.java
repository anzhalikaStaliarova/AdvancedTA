package com.epam.rp.com.epam.rp.pages;

import com.epam.rp.com.epam.rp.utils.Config;
import com.epam.rp.com.epam.rp.utils.DriverManager;
import com.epam.rp.com.epam.rp.utils.Logger;
import com.epam.rp.com.epam.rp.utils.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.util.List;
import java.util.Objects;


@Listeners(TestListener.class)
public class BaseTestPage implements IHookable {
    public static UserDetails regularUser;
    public static UserDetails adminUser;
    protected static final Logger LOGGER = Logger.getInstance();
    public static String driversPath = Config.getDriversFolderPath();


    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        envLogging();
    }

    private void envLogging() {
        Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();
        LOGGER.info(String.format("WebDriver instantiation successful. ChromeDriver capabilities: %s, Browser name: %s, Browser version: %s",
                caps.getCapability("chrome"), caps.getBrowserName(), caps.getVersion()));
    }

    protected WebDriver getDriver() {
        return DriverManager.getWebDriver();
    }

    protected void click(long timeout, String... ids) {
        waitForPageLoaded(timeout);
        Integer[] attempt = {0};
        new WebDriverWait(getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            WebElement element = findElement(ids);
                            if (element.isEnabled()) {
                                String prev = getDriver().getPageSource();
                                findElement(ids).click();
                                attempt[0]++;
                                return (!prev.equals(getDriver().getPageSource()) || attempt[0] > 0);
                            }
                        } catch (NoSuchElementException e) {
                            if (attempt[0] > 0) return true;
                        }
                        return false;
                    }

                    @Override
                    public String toString() {
                        return String.format("element (%s) to be clicked", createXPath(ids));
                    }
                });
        LOGGER.info("Click on element - xpath: " + createXPath(ids));
    }

    protected WebElement findElement(String... ids) {
        return getDriver().findElement(By.xpath(createXPath(ids)));
    }

    protected String createXPath(String... ids) {
        String tag = "*";
        String xPath = "//" + tag + "[(";
        if (ids[0].contains("//")) {
            xPath = String.format("(%s)[not(ancestor::div[contains(@style,'display: none')])]", ids[0]);
            xPath = xPath.replaceAll("\\$(.*?)\\$", String.format("translate(%s, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')", "$1"));
            LOGGER.info("--- xpath: " + xPath);
            return xPath;
        }
        return xPath;
    }

    protected String getSelectedValueFromList(String... ids) {
        String xpath = createXPath(ids);
        Select droplist = new Select(getDriver().findElement(By.xpath(xpath)));
        WebElement option = droplist.getFirstSelectedOption();
        String valueSelected = option.getText();
        LOGGER.info("Get selected value from list xpath: " + xpath);
        LOGGER.info("Selected value is - " + valueSelected);
        return valueSelected;
    }

    protected void waitForPageLoaded(long timeout) {
        LOGGER.info("Wait " + timeout + " seconds for page loaded...");
        new WebDriverWait(getDriver(), timeout)
                .until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) Objects.requireNonNull(driver))
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }


    protected long getWaitSecsMaxTimeout() {
        return Config.getWaitSecsMaxTimeout();
    }

    /**
     * Selects the value from dropdown with waits the default timeout
     *
     * @param value - value to be selected (Exact Match)
     * @param ids   - composite id of element
     */
    protected void selectListBoxByValue(String value, String... ids) {
        selectListBoxByValue(getWaitSecsMaxTimeout(), value, ids);
    }

    protected void setValue(String value, WebElement element) {
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Sets the value to input field such as input, checkbox, radio buttons etc.
     *
     * @param value - value to set
     * @param ids   - composite id of element
     */
    protected void setValue(String value, String... ids) {
        setValue(Config.getWaitSecsMaxTimeout(), value, ids);
        LOGGER.info("Set value [ " + value + " ] in element - xpath: " + createXPath(ids));
    }

    /**
     * Sets the value to input field such as input, checkbox, radio buttons etc.
     *
     * @param timeout - maximum time to wait
     * @param value   - value to set
     * @param ids     - composite id of element
     */
    protected void setValue(long timeout, String value, String... ids) {
        if (null == value) return;

        new WebDriverWait(getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        WebElement element = findElement(ids);
                        element.sendKeys(value);
                        String actualValue = element.getAttribute("value");
                        return (startsWith(value, actualValue) ||
                                startsWith(value, element.getText()) ||
                                startsWith(value, element.getAttribute("title")));
                    }

                    @Override
                    public String toString() {
                        return String.format("text '%s' to be set in element (%s)", value, createXPath(ids));
                    }
                });
    }

    private boolean startsWith(String string, String substring) {
        if (null == substring) return false;
        if (string.isEmpty() && substring.isEmpty()) return true;
        if (!string.isEmpty() && substring.isEmpty()) return false;
        return string.startsWith(substring);
    }

    /**
     * Selects the value from dropdown and waits the given timeout
     *
     * @param timeout - maximum time to wait
     * @param value   - value to be selected (Exact Match)
     * @param ids     - composite id of element
     */
    protected void selectListBoxByValue(long timeout, String value, String... ids) {
        if (null == value) return;
        Integer[] attempt = {0};
        new WebDriverWait(getDriver(), timeout)
                .ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        try {
                            Select dropDown = new Select(findElement(ids));
                            String valueToSelect = getExactDropdownValue(dropDown.getOptions(), value);
                            if (valueToSelect == null) return false;
                            dropDown.selectByVisibleText(valueToSelect);
                            attempt[0]++;
                            String selectedValue = dropDown.getFirstSelectedOption().getText();
                            return valueToSelect.equals(selectedValue);

                        } catch (NoSuchElementException | StaleElementReferenceException e) {
                            if (attempt[0] > 0) return true;
                        }
                        return false;
                    }
                });
        LOGGER.info("Select list box by value [ " + value + " ] xpath: " + createXPath(ids));
    }

    protected String getExactDropdownValue(List<WebElement> options, String value) {
        try {
            return options.stream().filter(option -> value.equalsIgnoreCase(option.getText())).findFirst().get().getText();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }
}
