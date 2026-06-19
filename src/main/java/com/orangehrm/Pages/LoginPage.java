package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Logs;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {


    private final WebDriver driver;

    // locators
    private final By username = By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div:nth-child(2) > div > div:nth-child(2) > input");
    private final By password = By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div:nth-child(3) > div > div:nth-child(2) > input");
    private final By submitButton = By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div.oxd-form-actions.orangehrm-login-action > button");
    private final By actualErrorMessage = By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > div > div.oxd-alert.oxd-alert--error");
    public LoginPage(WebDriver driver){
        this.driver = driver ;
    }

    // Actions
    public LoginPage enterUsername(String username){
        Elements.sendData(driver,this.username,username);
        return this;
    }

    public LoginPage enterPassword(String password){
        Elements.sendData(driver,this.password,password);
        return this ;
    }

    public LoginPage clickLoginButton(){
        Logs.info("Click on Login Button");
        Elements.submitData(driver,this.submitButton);
        return this ;
    }


    public void navigateToLoginPage(String url){
        driver.get(url);
    }

    public void assertSuccessfulLogin(String ExpectedUrl){

        Waits.waitForUrlToBe(driver,ExpectedUrl);
        Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);
    }

    public void assertInvalidCredentials(){
        Assert.assertEquals(Elements.getData(driver,actualErrorMessage), "Invalid credentials");
    }
}
