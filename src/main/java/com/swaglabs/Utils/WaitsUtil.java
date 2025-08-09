package com.swaglabs.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WaitsUtil {

    private WebDriver driver;

    public WaitsUtil(WebDriver driver) {
        this.driver = driver;

    }



    public  WebElement waitForElementPresent( By locator ) {
        LogsUtil.info("Waiting for element to be present: " + locator);
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 ->
                        driver1.findElement(locator));

    }


    public WebElement waitForElementVisible( By locator) {
        LogsUtil.info("Waiting for element to be visible: " + locator);
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> {
                    WebElement element = waitForElementPresent( locator);
                    return element.isDisplayed() ? element : null;
                });
    }


    public WebElement waitForElementClickable( By locator) {
        LogsUtil.info("Waiting for element to be clickable: " + locator);
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> {
                    WebElement element = waitForElementVisible( locator);
                    return element.isEnabled() ? element : null;
                });
    }



}
