package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {

    private final By dashboardTitle =By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > header > div.oxd-topbar-header > div.oxd-topbar-header-title > span > h6");
    private final WebDriver driver ;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToDashboard(String url){
        driver.get(url);
    }

    public DashboardPage isDashboardDisplayed(){
        // wait until element by visible
        Waits.waitUntilVisible(driver,this.dashboardTitle);
        String actual = Elements.getData(driver,this.dashboardTitle);
        Assert.assertEquals(
                actual,
                "Dashboard"
        );
        return this;
    }

}
