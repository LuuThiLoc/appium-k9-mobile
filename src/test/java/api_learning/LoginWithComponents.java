package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import platform.Platform;

public class LoginWithComponents {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            LoginScreen loginScreen = new LoginScreen(appiumDriver);
            LoginFormComponent loginFormComp = loginScreen.loginFormComp();
            BottomNavComponent bottomNavComp = loginScreen.bottomNavComp();

            bottomNavComp.clickOnLoginIcon();
            loginFormComp.inputUsername("teo@sth.com");
            loginFormComp.inputPassword("12345678");
            loginFormComp.clickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
