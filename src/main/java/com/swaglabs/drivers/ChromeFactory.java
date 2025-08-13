package com.swaglabs.drivers;

import com.swaglabs.Utils.PropertiesUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

public class ChromeFactory extends AbstractDriver implements WebDriverOptionsAbstract<ChromeOptions> {

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> ChromePrefs = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autoFillEnabled", false);
        chromeOptions.setExperimentalOption("prefs", ChromePrefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if(!PropertiesUtil.getPropertyValue("EXECUTION_TYPE").equalsIgnoreCase("local")){

            // headless mode means the browser will run in the background without a GUI
            // this is useful for CI/CD pipelines or when you don't need to see the browser
            chromeOptions.addArguments("--headless");
        }
        return chromeOptions;
    }


    @Override
    public WebDriver initializeDriver() {
        return new ChromeDriver(getOptions());

    }
}
