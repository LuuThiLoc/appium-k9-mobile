package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            // Fill the form and login
            emailInputElem.sendKeys("teo@sth.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            // Put the app under test to background in a certain time | simulate pressing home button > relaunch
            appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            // Put the app under test to background till we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Switch into another app | Go to Settings app, toggle wifi
            appiumDriver.activateApp(AppPackages.SETTINGS);

            // Navigate to network list
            By wifiLabelSel = MobileBy.xpath("//*[@text = 'Wi-Fi']");
            appiumDriver.findElement(wifiLabelSel).click();

            // Toggle wifi ON/OFF
            By wifiStatusSel = MobileBy.id("android:id/title");
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            String wifiStatusStr = wifiStatusElem.getText().trim();
            boolean isWifiOn = wifiStatusStr.equalsIgnoreCase("on");
            if (isWifiOn) wifiStatusElem.click();
            Thread.sleep(2000);

            // Comeback to the app | interact with another elements
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text = 'OK']")).click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

}
