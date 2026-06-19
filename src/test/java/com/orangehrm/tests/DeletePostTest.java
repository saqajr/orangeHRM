package com.orangehrm.tests;

import com.orangehrm.Pages.CreatePostPage;
import com.orangehrm.Pages.DeletePostPage;
import com.orangehrm.Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;

public class DeletePostTest {
    private final String LoginPageUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String DashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    private final String CreatePostUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
    private String PostTitle = "alselam alekoum 101";

    @Test
    public void deletePostTc() throws InterruptedException {

        // login steps
        new LoginPage(getDriver()).enterUsername("Admin").enterPassword("admin123")
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // Create post
        new CreatePostPage(getDriver()).navigateToCreatePost(CreatePostUrl)
                .enterPostData(PostTitle)
                .submitPost().assertPostCreationSuccessfully();

        Thread.sleep(10000);

        new CreatePostPage(getDriver()).navigateToCreatePost(CreatePostUrl);

        //Delete Post
        new DeletePostPage(getDriver(),PostTitle)
                .clickOnThreeDotsButton().clickOnDeleteIcon().clickOnDeleteButton();
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
