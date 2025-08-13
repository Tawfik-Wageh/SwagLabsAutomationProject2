package com.swaglabs.Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActionsUtil {

    // driver Bot
    private final WebDriver driver;

    // to use BOT Pattern
    public BrowserActionsUtil(WebDriver driver) {
        this.driver = driver;

    }

    // navigate to a URL
    @Step("Navigating to URL: {url}")
    public void navigateToUrl( String url) {
        driver.get(url);
        LogsUtil.info("Navigated to URL: " + url);
    }

    //close the browser
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


}
