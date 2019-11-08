package com.epam.rp.com.epam.rp.core.controls;

import com.epam.rp.com.epam.rp.utils.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseWebElement {
    private WebElement wrappedElement;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public void click() {

        this.clickJS();
    }

    public void clear() {
        this.wrappedElement.clear();
    }
    /**
     * Click on element using javascript
     */
    public void clickJS() {
        String code = "var element = arguments[0];" + "clickEvent = document.createEvent(\"MouseEvents\");"
                + "clickEvent.initEvent(\"mousedown\", true, true);" + "element.dispatchEvent(clickEvent);"
                + "clickEvent = document.createEvent(\"MouseEvents\");" + "clickEvent.initEvent(\"click\", true, true);"
                + "element.dispatchEvent(clickEvent);" + "clickEvent = document.createEvent(\"MouseEvents\");"
                + "clickEvent.initEvent(\"mouseup\", true, true);" + "element.dispatchEvent(clickEvent);";
        executeJS(code);
    }

    /**
     * Execute Javascript
     *
     * @param code script to execute
     * @return result of execution
     */
    public String executeJS(String code) {
        return Browser.get().executeJS(code, this.getWrappedElement());
    }

    public WebElement getWrappedElement() {
        return this.wrappedElement;
    }
    public String getAttribute(String name) {
        return this.wrappedElement.getAttribute(name);
    }

    public void sendKeys(CharSequence... charSequences) {
        this.wrappedElement.sendKeys(charSequences);
    }
}
