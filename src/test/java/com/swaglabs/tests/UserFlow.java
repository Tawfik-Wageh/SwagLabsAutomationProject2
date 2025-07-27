package com.swaglabs.tests;

import com.swaglabs.Listeners.TestNGListeners;
import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.InformationPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.Utils.*;
import org.testng.annotations.*;

import static com.swaglabs.Utils.TimeStampUtil.getTimeStamp;

 
@Listeners(TestNGListeners.class)
public class UserFlow {

    GUIDriver driver;
    JsonUtil testData;
    String FIRST_NAME;
    String LAST_NAME;

    //Test Cases :
    @Test
    public void successfulLoginTest() {
        new LoginPage(driver)
                .enterUsername(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickOnLoginButton()
                .loginSuccessful();

    }

    @Test(dependsOnMethods = "successfulLoginTest")
    public void addingItemsToCartTest() {

        new HomePage(driver).addSpecificItemToCart(testData.getJsonData("product-names.item1.name"))
                .assertItemAddedToCart(testData.getJsonData("product-names.item1.name"));

    }

    @Test(dependsOnMethods = "addingItemsToCartTest")
    public void checkoutItemTest() {

        new HomePage(driver).clickOnCartIcon()
                .assertItemDetails(testData.getJsonData("product-names.item1.name"),
                        testData.getJsonData("product-names.item1.price"));

    }

    @Test(dependsOnMethods = "checkoutItemTest")
    public void fillingInformationFormTest() {
        new CartPage(driver).clickOnCheckoutButton()
                .fillingInformationForm(FIRST_NAME,LAST_NAME, testData.getJsonData("information-form.postal-code"))
                .assertInformationPage(FIRST_NAME, LAST_NAME, testData.getJsonData("information-form.postal-code"));
    }

    @Test(dependsOnMethods = "fillingInformationFormTest")
    public void finishCheckoutTest() {
        new InformationPage(driver)
                .clickingOnContinueButton()
                .clickOnFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmation-message"));

    }

    //Configuration :

    @BeforeClass
    public void beforeClass() {

        testData = new JsonUtil("test-data");
        FIRST_NAME = testData.getJsonData("information-form.first-name") + getTimeStamp();
        LAST_NAME = testData.getJsonData("information-form.last-name") + getTimeStamp();

        String browserName = PropertiesUtil.getPropertyValue("BROWSER_TYPE");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }


    @AfterClass
    public void tearDown() {
        // Close the browser after the test
       driver.browserActions().closeBrowser();

    }


}
