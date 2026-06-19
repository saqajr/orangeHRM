package com.orangehrm.Drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver {

    private static final ThreadLocal<ChromeDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) //Edge edge EDGE
    {
        switch (browser.toLowerCase())
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;

        }

    }
    public static ChromeDriver getDriver()
    {
        return driverThreadLocal.get();
    }
    public static void quitDriver()
    {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
