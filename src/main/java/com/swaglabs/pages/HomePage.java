package com.swaglabs.pages;

import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.Utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {


    //variables:

    private GUIDriver driver;

    // Constructor
    public HomePage(GUIDriver driver) {

        this.driver = driver;
    }

    //Locators :

    private final By cartIcon = By.cssSelector("[data-icon='shopping-cart']");

    //Actions (Methods) :

    @Step("Navigate to Home Page")
    public HomePage navigateToHomePage() {
        driver.browserActions().navigateToUrl( PropertiesUtil.getPropertyValue("HOME_URL"));

        return this;
    }

    @Step("Add specific item to cart")
    public HomePage addSpecificItemToCart(String itemName) {

        LogsUtil.info( "Adding  " + itemName + " to cart");

        //Relative locator to find the button below the item name using XPath and Dynamic Locator
        By addToCartButton = RelativeLocator.with(By.tagName("button"))
                .below(By.xpath("//div[.='" + itemName + "']" ));

        driver.elementActions().clickingOnElement( addToCartButton);

        return this;
    }

    @Step("Click on cart icon")
    public CartPage clickOnCartIcon() {
        LogsUtil.info("Clicking on cart icon");
        driver.elementActions().clickingOnElement( cartIcon);
        return new CartPage(driver);
    }


    //validations(assertions) :

    @Step("Assert item added to cart")
    public HomePage assertItemAddedToCart(String itemName) {
        By addToCartButton = RelativeLocator.with(By.tagName("button"))
                .below(By.xpath("//div[.='" + itemName + "']" ));

        String actualText =driver.elementActions().getTextFromElement(addToCartButton);
        LogsUtil.info("Actual text: " + actualText);
        driver.validations().validateEquals(actualText, "REMOVE" , "Item was not added to cart" );
        LogsUtil.info("Item " + itemName + " was successfully added to cart");
        return this;
    }


}
