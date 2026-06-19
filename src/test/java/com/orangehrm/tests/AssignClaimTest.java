package com.orangehrm.tests;

import com.orangehrm.Pages.AssignClaimPage;
import com.orangehrm.Pages.CreateEmployeePage;
import com.orangehrm.Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;

public class AssignClaimTest {

    private final String CreateEmployeeUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    private final String AssignClaimUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/claim/assignClaim";
    private final String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @Test
    public void assignClaimTC(){ // positive

        // login steps
        new LoginPage(getDriver()).enterUsername("Admin")
                .enterPassword("admin123").clickLoginButton()
                .assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName("mahmoud5").enterMiddleName("mohamed5")
                .enterLastName("saqa5").saveEmployeeData()
                .assertEmployeeCreationSuccessfully("/pim/viewPersonalDetails/empNumber/");

        // assign Claim for this emp
        new AssignClaimPage(getDriver()).goToAssignClaimPage(AssignClaimUrl)
                .enterFullEmployeeName("mahmoud5 mohamed5 saqa5")
                .selectEvent("Accommodation")
                .selectCurrency("Afghanistan Afghani")
                .createAssignClaim().submitAssignClaim().assertAssignClaimSuccessfully();
    }

    @Test
    public void assignClaimWithMissingRequiredField(){ // negative
        // login steps
        new LoginPage(getDriver()).enterUsername("Admin")
                .enterPassword("admin123").clickLoginButton()
                .assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName("mahmoud5").enterMiddleName("mohamed5")
                .enterLastName("saqa5").saveEmployeeData()
                .assertEmployeeCreationSuccessfully("/pim/viewPersonalDetails/empNumber/");

        // assign Claim for this emp
        new AssignClaimPage(getDriver()).goToAssignClaimPage(AssignClaimUrl)
                .enterFullEmployeeName("mahmoud5 mohamed5 saqa5")
                .selectCurrency("Afghanistan Afghani")
                .createAssignClaim().assertRequiredField();
    }

    @BeforeMethod
    public void setUp(){
        // setup driver
        setupDriver("chrome");
        //get driver
        new LoginPage(getDriver()).navigateToLoginPage(loginUrl);
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }
}
