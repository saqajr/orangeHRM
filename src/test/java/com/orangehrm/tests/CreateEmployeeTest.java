package com.orangehrm.tests;

import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class CreateEmployeeTest {


    private final String CreateEmployeeUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    @Test
    public void testValidEmployeeCreation(){ // positive test case

        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123").clickLoginButton().assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl).enterFirstName("mahmoud3").enterMiddleName("mohamed3")
                .enterLastName("saqa3").saveEmployeeData().assertEmployeeCreationSuccessfully("viewPersonalDetails");

    }

    @Test
    public void createEmployeeWithMissingFirstName(){ // negative test case
        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123").clickLoginButton().assertSuccessfulLogin(DashboardUrl);


        // createEmployee ( negative )
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName("").enterMiddleName("mohamed3").enterLastName("saqa3")
                .saveEmployeeData().assertFirstNameRequired();

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
    }
}
