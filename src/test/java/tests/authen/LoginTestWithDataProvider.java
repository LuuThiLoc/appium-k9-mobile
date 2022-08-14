package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authentication.LoginFlow;

public class LoginTestWithDataProvider {

    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCreds loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);


        try {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
                loginFlow.goToLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class LoginCreds {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public LoginCreds(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @DataProvider
    public LoginCreds[] loginCredData(){ //LoginCreds[] : mảng 1 chiều
        LoginCreds loginCred01 = new LoginCreds("teo@s", "1234567");
        LoginCreds loginCred02 = new LoginCreds("teo@sth.com","1234567");
        LoginCreds loginCred03 = new LoginCreds("teo@sth.com","12345678");
        return new LoginCreds[]{loginCred01, loginCred02, loginCred03};
    }
}
