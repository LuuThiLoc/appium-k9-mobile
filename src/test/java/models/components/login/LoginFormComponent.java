package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginFormComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text,'Please enter a valid email address')]");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text,'Please enter at least 8 characters')]");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By actualLoggedInMsgSel = MobileBy.xpath("//*[contains(@text,'You are logged in!')]");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputUsername(String usernameTxt) {
        if (!usernameTxt.isEmpty()){
            MobileElement usernameElem = appiumDriver.findElement(usernameSel);
            usernameElem.clear();
            usernameElem.sendKeys(usernameTxt);
        }
    }

    public String getInvalidEmailStr(){
        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
    }

    public void inputPassword(String passwordTxt) {
        if (!passwordTxt.isEmpty()) {
            MobileElement passwordElem = appiumDriver.findElement(passwordSel);
            passwordElem.clear();
            passwordElem.sendKeys(passwordTxt);
        }
    }

    public String getInvalidPasswordStr(){
        return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
    }

    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }

    public String getActualLoggedInMsgStr() {
        return appiumDriver.findElement(actualLoggedInMsgSel).getText();
    }
}
