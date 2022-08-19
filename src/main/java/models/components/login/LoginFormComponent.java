package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginFormComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
//    private static final By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text,'Please enter a valid email address')]");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
//    private static final By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text,'Please enter at least 8 characters')]");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By actualLoggedInMsgSel = MobileBy.xpath("//*[contains(@text,'You are logged in!')]");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)), this);
    }

    @Step("Input username as {usernameTxt}")
    public void inputUsername(String usernameTxt) {
        if (!usernameTxt.isEmpty()){
            MobileElement usernameElem = appiumDriver.findElement(usernameSel);
            usernameElem.clear();
            usernameElem.sendKeys(usernameTxt);
        }
    }

    @Step("Input password as {passwordTxt}")
    public void inputPassword(String passwordTxt) {
        if (!passwordTxt.isEmpty()) {
            MobileElement passwordElem = appiumDriver.findElement(passwordSel);
            passwordElem.clear();
            passwordElem.sendKeys(passwordTxt);
        }
    }

    @AndroidFindBy(xpath="//*[contains(@text,'Please enter a valid email address')]")
    @iOSXCUITFindBy(iOSNsPredicate="label==\"Please enter a valid email address\"")
    private MobileElement incorrectEmailTxtElem;

    @AndroidFindBy(xpath="//*[contains(@text,'Please enter at least 8 characters')]")
    @iOSXCUITFindBy(iOSNsPredicate="label==\"Please enter at least 8 characters\"")
    private MobileElement incorrectPasswordTxtElem;

    public String getInvalidEmailStr(){
//        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
        return incorrectEmailTxtElem.getText().trim();
    }

    public String getInvalidPasswordStr(){
//        return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
        return incorrectPasswordTxtElem.getText().trim();
    }

    @Step("Click on Login Button")
    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }

    public String getActualLoggedInMsgStr() {
        return appiumDriver.findElement(actualLoggedInMsgSel).getText();
    }
}
