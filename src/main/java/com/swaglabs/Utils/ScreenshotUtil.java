package com.swaglabs.Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static com.swaglabs.Utils.TimeStampUtil.getTimeStamp;


public class ScreenshotUtil {

    public static final String SCREENSHOTS_PATH = "test-outputs/screenshots";



    // Private constructor to prevent instantiation
    private ScreenshotUtil() {

    }

   public static void takeScreenshot(WebDriver driver , String screenshotName)  {

       try{
           File screenshot = ((TakesScreenshot) driver)
                   .getScreenshotAs(OutputType.FILE);

           File screenshotFile = new File(SCREENSHOTS_PATH + "/" + screenshotName + "_" + getTimeStamp() + ".png");

           FileUtils.copyFile(screenshot, screenshotFile);
           AllureUtil.attachScreenshotToAllureReport(screenshotName, screenshotFile.getPath());
       } catch (Exception e) {
           LogsUtil.error("Failed to take screenshot: " + e.getMessage());

       }

    }



}
