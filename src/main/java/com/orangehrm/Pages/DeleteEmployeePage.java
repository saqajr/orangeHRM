package com.orangehrm.Pages;

import com.orangehrm.Utils.Elements;
import com.orangehrm.Utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.orangehrm.Drivers.WebDriver.getDriver;

public class DeleteEmployeePage {

    private final WebDriver driver ;
    private final By EmployeeName = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.oxd-table-filter > div.oxd-table-filter-area > form > div.oxd-form-row > div > div:nth-child(1) > div > div:nth-child(2) > div > div > input");
    private final By SearchButton = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.oxd-table-filter > div.oxd-table-filter-area > form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space");
    private final By DeleteIcon = By.xpath("//i[contains(@class,'bi-trash')]/parent::button");
    private final By successToast = By.xpath("//div[contains(@class,'oxd-toast-content--success')]");
    private final By confirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    public DeleteEmployeePage(WebDriver driver){
        this.driver = driver;
    }

    public DeleteEmployeePage goToEmployeesPage(String Url){
        driver.get(Url);
        return this ;
    }
    public DeleteEmployeePage enterFullEmployeeName(String EmployeeName){
        // send text to input field
        Elements.sendData(driver,this.EmployeeName,EmployeeName);
        // click on the first suggestion records
        Elements.clickButton(driver,
                By.xpath("//div[@role='option']//span[text()='" + EmployeeName + "']")
        );
        return this ;
    }
    public DeleteEmployeePage enterInvalidEmployee(String EmployeeName){
        // send text to input field
        Elements.sendData(driver,this.EmployeeName,EmployeeName);

        return this ;
    }
    public DeleteEmployeePage searchEmployee(){
        Elements.submitData(driver,this.SearchButton);
        return this;
    }

    public DeleteEmployeePage clickOnDeleteIcon(){
        Elements.clickButton(driver,this.DeleteIcon);
        return this ;
    }

    public DeleteEmployeePage clickOnDeleteButton(){
        Elements.clickButton(driver,this.confirmDelete);
        return this ;
    }

    public void assertInvalidDeletion(){
        List<WebElement> deleteIcons =getDriver().findElements(By.xpath("//i[contains(@class,'bi-trash')]"));
        Assert.assertEquals(deleteIcons.size(), 0);
    }

    //assert Employee Delete successfully
    public void assertEmployeeDeletionSuccessfully(){
        // wait until pop-up be visible
        Waits.waitUntilVisible(driver,successToast);
        // check for assertion
        Assert.assertTrue(Elements.getData(driver,successToast).contains("Successfully"));
    }

}
