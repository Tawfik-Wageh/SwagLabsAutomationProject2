package com.swaglabs.drivers;

import com.swaglabs.Utils.BrowserActionsUtil;
import com.swaglabs.Utils.ElementActionsUtil;
import com.swaglabs.Utils.LogsUtil;

import com.swaglabs.Utils.ValidationsUtil;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class GUIDriver {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public GUIDriver(String browserName) {

       WebDriver driver = getDriver(browserName).initializeDriver();
       setDriver(driver);

    }


    private AbstractDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return new ChromeFactory();
            case "firefox":
                return new FirefoxFactory();
            case "edge":
                return new EdgeFactory();
            default:
               throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

    }

    private void setDriver(WebDriver webDriver) {
        driverThreadLocal.set(webDriver);
    }


    public static WebDriver getInstance() {
        return driverThreadLocal.get();
    }


    public WebDriver get(){
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            LogsUtil.error("WebDriver is not initialized. Please check your setup.");
            fail("WebDriver is not initialized. Please check your setup.");
        }
        return driver;
    }

    public ElementActionsUtil elementActions(){
        return new ElementActionsUtil(get());
    }

    public BrowserActionsUtil browserActions() {
        return new BrowserActionsUtil(get());
    }

    public ValidationsUtil validations() {
        return new ValidationsUtil(get());
    }
}

