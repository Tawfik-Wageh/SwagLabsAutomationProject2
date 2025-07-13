package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ElementActionsUtil {

    private WebDriver driver;

    // Waits Bot to handle waits for elements
    private WaitsUtil waits ;



    public ElementActionsUtil(WebDriver driver) {
        this.driver = driver;
        waits = new WaitsUtil(driver);

    }

    // Clicks on an element after waiting for it to be visible and scrolling to it
    @Step("Sending data to element: {locator} with data: {data}")
    public void type(By locator, String data) {

        waits.waitForElementVisible( locator);
        scrollToElement( locator);
        findElement(locator).sendKeys(data);
        LogsUtil.info("Sent data: " + data + " to element: " + locator);

    }

    // Clicks on an element after waiting for it to be clickable and scrolling to it
    @Step("Clicking on element: {locator}")
    public void click(By locator) {

        waits.waitForElementClickable( locator);
        scrollToElement(locator);
        findElement(locator).click();
        LogsUtil.info("Clicked on element: " + locator);

    }

    // Gets the text of an element after waiting for it to be visible
    @Step("Getting text from element: {locator}")
    public String getText( By locator) {
        waits.waitForElementVisible( locator);
        scrollToElement( locator);
        LogsUtil.info("Retrieved text from element: " + locator.toString() , " - Text: " + findElement( locator).getText());
        return findElement(locator).getText();

    }

    //finds an element and returns it
    public WebElement findElement( By locator) {
    LogsUtil.info("Finding element: " + locator.toString());
        return driver.findElement(locator);
    }

    // Gets the text from an input field (value attribute) after waiting for it to be visible
    public String getTextFromInputField( By locator) {
        waits.waitForElementVisible( locator);
        scrollToElement( locator);
        LogsUtil.info("Retrieved text from input field: " + locator.toString() , " Text: " , findElement( locator).getDomAttribute("value"));
        return findElement( locator).getDomAttribute("value");
    }

    // Scrolls the page to the specified element using JavaScript
    @Step("ScrollingUtil to element: {locator}")
    public  void scrollToElement( By locator) {
        LogsUtil.info("ScrollingUtil to element: " + locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                findElement( locator));
    }

}
