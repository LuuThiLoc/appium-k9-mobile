package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod02;
import models.pages.LoginScreenMod03;
import platform.Platform;

public class LoginWithPOMMod03 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            LoginScreenMod03 loginScreen = new LoginScreenMod03(appiumDriver);
            loginScreen.inputUsernameElem("teo@sth.com").inputPasswordElem("12345678").clickOnLoginBtnElem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
