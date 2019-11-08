package com.epam.rp.com.epam.rp.tests;

import com.epam.rp.com.epam.rp.pages.BaseTestPage;
import com.epam.rp.com.epam.rp.pages.DashboardPage;
import com.epam.rp.com.epam.rp.pages.LoginPage;
import com.epam.rp.com.epam.rp.pages.TestValues;
import org.testng.annotations.Test;

public class ModifyDashboardTest extends BaseTestPage {
    String dashboardName = TestValues.DASHBOARD_NAME;
    String newDashboardName= TestValues.NEW_DASHBOARD_NAME;

    @Test
    public void userAbleAddNewDashboard() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        DashboardPage dashboardPage = loginPage.clickDashboardTab();
        dashboardPage.addNewDashboard(dashboardName);
        dashboardPage.verifyDashboardExists(dashboardName, true);
    }

    @Test
    public void userAbleEditDashboard() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        DashboardPage dashboardPage = loginPage.clickDashboardTab();
        dashboardPage.editDashboard(dashboardName, newDashboardName);
        dashboardPage.verifyDashboardExists(newDashboardName, false);
    }

    @Test
    public void userAbleDeleteDashboard() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        DashboardPage dashboardPage = loginPage.clickDashboardTab();
        dashboardPage.deleteDashboard(dashboardName);
        dashboardPage.verifyDashboardExists(dashboardName, false);
    }
}

