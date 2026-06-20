package com.orangehrm.tests;

import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Utils.DataUtils;
import com.orangehrm.Utils.ScreenShots;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class LoginTest {

    private final String LoginPageUrl;
    private final String DashboardUrl;
    private final String BROWSER;
    private final String USERNAME;
    private final String PASSWORD;
    private final String INVALID_USERNAME;
    private final String INVALID_PASSWORD;

    public LoginTest() throws IOException {
        LoginPageUrl = DataUtils.getPropertyValue("environment", "loginUrl");
        DashboardUrl = DataUtils.getPropertyValue("environment", "dashboardUrl");
        BROWSER = DataUtils.getPropertyValue("environment", "browser");
        USERNAME = DataUtils.getJsonData("validLogin", "username");
        PASSWORD = DataUtils.getJsonData("validLogin", "password");
        INVALID_USERNAME = DataUtils.getJsonData("invalidLogin", "invalidUsername");
        INVALID_PASSWORD = DataUtils.getJsonData("invalidLogin", "invalidPassword");
    }


    @Test
    public void testValidLogin() throws InterruptedException { //positive case
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD)
                .clickLoginButton()
                .assertSuccessfulLogin(DashboardUrl);

        Thread.sleep(5000);
        ScreenShots.takeScreenShot("successful-login");
    }

    @Test
    public void testInvalidUsername() {  // new negative case
        new LoginPage(getDriver()).enterUsername(INVALID_USERNAME).enterPassword(PASSWORD)
                .clickLoginButton()
                .assertInvalidCredentials();
    }

    @Test
    public void testInvalidPassword() { // new negative case
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(INVALID_PASSWORD)
                .clickLoginButton()
                .assertInvalidCredentials();
    }


    //configurations
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
