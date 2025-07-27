package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.Utils.CustomSoftAssertionUtil;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {

    //variables

    private GUIDriver driver;

    //locators :

    private final By itemName = By.cssSelector(".inventory_item_name");
    private final By itemPrice = By.cssSelector(".inventory_item_price");
    private final By checkoutButton = By.xpath("//a[@class='btn_action checkout_button']");

    //constructor
    public CartPage (GUIDriver driver) {
        this.driver = driver;

    }

    //actions (Methods) :

    @Step("Get item name")
    private String getItemName() {
        return driver.elementActions().getTextFromElement( itemName);
    }

    @Step("Get item price")
    private String getItemPrice() {
        return driver.elementActions().getTextFromElement( itemPrice);
    }

    @Step("Click on checkout button")
    public InformationPage clickOnCheckoutButton() {
        driver.elementActions().clickingOnElement( checkoutButton);
        return new InformationPage(driver);
    }

    //validations (assertions) :

    @Step("Assert item details")
    public CartPage assertItemDetails(String expectedName, String expectedPrice) {
        String actualItemName = getItemName();
        String actualItemPrice = getItemPrice();

        CustomSoftAssertionUtil.softAssert.assertEquals(actualItemName, expectedName, "Item name does not match");
        CustomSoftAssertionUtil.softAssert.assertEquals(actualItemPrice, expectedPrice, "Item price does not match");
        return this;
    }




}
