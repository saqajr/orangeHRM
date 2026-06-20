package com.orangehrm.tests;

import com.orangehrm.Pages.AssignClaimPage;
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

public class AssignClaimTest {

    private final String CreateEmployeeUrl;
    private final String DashboardUrl;
    private final String AssignClaimUrl;
    private final String loginUrl;
    private final String BROWSER;
    private final String USERNAME;
    private final String PASSWORD;
    private final String CLAIM_EMPLOYEE_FIRST_NAME;
    private final String CLAIM_EMPLOYEE_MIDDLE_NAME;
    private final String CLAIM_EMPLOYEE_LAST_NAME;
    private final String CLAIM_EVENT;
    private final String CLAIM_CURRENCY;

    public AssignClaimTest() throws IOException {
        CreateEmployeeUrl = DataUtils.getPropertyValue("environment", "createEmployeeUrl");
        DashboardUrl = DataUtils.getPropertyValue("environment", "dashboardUrl");
        AssignClaimUrl = DataUtils.getPropertyValue("environment", "assignClaimUrl");
        loginUrl = DataUtils.getPropertyValue("environment", "loginUrl");
        BROWSER = DataUtils.getPropertyValue("environment", "browser");
        USERNAME = DataUtils.getJsonData("validLogin", "username");
        PASSWORD = DataUtils.getJsonData("validLogin", "password");
        CLAIM_EMPLOYEE_FIRST_NAME = DataUtils.getJsonData("assignClaimData", "claimEmployeeFirstName");
        CLAIM_EMPLOYEE_MIDDLE_NAME = DataUtils.getJsonData("assignClaimData", "claimEmployeeMiddleName");
        CLAIM_EMPLOYEE_LAST_NAME = DataUtils.getJsonData("assignClaimData", "claimEmployeeLastName");
        CLAIM_EVENT = DataUtils.getJsonData("assignClaimData", "claimEvent");
        CLAIM_CURRENCY = DataUtils.getJsonData("assignClaimData", "claimCurrency");
    }

    @Test
    public void assignClaimTC(){ // positive

        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME)
                .enterPassword(PASSWORD).clickLoginButton()
                .assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName(CLAIM_EMPLOYEE_FIRST_NAME).enterMiddleName(CLAIM_EMPLOYEE_MIDDLE_NAME)
                .enterLastName(CLAIM_EMPLOYEE_LAST_NAME).saveEmployeeData()
                .assertEmployeeCreationSuccessfully("/pim/viewPersonalDetails/empNumber/");

        // assign Claim for this emp
        new AssignClaimPage(getDriver()).goToAssignClaimPage(AssignClaimUrl)
                .enterFullEmployeeName(CLAIM_EMPLOYEE_FIRST_NAME + " " + CLAIM_EMPLOYEE_MIDDLE_NAME + " " + CLAIM_EMPLOYEE_LAST_NAME)
                .selectEvent(CLAIM_EVENT)
                .selectCurrency(CLAIM_CURRENCY)
                .createAssignClaim().submitAssignClaim().assertAssignClaimSuccessfully();
    }

    @Test
    public void assignClaimWithMissingRequiredField(){ // negative
        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME)
                .enterPassword(PASSWORD).clickLoginButton()
                .assertSuccessfulLogin(DashboardUrl);


        // createEmployee
        new CreateEmployeePage(getDriver()).goToAddEmployeePage(CreateEmployeeUrl)
                .enterFirstName(CLAIM_EMPLOYEE_FIRST_NAME).enterMiddleName(CLAIM_EMPLOYEE_MIDDLE_NAME)
                .enterLastName(CLAIM_EMPLOYEE_LAST_NAME).saveEmployeeData()
                .assertEmployeeCreationSuccessfully("/pim/viewPersonalDetails/empNumber/");

        // assign Claim for this emp
        new AssignClaimPage(getDriver()).goToAssignClaimPage(AssignClaimUrl)
                .enterFullEmployeeName(CLAIM_EMPLOYEE_FIRST_NAME + " " + CLAIM_EMPLOYEE_MIDDLE_NAME + " " + CLAIM_EMPLOYEE_LAST_NAME)
                .selectCurrency(CLAIM_CURRENCY)
                .createAssignClaim().assertRequiredField();
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
    }
}
