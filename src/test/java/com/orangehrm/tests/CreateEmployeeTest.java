package com.orangehrm.tests;

import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Utils.DataUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class CreateEmployeeTest {


    private final String CreateEmployeeUrl;
    private final String DashboardUrl;
    private final String BROWSER;
    private final String LoginPageUrl;
    private final String USERNAME;
    private final String PASSWORD;
    private final String FIRST_NAME;
    private final String MIDDLE_NAME;
    private final String LAST_NAME;

    public CreateEmployeeTest() throws IOException {
        CreateEmployeeUrl = DataUtils.getPropertyValue("environment", "createEmployeeUrl");
        DashboardUrl = DataUtils.getPropertyValue("environment", "dashboardUrl");
        BROWSER = DataUtils.getPropertyValue("environment", "browser");
        LoginPageUrl = DataUtils.getPropertyValue("environment", "loginUrl");
        USERNAME = DataUtils.getJsonData("validLogin", "username");
        PASSWORD = DataUtils.getJsonData("validLogin", "password");
        FIRST_NAME = DataUtils.getJsonData("employeeData", "firstName");
        MIDDLE_NAME = DataUtils.getJsonData("employeeData", "middleName");
        LAST_NAME = DataUtils.getJsonData("employeeData", "lastName");
    }

    @Test
    public void testValidEmployeeCreation(){ // positive test case

        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD).clickLoginButton().assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl).enterFirstName(FIRST_NAME).enterMiddleName(MIDDLE_NAME)
                .enterLastName(LAST_NAME).saveEmployeeData().assertEmployeeCreationSuccessfully("viewPersonalDetails");

    }

    @Test
    public void createEmployeeWithMissingFirstName(){ // negative test case
        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD).clickLoginButton().assertSuccessfulLogin(DashboardUrl);


        // createEmployee ( negative )
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName("").enterMiddleName(MIDDLE_NAME).enterLastName(LAST_NAME)
                .saveEmployeeData().assertFirstNameRequired();

    }

    @BeforeMethod
    public void setUp() throws IOException {
        // setup driver
        setupDriver(BROWSER);
        //get driver
        new LoginPage(getDriver()).navigateToLoginPage(LoginPageUrl);
    }

    @AfterMethod
    public void tearDown(){
        sleepForDemo();
        getDriver().quit();
    }
}
