package com.orangehrm.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;

public class Waits {




    public static void waitUntilVisible(WebDriver driver , By locator ){
        // wait until element is visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void waitForClickable(WebDriver driver , By locator ){
        // wait until element is visible
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitForUrlToBe(WebDriver driver , String ExpectedUrl){
        // wait until element is visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(ExpectedUrl));
    }

    public static void waitForUrlContains(WebDriver driver , String Expected){
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains(Expected));

    }

    //override for waitUntilVisible
    public static void waitUntilVisible(WebDriver driver , WebElement locator){
        // wait until element is visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(locator));
    }

    public static void sleepForDemo() {
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
    }
}
