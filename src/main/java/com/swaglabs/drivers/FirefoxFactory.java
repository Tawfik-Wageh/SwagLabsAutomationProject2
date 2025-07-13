package com.swaglabs.drivers;

import com.swaglabs.utils.PropertiesUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class FirefoxFactory extends AbstractDriver implements WebDriverOptionsAbstract<FirefoxOptions> {

    @Override
    public FirefoxOptions getOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.setAcceptInsecureCerts(true);
        if(!PropertiesUtil.getPropertyValue("EXECUTION_TYPE").equalsIgnoreCase("local")){
            firefoxOptions.addArguments("--headless");
        }
        return firefoxOptions;

    }

    @Override
    public WebDriver initializeDriver() {
        return new FirefoxDriver(getOptions());
    }
}
