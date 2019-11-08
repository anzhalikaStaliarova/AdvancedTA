package com.epam.rp.com.epam.rp.pages;

import org.testng.Assert;

public class DashboardPage extends BaseTestPage {
    public static String newDashboardButton = "//*[@id='contentHeader']/div/button";
    public static String inputDashboardName = " //input[@class='rp-input']";
    public static String submitButton = "//button[@class='rp-btn rp-btn-submit']";
    public static String editButton = "//*[@class='icons-block']/i[1]";
    public static String deleteButton = "//*[@class='material-icons close-icon']";
    public static String deleteButtonSubmit = "//button[@class='rp-btn rp-btn-danger']";

    public DashboardPage addNewDashboard(String dashboardName) {
        click(getWaitSecsMaxTimeout(), newDashboardButton);
        setValue(dashboardName, inputDashboardName);
        click(getWaitSecsMaxTimeout(), submitButton);
        return this;
    }

    public DashboardPage verifyDashboardExists(String dashboardName, boolean isExists) {
        Assert.assertEquals(findElement(dashboardName).isDisplayed(), isExists, String.format(dashboardName + " dashboard does not present on the page", isExists ? " not" : ""));
        return this;
    }

    public DashboardPage deleteDashboard(String dashboardName) {
        click(getWaitSecsMaxTimeout(), deleteButton);
        click(getWaitSecsMaxTimeout(), deleteButtonSubmit);
        return this;
    }

    public DashboardPage editDashboard(String dashboardName, String newDashboardName) {
        click(getWaitSecsMaxTimeout(), editButton);
        setValue(newDashboardName, inputDashboardName);
        click(getWaitSecsMaxTimeout(), submitButton);
        return this;
    }

}
