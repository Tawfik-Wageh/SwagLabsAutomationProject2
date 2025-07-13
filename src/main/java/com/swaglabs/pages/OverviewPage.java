package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


public class OverviewPage {

    // Variables
    private GUIDriver driver;

    // Locators :
    private final By finishButton = By.xpath("//a[text()='FINISH']");

    // Constructor
    public OverviewPage(GUIDriver driver) {
        this.driver = driver;
    }

    // Actions :

    @Step("Click on Finish button")
    public ConfirmationPage clickOnFinishButton() {
        driver.elementActions().click( finishButton);
        return new ConfirmationPage(driver);
    }


}
