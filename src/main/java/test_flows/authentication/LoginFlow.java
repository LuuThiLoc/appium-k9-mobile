package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;
import tests.BaseFlow;

public class LoginFlow extends BaseFlow {
    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login(){
        LoginScreen loginScreen = new LoginScreen(appiumDriver);

        if (!username.isEmpty()){
            loginScreen.loginFormComp().inputUsername(username);
        }

        if (!username.isEmpty()){
            loginScreen.loginFormComp().inputPassword(password);
        }

        loginScreen.loginFormComp().clickOnLoginBtn();
    }

    public void verifyLogin(){

        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComp();

        boolean isEmailValid = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >= 8;

        if (isEmailValid && isPasswordValid){
            verifyCorrectLoginCreds(loginFormComp);
        }

        if (!isEmailValid){
            verifyIncorrectEmail(loginFormComp);
        }

        if (!isPasswordValid){
            verifyIncorrectPassword(loginFormComp);
        }
    }

    // TODO-HOMEWORK
    @Step("Verify Correct Login Creds")
    private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {
        String actualLoggedInMsgStr = loginFormComp.getActualLoggedInMsgStr();
        String expectedLoggedInMsgStr = "You are logged in!";
        Assert.assertEquals(actualLoggedInMsgStr, expectedLoggedInMsgStr,"[ERR] Actual message and expected message are different!");
    }

    @Step("Verify login with incorrect email")
    private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
        String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
        String expectedInvalidEmailStr = "Please enter a valid email address";
        Assert.assertEquals(actualInvalidEmailStr, expectedInvalidEmailStr, "[ERR]Please enter a valid email address");
    }

    @Step("Verify login with incorrect password")
    private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
        String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
        String expectedInvalidPasswordStr = "Please enter at least 8 characters";
        Assert.assertEquals(actualInvalidPasswordStr, expectedInvalidPasswordStr, "[ERR] Please enter at least 8 characters");
    }
}
