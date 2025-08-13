package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.Utils.CustomSoftAssertionUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class InformationPage {
    //variables :

    private final GUIDriver driver;

    //locators :

    private final By firstName = By.id("first-name");

    private final By lastName = By.id("last-name");

    private final By zipCode = By.id("postal-code");

    private final By continueButton = By.xpath("//input[@value='CONTINUE']");


    //constructor
    public InformationPage(GUIDriver driver) {
        this.driver = driver;

    }

    // Actions (Methods) :

    @Step("filling information form with first name: {fName}, last name: {lName}, zip code: {zip}")
    public InformationPage fillingInformationForm(String fName, String lName, String zip) {
        driver.elementActions().sendDataToElement( firstName, fName);
        driver.elementActions().sendDataToElement( lastName, lName);
        driver.elementActions().sendDataToElement( zipCode, zip);
        return this;
    }

    @Step("Clicking on continue button")
    public OverviewPage clickingOnContinueButton() {
        driver.elementActions().clickingOnElement( continueButton);
        return new OverviewPage(driver);
    }


    //validations (assertions) :
    @Step("Assert information page")
    public void assertInformationPage(String expectedFirstName, String expectedLastName, String expectedZipCode) {
        CustomSoftAssertionUtil.softAssert.assertEquals(driver.elementActions().getTextFromInputField( firstName ) , expectedFirstName);
        CustomSoftAssertionUtil.softAssert.assertEquals(driver.elementActions().getTextFromInputField( lastName ) , expectedLastName);
        CustomSoftAssertionUtil.softAssert.assertEquals(driver.elementActions().getTextFromInputField( zipCode ) , expectedZipCode);
    }

}
