package com.swaglabs.Utils;

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


    @Step("Sending data to element: {locator} with data: {data}")
    public void sendDataToElement(By locator, String data) {

        waits.waitForElementVisible( locator);
        scrollToElement( locator);
        findElement(locator).sendKeys(data);
        LogsUtil.info("Sent data: " + data + " to element: " + locator);

    }


    @Step("Clicking on element: {locator}")
    public void clickingOnElement(By locator) {

        waits.waitForElementClickable( locator);
        scrollToElement(locator);
        findElement(locator).click();
        LogsUtil.info("Clicked on element: " + locator);

    }


    @Step("Getting text from element: {locator}")
    public String getTextFromElement(By locator) {
        waits.waitForElementVisible( locator);
        scrollToElement( locator);
        LogsUtil.info("Retrieved text from element: " + locator.toString() , " - Text: " + findElement( locator).getText());
        return findElement(locator).getText();

    }


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
