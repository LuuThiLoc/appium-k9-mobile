package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginScreenMod03 {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreenMod03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginScreenMod03 inputUsernameElem(String usernameTxt) {
        if (!usernameTxt.isEmpty()) appiumDriver.findElement(usernameSel).sendKeys(usernameTxt);
        return this;
    }

    public LoginScreenMod03 inputPasswordElem(String passwordTxt) {
        if (!passwordTxt.isEmpty()) appiumDriver.findElement(passwordSel).sendKeys(passwordTxt);
        return this;
    }

    public LoginScreenMod03 clickOnLoginBtnElem() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }
}
