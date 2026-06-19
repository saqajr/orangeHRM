    package com.orangehrm.tests;

    import com.orangehrm.Pages.DashboardPage;
    import com.orangehrm.Pages.LoginPage;
    import org.openqa.selenium.PageLoadStrategy;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.openqa.selenium.safari.SafariDriver;
    import org.openqa.selenium.safari.SafariOptions;
    import org.testng.annotations.AfterMethod;
    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.Test;
    import static com.orangehrm.Drivers.WebDriver.getDriver;
    import static com.orangehrm.Drivers.WebDriver.setupDriver;
    import static com.orangehrm.Utils.Waits.sleepForDemo;

    public class DashboardTest {
        private final String LoginPageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        //tests
        @Test
        public void dashboardTitleDisplayed() {

            // login steps
            new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123").clickLoginButton().assertSuccessfulLogin(DashboardUrl);

            // is dashboard title displayed
            new DashboardPage(getDriver()).isDashboardDisplayed();
        }

        //configurations
        @BeforeMethod
        public void setUp(){

            //setup driver
            setupDriver("chrome");
            //get driver
            new LoginPage(getDriver()).navigateToLoginPage(LoginPageUrl);
        }

        @AfterMethod
        public void tearDown(){
            sleepForDemo();
            getDriver().quit();
        }    }
