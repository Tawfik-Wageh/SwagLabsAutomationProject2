package com.swaglabs.pages;


import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.Utils.CustomSoftAssertionUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.swaglabs.Utils.PropertiesUtil.getPropertyValue;


public class LoginPage {
    // Variables :

    private final GUIDriver driver;

    // Locators :

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    //Constructor
    public LoginPage(GUIDriver driver) {

        this.driver = driver;
    }


    // Actions (Methods) :

    @Step("Navigate to Login Page")
    public void navigateToLoginPage() {

    driver.browserActions().navigateToUrl(getPropertyValue("BASE_URL"));
    }

    @Step("Enter Username: {usernameText}")
    public LoginPage enterUsername(String usernameText) {
        driver.elementActions().sendDataToElement(username, usernameText);
        return this;
    }

    @Step("Enter Password: {passwordText}")
    public LoginPage enterPassword(String passwordText) {
        driver.elementActions().sendDataToElement( password, passwordText);
        return this;
    }

    @Step("Click on Login Button")
    public LoginPage clickOnLoginButton() {
        driver.elementActions().clickingOnElement( loginButton);
        return this;
    }

    @Step("Get Error Message")
    public String getErrorMessage() {

        return driver.elementActions().getTextFromElement( errorMessage);
    }


    //Validations(assertions) :

    @Step("Assert Login Page URL")
    public LoginPage assertLoginPageUrl() {
        CustomSoftAssertionUtil.softAssert.assertEquals(driver.browserActions().getCurrentUrl(), getPropertyValue("HOME_URL"), "Login page URL is not as expected");
        return this;
    }

    @Step("Assert Login Page Title")
    public LoginPage assertLoginPageTitle() {
        CustomSoftAssertionUtil.softAssert.assertEquals(driver.browserActions().getPageTitle(), getPropertyValue("APP_TITLE"), "Login page title is not as expected");
        return this;
    }

    @Step("Login Successful with Soft Assertion")
    public LoginPage loginSuccessfulSoftAssert() {
        assertLoginPageUrl().assertLoginPageTitle();
        return this;
    }

    @Step("Login Successful")
    public HomePage loginSuccessful() {
        driver.validations().validatePageUrl( getPropertyValue("HOME_URL"));
        return new HomePage(driver);
    }

    @Step("Login Unsuccessful with Soft Assertion")
    public HomePage loginUnsuccessful() {
        driver.validations().validateEquals(getErrorMessage(), getPropertyValue("ERROR_MESSAGE"), "Login failed with unexpected error message");
        return new HomePage(driver);
    }


}
