package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutPage {

    //scenario to logout user
    // .click on ul list that display li which logout one of them .
    // .then click on logout button

    private final WebDriver driver ;
    private final By DashboardList = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > header > div.oxd-topbar-header > div.oxd-topbar-header-userarea > ul");
    private final By LogoutButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > header > div.oxd-topbar-header > div.oxd-topbar-header-userarea > ul > li > ul > li:nth-child(4) > a");


    public LogoutPage(WebDriver driver ){
        this.driver = driver ;
    }

    public LogoutPage clickOnDashboardList(){
        Elements.clickButton(driver,DashboardList);
        return this ;
    }

    public LogoutPage clickOnLogoutButton(){
        Elements.clickButton(driver,LogoutButton);
        return this ;
    }

    public void assertLogoutSuccessfully(String ExpectedUrl) {
        Waits.waitForUrlToBe(driver,ExpectedUrl);
        Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);

    }
}
