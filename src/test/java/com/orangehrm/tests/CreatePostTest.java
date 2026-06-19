package com.orangehrm.tests;

import com.orangehrm.Pages.CreatePostPage;
import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Pages.LogoutPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;

public class CreatePostTest {
    private final String LoginPageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    private final String CreatePostUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";


    @Test
    public void createPostTc(){ // positive test case

        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123")
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // Create post
        new CreatePostPage(getDriver()).navigateToCreatePost(CreatePostUrl)
                .enterPostData("alselam alekoum 1")
                .submitPost().assertPostCreationSuccessfully();

    }

    @Test
    public void createPostWithEmptyContent(){ // negative test case
        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123")
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // Create post
        new CreatePostPage(getDriver()).navigateToCreatePost(CreatePostUrl)
                .enterPostData("")
                .submitPost().assertCreatePostWithEmptyContent();
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
