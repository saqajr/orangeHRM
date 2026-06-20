package com.orangehrm.tests;

import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.DeleteEmployeePage;
import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Utils.DataUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class DeleteEmployeeTest {

    private final String CreateEmployeeUrl;
    private final String DashboardUrl;
    private final String EmployeesUrl;
    private final String BROWSER;
    private final String loginUrl;
    private final String USERNAME;
    private final String PASSWORD;
    private final String DELETE_EMPLOYEE_FIRST_NAME;
    private final String DELETE_EMPLOYEE_MIDDLE_NAME;
    private final String DELETE_EMPLOYEE_LAST_NAME;
    private final String INVALID_EMPLOYEE_NAME;

    public DeleteEmployeeTest() throws IOException {
        CreateEmployeeUrl = DataUtils.getPropertyValue("environment", "createEmployeeUrl");
        DashboardUrl = DataUtils.getPropertyValue("environment", "dashboardUrl");
        EmployeesUrl = DataUtils.getPropertyValue("environment", "employeesUrl");
        BROWSER = DataUtils.getPropertyValue("environment", "browser");
        loginUrl = DataUtils.getPropertyValue("environment", "loginUrl");
        USERNAME = DataUtils.getJsonData("validLogin", "username");
        PASSWORD = DataUtils.getJsonData("validLogin", "password");
        DELETE_EMPLOYEE_FIRST_NAME = DataUtils.getJsonData("deleteEmployeeData", "deleteEmployeeFirstName");
        DELETE_EMPLOYEE_MIDDLE_NAME = DataUtils.getJsonData("deleteEmployeeData", "deleteEmployeeMiddleName");
        DELETE_EMPLOYEE_LAST_NAME = DataUtils.getJsonData("deleteEmployeeData", "deleteEmployeeLastName");
        INVALID_EMPLOYEE_NAME = DataUtils.getJsonData("deleteEmployeeData", "invalidEmployeeName");
    }

    @Test
    public void testValidEmployeeDeletion(){ // positive test case

        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD).clickLoginButton().assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName(DELETE_EMPLOYEE_FIRST_NAME).enterMiddleName(DELETE_EMPLOYEE_MIDDLE_NAME)
                .enterLastName(DELETE_EMPLOYEE_LAST_NAME).saveEmployeeData().assertEmployeeCreationSuccessfully("viewPersonalDetails");

        // deleteEmployee
        new DeleteEmployeePage(getDriver())
                .goToEmployeesPage(EmployeesUrl)
                .enterFullEmployeeName(DELETE_EMPLOYEE_FIRST_NAME + " " + DELETE_EMPLOYEE_MIDDLE_NAME + " " + DELETE_EMPLOYEE_LAST_NAME)
                .searchEmployee()
                .clickOnDeleteIcon().clickOnDeleteButton().assertEmployeeDeletionSuccessfully();
    }

    @Test
    public void deleteEmployeeWithInvalidName(){ // negative test case
        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD).clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // deleteEmployee
        new DeleteEmployeePage(getDriver())
                .goToEmployeesPage(EmployeesUrl)
                .enterInvalidEmployee(INVALID_EMPLOYEE_NAME).searchEmployee()
                .assertInvalidDeletion();

    }

    @BeforeMethod
    public void setUp() throws IOException {
        // setup driver
        setupDriver(BROWSER);
        //get driver
        new LoginPage(getDriver()).navigateToLoginPage(loginUrl);
    }

    @AfterMethod
    public void tearDown(){
        sleepForDemo();
        getDriver().quit();
    }}
