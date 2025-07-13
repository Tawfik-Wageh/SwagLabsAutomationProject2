package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class AllureUtil {

    public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results";

    static String  REPORT_PATH = "test-outputs/allure-report";

    static String USER_HOME = System.getProperty("user.home");

   //  Hint : File.separator = "\" on Windows

    static String ALLURE_PATH = USER_HOME + File.separator + "scoop" + File.separator + "apps"
            + File.separator + "allure" + File.separator + "2.34.1" + File.separator
            + "bin" + File.separator + "allure" ;


    // Private constructor to prevent instantiation
    private AllureUtil() {
    }

    // Generates the Allure report from the results directory
    public static void generateAllureReport() {

        if(PropertiesUtil.getPropertyValue("os.name").toLowerCase().contains("win")) {
            String WINDOWS = ALLURE_PATH + ".bat";
            TerminalUtil.executeCommand(WINDOWS , "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH , "clean" , "--single-file");
            LogsUtil.info("Allure report generated successfully on Windows.");
        }
        else{

            TerminalUtil.executeCommand(ALLURE_PATH , "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH , "clean" , "--single-file");
            LogsUtil.info("Allure report generated successfully on :" + PropertiesUtil.getPropertyValue("os.name"));
        }
    }


    // Renames the Allure report file to include a timestamp
    public static String renameAllureReport(){

        File newName = new File("Report_" + TimeStampUtil.getTimeStamp() + ".html");
        File oldName = new File(REPORT_PATH + File.separator + "index.html");
        FilesUtil.renameFile(oldName , newName);
        return newName.getName();
    }


    // Opens the Allure report in the default browser
    public static void openAllureReport(String reportName) {

        String allureReportPath = REPORT_PATH + File.separator + reportName;
        if(PropertiesUtil.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true")){

            if(PropertiesUtil.getPropertyValue("os.name").toLowerCase().contains("win")) {
                TerminalUtil.executeCommand("cmd.exe", "/c", "start", allureReportPath);

            } else {

                TerminalUtil.executeCommand("open", allureReportPath);

            }
        }
    }


    // Attaches the latest logs file to the Allure report
    public static void attachLogsToAllureReport(){
        try {
            File logsFile = FilesUtil.getLatestFile(LogsUtil.LOGS_PATH);
            assert logsFile != null;
            if (!logsFile.exists()) {
                LogsUtil.warn("Logs file does not exist: " + LogsUtil.LOGS_PATH);
            }
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logsFile.getPath())));
            LogsUtil.info("Logs attached to Allure report ");
        } catch (Exception e) {
            LogsUtil.error("Failed to attach logs to Allure report: " + e.getMessage());
        }
    }


    // Attaches a screenshot to the Allure report
    public static void attachScreenshotToAllureReport(String screenshotName , String screenshotPath) {

        try {
            Allure.addAttachment(screenshotName , Files.newInputStream(Path.of(screenshotPath)));

        } catch (Exception e) {
            LogsUtil.error("Failed to attach screenshot to Allure report: " + e.getMessage());
        }

    }

}
