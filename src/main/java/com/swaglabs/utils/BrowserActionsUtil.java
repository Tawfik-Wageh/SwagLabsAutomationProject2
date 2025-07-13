package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActionsUtil {

    private WebDriver driver;

    public BrowserActionsUtil(WebDriver driver) {
        this.driver = driver;

    }

    // Method to navigate to a URL
    @Step("Navigating to URL: {url}")
    public void navigateToUrl( String url) {
        driver.get(url);
        LogsUtil.info("Navigated to URL: " + url);
    }

    // Method to refresh the current page
    @Step("Refreshing the current page")
    public void refreshPage() {
        driver.navigate().refresh();
        LogsUtil.info("Page refreshed");
    }

    // Method to close the browser
    @Step("Closing the browser")
    public  void closeBrowser() {
        if (driver != null) {
            LogsUtil.info("Closing the browser");
            driver.quit();
        }
    }

    //Get Current URL
    @Step("Getting the current URL")
    public String getCurrentUrl() {
        LogsUtil.info("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();

    }

    // Get the title of the current page
    @Step("Getting the current page title")
    public String getPageTitle() {
        LogsUtil.info("Current Page Title: " + driver.getTitle());
        return driver.getTitle();
    }


    //reload the current page
    @Step("Reloading the current page")
    public void reloadPage() {
        LogsUtil.info("Reloading the current page");
        driver.navigate().refresh();
    }

}
