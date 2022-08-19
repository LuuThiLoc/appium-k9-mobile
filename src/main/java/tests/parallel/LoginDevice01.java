package tests.parallel;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCreds;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;

public class LoginDevice01 extends BaseTest {

    @Description("Login Test with data driven")
    @Test(dataProvider = "loginCredData", description = "Login Test")
    @Issue("JIRA-456")
    public void testLogin(LoginCreds loginCred) {
        System.out.println("--> Session ID: " + getDriver().getSessionId());
        LoginFlow loginFlow = new LoginFlow(getDriver(), loginCred.getEmail(), loginCred.getPassword());
        loginFlow.goToLoginScreen();
        loginFlow.login();
        loginFlow.verifyLogin();
    }

    @DataProvider
    public LoginCreds[] loginCredData(){
        String filePath = "\\src\\main\\java\\test_data\\authen\\LoginCreds.json";
        return DataObjectBuilder.buildDataObject(filePath, LoginCreds[].class);
    }
}
