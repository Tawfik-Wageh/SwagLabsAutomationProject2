package com.swaglabs.Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class ValidationsUtil {

    private final WebDriver driver;

    // BrowserActions Bot
    private final BrowserActionsUtil browserActions;

    // to use BOT Pattern
    public ValidationsUtil(WebDriver driver) {
        this.driver = driver;
        browserActions = new BrowserActionsUtil(driver);

    }

    @Step("Validating that the condition is true")
    public void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Step("Validating that the condition is false")
    public  void validateFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    @Step("Validate Equals")
    public  void validateEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Validate Not Equals")
    public  void validateNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

   @Step("Validate Page URL : {expectedUrl}")
   public void validatePageUrl( String expectedUrl ) {
        Assert.assertEquals(browserActions.getCurrentUrl(),  expectedUrl);
    }

   @Step("Validate Page Title : {expectedTitle}")
   public  void validatePageTitle( String expectedTitle) {
        Assert.assertEquals(browserActions.getPageTitle(), expectedTitle);
    }


}
