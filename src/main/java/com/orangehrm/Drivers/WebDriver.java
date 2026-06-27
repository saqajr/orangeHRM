package com.orangehrm.Drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriver {

    private static final ThreadLocal<ChromeDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) //Edge edge EDGE
    {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        switch (browser.toLowerCase())
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                if (headless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--window-size=1920,1080"); // needed: --start-maximized doesn't work headless

                }
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
        if (driverThreadLocal.get() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }

    }
}
