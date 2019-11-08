package com.epam.rp.com.epam.rp.pages;

import org.testng.asserts.SoftAssert;

public class SettingPage extends BaseTestPage {
    public static String keepLogs = "//div[@data-js-selector='keepLogs']";
    public static String interruptedJob = "//div[@data-js-selector='interruptedJob']";
    public static String keepScreenshots = "//div[@data-js-selector='keepScreenshots']";

    public SettingPage verifyUserChangedGenneralSettings(String lounchInactivityTimeout, String keepLogsTime, String keepAtachmentsTime) {
        SoftAssert softAssert = new SoftAssert();
        String selectedLounchInactivityTimeout = getSelectedValueFromList(interruptedJob);
        String selectedkeepLogsTime = getSelectedValueFromList(keepLogs);
        String selectedkeepAtachmentsTime = getSelectedValueFromList(keepScreenshots);
        softAssert.assertEquals(selectedLounchInactivityTimeout, lounchInactivityTimeout, lounchInactivityTimeout + "is selected in the drop down list");
        softAssert.assertEquals(selectedkeepLogsTime, keepLogsTime, keepLogsTime + "is selected in the drop down list");
        softAssert.assertEquals(selectedkeepAtachmentsTime, keepAtachmentsTime, keepAtachmentsTime + "is selected in the drop down list");
        softAssert.assertAll();
        return this;
    }

    public SettingPage selectLounchInactivityTimeout(String value) {
        selectListBoxByValue(getWaitSecsMaxTimeout(),value,interruptedJob);
        return this;
    }

    public SettingPage selectKeepLogsTime(String value) {
        selectListBoxByValue(getWaitSecsMaxTimeout(),value,keepLogs);
        return this;
    }

    public SettingPage selectKeepAtachmentsTimeTime(String value) {
        selectListBoxByValue(getWaitSecsMaxTimeout(),value,keepScreenshots);
        return this;
    }
}
