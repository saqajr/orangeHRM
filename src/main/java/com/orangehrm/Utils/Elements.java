package com.orangehrm.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Elements {



    public static void sendData(WebDriver driver , By locator , String data)
    {
        // wait until element is visible
        Waits.waitUntilVisible(driver,locator);
        //find element
        WebElement element = driver.findElement(locator);
        //focus on element
        element.click();
        // send data to the target element
        element.sendKeys(data);
    }

    public static void submitData(WebDriver driver , By locator){

        // wait until element is visible
        Waits.waitUntilVisible(driver,locator);

        // scrolling to element
        scrolling(driver,locator);

        // find element with driver then submit
        driver.findElement(locator).submit();
    }

    public static String getData(WebDriver driver , By locator){
        // wait until element is visible
        Waits.waitUntilVisible(driver,locator);
        return driver.findElement(locator).getText();
    }

    public static void clickButton(WebDriver driver , By locator){
        // wait until element is Clickable
        Waits.waitForClickable(driver,locator);

        // scrolling to element
        scrolling(driver,locator);

        // find element with driver then submit
        driver.findElement(locator).click();
    }

    public static int getElementsSize(WebDriver driver , By locator){
        // wait until element is visible
        Waits.waitUntilVisible(driver,locator);

        // find elements size with driver
        return driver.findElements(locator).size();
    }


    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement( locator));
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(driver.findElement(locator)).selectByVisibleText(option);
    }


}

