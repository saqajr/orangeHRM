package com.orangehrm.tests;

import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Pages.LogoutPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;

public class LogoutTest {

    private final String LoginPageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    @Test
    public void createEmployeeTC(){

        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123")
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // createEmployee
        new LogoutPage(getDriver()).clickOnDashboardList()
                .clickOnLogoutButton().assertLogoutSuccessfully(LoginPageUrl);

    }

    @BeforeMethod
    public void setUp(){
        // setup driver
        setupDriver("chrome");
        //get drive
        new LoginPage(getDriver()).navigateToLoginPage(LoginPageUrl);
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }
}

