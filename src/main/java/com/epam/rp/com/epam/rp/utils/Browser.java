package com.epam.rp.com.epam.rp.utils;

public interface Browser {
    static Browser get() {
        return WebDriverBrowser.get();
    }

    /**
     * Executes Javascript with arguments
     * @param script script to execute
     * @param objects arguments to call script with
     * @return result of execution (empty string if js completed with error)
     */
    String executeJS(String script, Object... objects);

    /**
     * Get a string representing the current URL that the browser is looking at.
     * @return - the absolute URL of the current page
     */
    String getCurrentUrl();

}
