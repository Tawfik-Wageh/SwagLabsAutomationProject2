package com.swaglabs.Utils;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertionUtil extends SoftAssert {

    public static CustomSoftAssertionUtil softAssert = new CustomSoftAssertionUtil();


    public static void customAssertAll(ITestResult testResult) {
        try {
            softAssert.assertAll("Custom soft assertions");
        } catch (AssertionError e) {
            System.out.println("Soft assertions failed: " + e.getMessage());
            testResult.setStatus(ITestResult.FAILURE);
            testResult.setThrowable(e);

        }
        finally {
            reInitializeSoftAssert();
        }
    }

    private static void reInitializeSoftAssert() {
        softAssert = new CustomSoftAssertionUtil();
    }


}
