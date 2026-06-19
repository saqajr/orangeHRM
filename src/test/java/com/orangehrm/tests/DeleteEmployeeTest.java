package com.orangehrm.tests;

import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.DeleteEmployeePage;
import com.orangehrm.Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class DeleteEmployeeTest {

    private final String CreateEmployeeUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    private final String EmployeesUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
    private String firstName = "mahmoud3";
    private String MiddleName = "mohamed3";
    private String LastName = "saqa";
    private String InvalidEmployee = "invalid-user11";
    @Test
    public void testValidEmployeeDeletion(){ // positive test case

        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123").clickLoginButton().assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName(firstName).enterMiddleName(MiddleName)
                .enterLastName(LastName).saveEmployeeData().assertEmployeeCreationSuccessfully("viewPersonalDetails");

        // deleteEmployee
        new DeleteEmployeePage(getDriver())
                .goToEmployeesPage(EmployeesUrl)
                .enterFullEmployeeName(firstName + " " + MiddleName + " " + LastName)
                .searchEmployee()
                .clickOnDeleteIcon().clickOnDeleteButton().assertEmployeeDeletionSuccessfully();
    }

    @Test
    public void deleteEmployeeWithInvalidName(){ // negative test case
        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123").clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // deleteEmployee
        new DeleteEmployeePage(getDriver())
                .goToEmployeesPage(EmployeesUrl)
                .enterInvalidEmployee(InvalidEmployee).searchEmployee()
                .assertInvalidDeletion();

    }

    @BeforeMethod
    public void setUp(){
        // setup driver
        setupDriver("chrome");
        //get driver
        String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        new LoginPage(getDriver()).navigateToLoginPage(loginUrl);
    }

    @AfterMethod
    public void tearDown(){
        sleepForDemo();
        getDriver().quit();
    }}
