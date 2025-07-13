package com.swaglabs.tests;

import com.swaglabs.Listeners.TestNGListeners;
import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.JsonUtil;
import com.swaglabs.utils.PropertiesUtil;
import org.testng.annotations.*;



@Listeners(TestNGListeners.class)
public class UserFlowTC {


    GUIDriver driver;
    JsonUtil testData;

  @Test
    public void userFlowTest() {

      // fluent pattern approach
      new LoginPage(driver)
              // Step 1: Navigate to the login page
              .enterUsername(testData.getJsonData("login-credentials.username"))
              .enterPassword(testData.getJsonData("login-credentials.password"))
              .clickOnLoginButton()
              .loginSuccessful()
              // Step 2: Add specific item to cart
              .addSpecificItemToCart(testData.getJsonData("product-names.item1.name"))
              .assertItemAddedToCart(testData.getJsonData("product-names.item1.name"))
              .clickOnCartIcon()
              // Step 3: Assert item details in the cart
              .assertItemDetails(testData.getJsonData("product-names.item1.name"),
              testData.getJsonData("product-names.item1.price"))
              .clickOnCheckoutButton()
              // Step 4: Fill an information form
              .fillingInformationForm(testData.getJsonData("information-form.first-name"),
                      testData.getJsonData("information-form.last-name"),
                      testData.getJsonData("information-form.postal-code"))
              .assertInformationPage(testData.getJsonData("information-form.first-name"),
                      testData.getJsonData("information-form.last-name"),
                      testData.getJsonData("information-form.postal-code"))
              .clickingOnContinueButton()
              // Step 5: Assert confirmation of the order
              .clickOnFinishButton()
              .assertConfirmationMessage(testData.getJsonData("confirmation-message"));

        }


    //Configuration :


    @BeforeClass
    public void beforeClass() {
        testData = new JsonUtil("test-data");
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        String browserName = PropertiesUtil.getPropertyValue("BROWSER_TYPE");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close the browser after the test
        driver.browserActions().closeBrowser();

    }

}
