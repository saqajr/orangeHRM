package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CreateEmployeePage {

    private final WebDriver driver ;
    private final By FirstName = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.orangehrm-employee-container > div.orangehrm-employee-form > div:nth-child(1) > div.oxd-grid-1.orangehrm-full-width-grid > div > div > div.--name-grouped-field > div:nth-child(1) > div:nth-child(2) > input");
    private final By MiddleName = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.orangehrm-employee-container > div.orangehrm-employee-form > div:nth-child(1) > div.oxd-grid-1.orangehrm-full-width-grid > div > div > div.--name-grouped-field > div:nth-child(2) > div:nth-child(2) > input");
    private final By LastName = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.orangehrm-employee-container > div.orangehrm-employee-form > div:nth-child(1) > div.oxd-grid-1.orangehrm-full-width-grid > div > div > div.--name-grouped-field > div:nth-child(3) > div:nth-child(2) > input");

    // note empid must be greater than 1000
    private final By EmployeeId = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.orangehrm-employee-container > div.orangehrm-employee-form > div:nth-child(1) > div.oxd-grid-2.orangehrm-full-width-grid > div > div > div:nth-child(2) > input");
    private final By SaveEmployeeData = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space");
    private final By ActualRequiredMessage = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div.orangehrm-employee-container > div.orangehrm-employee-form > div:nth-child(1) > div.oxd-grid-1.orangehrm-full-width-grid > div > div > div.--name-grouped-field > div:nth-child(1) > span");

    public CreateEmployeePage(WebDriver driver ){
        this.driver = driver ;

    }

    public CreateEmployeePage goToAddEmployeePage(String Url){
        driver.get(Url);
        return this ;
    }
    public CreateEmployeePage enterFirstName(String FirstName){
        System.out.println("sending First name");
        Elements.sendData(driver,this.FirstName,FirstName);
        return this;

    }
    public CreateEmployeePage enterMiddleName(String MiddleName){
        System.out.println("sending Middle name");
        Elements.sendData(driver,this.MiddleName,MiddleName);
        return this;

    }
    public CreateEmployeePage enterLastName(String LastName){
        System.out.println("sending Last name");
        Elements.sendData(driver,this.LastName,LastName);
        return this;
    }
    public CreateEmployeePage enterEmployeeId(Integer EmployeeId){
        System.out.println("sending Employee ID");
        Elements.sendData(driver,this.EmployeeId,EmployeeId.toString());
        return this;
    }

    public CreateEmployeePage saveEmployeeData(){
        Elements.submitData(driver,this.SaveEmployeeData);
        System.out.println("Save Employee Data");

        return this;
    }

    // assert create employee successfully
    public void assertEmployeeCreationSuccessfully(String Expected){
        // wait until page changes
        Waits.waitForUrlContains(driver,Expected);
        Assert.assertTrue(driver.getCurrentUrl().contains(Expected));
    }

    public void assertFirstNameRequired(){
        Assert.assertEquals(Elements.getData(driver,ActualRequiredMessage), "Required");

    }


}

