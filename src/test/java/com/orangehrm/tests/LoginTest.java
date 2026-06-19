package com.orangehrm.tests;

import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Utils.ScreenShots;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class LoginTest {

    private final String LoginPageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";


    @Test
    public void testValidLogin() throws InterruptedException { //positive case
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123")
                .clickLoginButton()
                .assertSuccessfulLogin(DashboardUrl);

        Thread.sleep(5000);
        ScreenShots.takeScreenShot("successful-login");
    }

    @Test
    public void testInvalidUsername() {  // new negative case
        new LoginPage(getDriver()).enterUsername("Admin1").enterPassword("admin123")
                .clickLoginButton()
                .assertInvalidCredentials();
    }

    @Test
    public void testInvalidPassword() { // new negative case
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin1234")
                .clickLoginButton()
                .assertInvalidCredentials();
    }


    //configurations
    @BeforeMethod
    public void setUp(){
        // setup driver
        setupDriver("chrome");
        //get drive
        new LoginPage(getDriver()).navigateToLoginPage(LoginPageUrl);
    }

    @AfterMethod
    public void tearDown(){
        sleepForDemo();
        getDriver().quit();
    }
}
