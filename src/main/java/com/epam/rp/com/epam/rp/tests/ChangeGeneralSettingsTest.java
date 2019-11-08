package com.epam.rp.com.epam.rp.tests;

import com.epam.rp.com.epam.rp.pages.BaseTestPage;
import com.epam.rp.com.epam.rp.pages.LoginPage;
import com.epam.rp.com.epam.rp.pages.SettingPage;
import com.epam.rp.com.epam.rp.pages.TestValues;
import org.testng.annotations.Test;

public class ChangeGeneralSettingsTest extends BaseTestPage {
    String lounchInactivityTimeout = TestValues.LAUNCH_INACTIVE_TIMEOUT;
    String keepLogTime = TestValues.KEEP_LOG_TIME;
    String keepAtachmentsTime=TestValues.KEEP_ATTACHMENTS_TIME;

    @Test
    public void changeGeneralSettingsTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        SettingPage settingPage= loginPage.clickSettings();
        settingPage.selectLounchInactivityTimeout(lounchInactivityTimeout);
        settingPage.selectKeepLogsTime(keepLogTime);
        settingPage.selectKeepAtachmentsTimeTime(keepAtachmentsTime);
        settingPage.verifyUserChangedGenneralSettings(lounchInactivityTimeout,keepLogTime,keepAtachmentsTime);
    }
}
