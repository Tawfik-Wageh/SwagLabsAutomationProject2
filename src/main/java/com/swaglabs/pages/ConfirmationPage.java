package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ConfirmationPage {

    // Variables
    private final GUIDriver driver;

    //constructor
    public ConfirmationPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators :

    private final By confirmationMessage = By.className("complete-header");

    //Actions (Methods) :

    @Step("Get confirmation message")
    public String getConfirmationMessage() {
        return driver.elementActions().getTextFromElement(confirmationMessage);
    }

    // validations (assertions) :

    @Step("Assert confirmation message: {expectedMessage}")
    public void assertConfirmationMessage(String expectedMessage) {
        String actualMessage = getConfirmationMessage();
        driver.validations().validateEquals(actualMessage, expectedMessage, "Confirmation message does not match");
    }

}


