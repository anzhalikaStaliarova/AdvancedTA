package com.epam.rp.com.epam.rp.core.controls;

public class WebButton extends BaseWebElement {

    /**
     * Click this element and wait for browser to become ready
     */
    public void clickAndWait() {
        clickAndWait(false);
    }
    /**
     * Click this element, reset current frame if necessary, and wait for browser to become ready
     * @param resetFrame - if true, frame is reset to top window prior to waiting for page reload
     */
    public void clickAndWait(boolean resetFrame) {
        this.click();
        if (resetFrame) {
            try {
                Thread.sleep(1000); // to avoid "permission denied" errors on not fully loaded frame/window
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
