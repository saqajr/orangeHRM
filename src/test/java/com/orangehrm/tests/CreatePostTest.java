package com.orangehrm.tests;

import com.orangehrm.Pages.CreatePostPage;
import com.orangehrm.Pages.LoginPage;
import com.orangehrm.Pages.LogoutPage;
import com.orangehrm.Utils.DataUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.orangehrm.Drivers.WebDriver.getDriver;
import static com.orangehrm.Drivers.WebDriver.setupDriver;
import static com.orangehrm.Utils.Waits.sleepForDemo;

public class CreatePostTest {
    private final String LoginPageUrl;
    private final String DashboardUrl;
    private final String CreatePostUrl;
    private final String BROWSER;
    private final String USERNAME;
    private final String PASSWORD;
    private final String VALID_POST_CONTENT;
    private final String EMPTY_POST_CONTENT;

    public CreatePostTest() throws IOException {
        LoginPageUrl = DataUtils.getPropertyValue("environment", "loginUrl");
        DashboardUrl = DataUtils.getPropertyValue("environment", "dashboardUrl");
        CreatePostUrl = DataUtils.getPropertyValue("environment", "createPostUrl");
        BROWSER = DataUtils.getPropertyValue("environment", "browser");
        USERNAME = DataUtils.getJsonData("validLogin", "username");
        PASSWORD = DataUtils.getJsonData("validLogin", "password");
        VALID_POST_CONTENT = DataUtils.getJsonData("postData", "validPostContent");
        EMPTY_POST_CONTENT = DataUtils.getJsonData("postData", "emptyPostContent");
    }


    @Test
    public void createPostTc(){ // positive test case

        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD)
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // Create post
        new CreatePostPage(getDriver()).navigateToCreatePost(CreatePostUrl)
                .enterPostData(VALID_POST_CONTENT)
                .submitPost().assertPostCreationSuccessfully();

    }

    @Test
    public void createPostWithEmptyContent(){ // negative test case
        // login steps
        new LoginPage(getDriver()).enterUsername(USERNAME).enterPassword(PASSWORD)
                .clickLoginButton().assertSuccessfulLogin(DashboardUrl);

        // Create post
        new CreatePostPage(getDriver()).navigateToCreatePost(CreatePostUrl)
                .enterPostData(EMPTY_POST_CONTENT)
                .submitPost().assertCreatePostWithEmptyContent();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        // setup driver
        setupDriver(BROWSER);
        //get drive
        new LoginPage(getDriver()).navigateToLoginPage(LoginPageUrl);
    }

    @AfterMethod
    public void tearDown(){
        sleepForDemo();
        getDriver().quit();
    }}
