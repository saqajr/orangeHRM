package com.orangehrm.Utils;

import io.qameta.allure.Allure;

import java.nio.file.Files;
import java.nio.file.Path;

public class AllureReports {

    public static void attachScreenShotToAllure(String screenShotName,String screenShotPath){

        try{
            Allure.addAttachment(screenShotName, Files.newInputStream(Path.of(screenShotPath)));
        } catch (Exception e) {
            Logs.error("Failed to attach screenshot "+e.getMessage());
        }
    }
}
