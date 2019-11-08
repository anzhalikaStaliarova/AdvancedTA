package com.epam.rp.com.epam.rp.tests;

import com.epam.rp.com.epam.rp.pages.BaseTestPage;
import com.epam.rp.com.epam.rp.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.rp.com.epam.rp.utils.Config.getProperty;

public class LoginTest extends BaseTestPage {

    @Test(dataProvider = "getData")
    public void loginToReportPortal(String userName, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(userName,password);
        loginPage.validateLoginPageIsOpened();
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{{getProperty("REGULAR_USER_LOGIN"), getProperty("REGULAR_USER_PASSWORD")}, {getProperty("ADMIN_LOGIN"), getProperty("AdminPassword")}};
    }
}

