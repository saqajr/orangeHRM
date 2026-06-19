package com.orangehrm.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;


import static com.orangehrm.Drivers.WebDriver.getDriver;

public class ScreenShots {


    public static final String ScreenShotspath = "test-outputs/screenshots/";

    public static void takeScreenShot(String sccreenShotName)  {
        try
        {
            File screenShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            File screenShotFile = new File(ScreenShotspath+sccreenShotName+".png");

            FileUtils.copyFile(screenShot,screenShotFile);
            AllureReports.attachScreenShotToAllure(sccreenShotName,ScreenShotspath);
        }
        catch (Exception e){
            Logs.error("Failed to take screenshot :"+ e.getMessage() );

        }


    }
}
