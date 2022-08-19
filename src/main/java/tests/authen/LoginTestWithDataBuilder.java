package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_data.DataObjectBuilder;
import test_data.models.LoginCreds;
import test_flows.authentication.LoginFlow;

public class LoginTestWithDataBuilder {

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


    @DataProvider
    public LoginCreds[] loginCredData(){
        String filePath = "\\src\\main\\java\\test_data\\authen\\LoginCreds.json";
        return DataObjectBuilder.buildDataObject(filePath, LoginCreds[].class);
    }
}
