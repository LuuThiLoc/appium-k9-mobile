package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authentication.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {

    @Test
    public void testLogin() {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        // Way: List<> --> permit duplicated values
        List<LoginCreds> loginCreds = new ArrayList<>();
        loginCreds.add(new LoginCreds("teo@","12345678"));
        loginCreds.add(new LoginCreds("teo@sth.com","1234567"));
        loginCreds.add(new LoginCreds("teo@sth.com","12345678"));

        try {

//            // Way 1: credData.keySet() --> not permit duplicated + auto sort credData
//            Map<String, String> credData = new HashMap<>();
//            credData.put("teo@", "12345678");
//            credData.put("teo@sth.xyz", "1234567");
//            credData.put("teo@sth.com", "12345678");
//
//            List<String> allEmails = new ArrayList<>(credData.keySet());
//            for (int keyIndex = 0; keyIndex < credData.keySet().size(); keyIndex++) {
//                LoginFlow loginFlow = new LoginFlow(appiumDriver, allEmails.get(keyIndex), credData.get(allEmails.get(keyIndex)));
//                loginFlow.goToLoginScreen();
//                loginFlow.login();
//                loginFlow.verifyLogin();
//            }

            for (LoginCreds loginCred : loginCreds) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
                loginFlow.goToLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
            }

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
}
