package com.epam.rp.com.epam.rp.core.controls;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
public class WebEdit extends BaseWebElement {
    private WebElement wrappedElement;
    /**
     * Send specified keys to the element
     * @param keys text to type in
     */
    public void type(String keys) {
        this.clear(); // clear editbox before sending text
        while (!this.getTrimText().isEmpty()) {
            this.sendKeys(Keys.BACK_SPACE);
        }
        if (keys != null && !keys.isEmpty()) {
            this.sendKeys(keys);
        }
    }

    /**
     * Retrieves the text entered into this text input.
     * @return Trimmed text entered into the text input.
     */
    public String getTrimText() {
        return getText().trim();
    }
    /**
     * Retrieves the text entered into this text input.
     * @return Text entered into the text input.
     */
    public String getText() {
        String enteredText = this.getAttribute("value");
        if (enteredText == null) {
            return "";
        }
        return enteredText;
    }


}
