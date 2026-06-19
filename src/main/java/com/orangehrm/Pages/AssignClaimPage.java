package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssignClaimPage {

    private final WebDriver driver ;
    private final By EmployeeName = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(1) > div > div > div > div:nth-child(2) > div > div > input");

    private final By Event = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(2) > div > div:nth-child(1) > div > div:nth-child(2) > div > div");
    private final By Currency = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > div > div");
    private final By CreateAssignButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space");
    private final By SubmitButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div.orangehrm-action-button-container > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-sm-button");
    private final By assignClaimHeader = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div.orangehrm-card-container > h6");
    private final By ActualRequiredMessage = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(2) > div > div:nth-child(1) > div > span");

    public AssignClaimPage(WebDriver driver){
        this.driver = driver;
    }

    public AssignClaimPage enterFullEmployeeName(String EmployeeName){
        // send text to input field
        Elements.sendData(driver,this.EmployeeName,EmployeeName);
        // click on the first suggestion records
        Elements.clickButton(driver,By.xpath("//div[@role='listbox']//span[starts-with(text(),'"+EmployeeName+"')]"));

        return this ;
    }

    @Step("Select Event")
    public AssignClaimPage selectEvent(String EventName){
        Elements.clickButton(driver,this.Event);
        Elements.clickButton(driver,By.xpath("//div[@role='listbox']//span[text()='"+EventName+"']"));

        return this ;
    }

    @Step("Select Currency")
    public AssignClaimPage selectCurrency(String CurrencyName){
        Elements.clickButton(driver,this.Currency);
        Elements.clickButton(driver,By.xpath("//div[@role='listbox']//span[text()='"+CurrencyName+"']"));

        return this ;
    }

    @Step("Assert Assign Claimed success")
    public void assertAssignClaimSuccessfully(){
        // wait until element by visible

        String actual = Elements.getData(driver,this.assignClaimHeader);
        Assert.assertEquals(
                actual,
                "Assign Claim"
        );
    }

    @Step("Create Assign Claim")
    public AssignClaimPage createAssignClaim(){
        Elements.submitData(driver,this.CreateAssignButton);
        return this;
    }

    @Step("Submit Assign Claim")
    public AssignClaimPage submitAssignClaim(){
        Elements.clickButton(driver,this.SubmitButton);
        return this ;
    }

    @Step("Go To Assign Claim")
    public AssignClaimPage goToAssignClaimPage(String AssignClaimUrl){
        driver.get(AssignClaimUrl);
        return this ;
    }

    @Step("Assert Required Field")
    public void assertRequiredField(){
        Assert.assertEquals(Elements.getData(driver,ActualRequiredMessage), "Required");
    }




}
