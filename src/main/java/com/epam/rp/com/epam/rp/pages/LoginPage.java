package com.epam.rp.com.epam.rp.pages;

import com.epam.rp.com.epam.rp.core.controls.WebButton;
import com.epam.rp.com.epam.rp.core.controls.WebEdit;
import com.epam.rp.com.epam.rp.utils.Browser;
import com.epam.rp.com.epam.rp.utils.Config;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.epam.rp.com.epam.rp.utils.Config.getProperty;

public class LoginPage extends BaseTestPage {

    public static String settingsButon = "//*[@id='settings']";
    public static String dashboardTab = "//*[@id='dashboard']";

    @FindBy(id = "username")
    WebEdit usernameEdit;

    @FindBy(id = "password")
    WebEdit passwordEdit;

    @FindBy(xpath = "//input[@value='Log In']")
    WebButton logInButton;

    public LoginPage login() {
        login(getProperty("REGULAR_USER_LOGIN"), getProperty("REGULAR_USER_PASSWORD"));
        return this;
    }

    public LoginPage login(String username, String password) {
        usernameEdit.type(username);
        passwordEdit.type(password);
        logInButton.clickAndWait();
        return this;
    }

    public LoginPage validateLoginPageIsOpened() {
        Assert.assertEquals(Browser.get().getCurrentUrl(), Config.getLoginPageURL(), "New page is not opened");
        return this;
    }

    public SettingPage clickSettings() {
        click(getWaitSecsMaxTimeout(), settingsButon);
        return new SettingPage();
    }

    public DashboardPage clickDashboardTab() {
        click(getWaitSecsMaxTimeout(), dashboardTab);
        return new DashboardPage();
    }

}
