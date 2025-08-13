package com.swaglabs.drivers;

import com.swaglabs.Utils.BrowserActionsUtil;
import com.swaglabs.Utils.ElementActionsUtil;
import com.swaglabs.Utils.LogsUtil;

import com.swaglabs.Utils.ValidationsUtil;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class GUIDriver {

   /* The Factory Pattern is a creational design pattern that provides an interface for creating objects
    while allowing subclasses to decide which class to instantiate.
    In automation testing, it helps manage object creation for different browsers, drivers, or test data,
    making the framework scalable and maintainable. */

    /* The BOT pattern in software automation testing refers to a design approach where automated actions
     (such as UI interactions, API calls, or validations) are encapsulated into modular, reusable entities called "bots."
     These bots act as autonomous agents that perform specific tasks, making your automation framework more scalable, maintainable, and flexible.
     */

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

    // to use BOT Pattern
    public ElementActionsUtil elementActions(){
        return new ElementActionsUtil(get());
    }

    // to use BOT Pattern
    public BrowserActionsUtil browserActions() {
        return new BrowserActionsUtil(get());
    }

    // to use BOT Pattern
    public ValidationsUtil validations() {
        return new ValidationsUtil(get());
    }
}

