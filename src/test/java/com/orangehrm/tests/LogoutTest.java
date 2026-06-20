package com.orangehrm.tests;

import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Pages.LogoutPage;
import com.orangehrm.Utils.DataUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class LogoutTest {

    private final String LoginPageUrl;
    private final String DashboardUrl;
    private final String BROWSER;
    private final String USERNAME;
    private final String PASSWORD;

    public LogoutTest() throws IOException {
        LoginPageUrl = DataUtils.getPropertyValue("environment", "loginUrl");
        DashboardUrl = DataUtils.getPropertyValue("environment", "dashboardUrl");
        BROWSER = DataUtils.getPropertyValue("environment", "browser");
        USERNAME = DataUtils.getJsonData("validLogin", "username");
        PASSWORD = DataUtils.getJsonData("validLogin", "password");
    }

    @Test
    public void createEmployeeTC(){

        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD)
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // createEmployee
        new LogoutPage(getDriver()).clickOnDashboardList()
                .clickOnLogoutButton().assertLogoutSuccessfully(LoginPageUrl);

    }

    @BeforeMethod
    public void setUp() throws IOException {
        // setup driver
        setupDriver(BROWSER);
        //get drive
        new LoginPage(getDriver()).navigateToLoginPage(LoginPageUrl);
    }

    @AfterMethod
    public void tearDown(){
        sleepForDemo();
        getDriver().quit();
    }
}

