package com.swaglabs.Listeners;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.Utils.*;
import org.testng.*;

import java.io.File;

import static com.swaglabs.Utils.PropertiesUtil.loadProperties;


public class TestNGListeners implements IExecutionListener  , ITestListener, IInvokedMethodListener {

    File allureResultsDir = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/Logs");
    File screenshots= new File("test-outputs/screenshots");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("TestNG Execution Started");
        loadProperties();
        FilesUtil.deleteFiles(allureResultsDir);
        FilesUtil.cleanDirectory(logs);
        FilesUtil.cleanDirectory(screenshots);

    }


    @Override
    public void onExecutionFinish() {
        LogsUtil.info("TestNG Execution Finished");
        AllureUtil.generateAllureReport();
        String reportName = AllureUtil.renameAllureReport();
        AllureUtil.openAllureReport(reportName);

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {


        if (method.isTestMethod()) {
            CustomSoftAssertionUtil.customAssertAll(testResult);

            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS:
                    ScreenshotUtil.takeScreenshot(GUIDriver.getInstance() , "Passed-" +testResult.getName());
                    break;
                case ITestResult.FAILURE:
                    ScreenshotUtil.takeScreenshot(GUIDriver.getInstance() ,"Failed-" +testResult.getName());
                    break;
                case ITestResult.SKIP:
                    ScreenshotUtil.takeScreenshot(GUIDriver.getInstance() ,"Skipped-" +testResult.getName());
                    break;
            }
            AllureUtil.attachLogsToAllureReport();
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test Case: " , result.getName(), " - Passed");


    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test Case: " , result.getName(), " - Failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test Case: " , result.getName(), " - Skipped");

    }

}
