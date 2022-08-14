package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

import java.util.List;

public class XpathLearning {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

//        List<MobileElement> elements = appiumDriver.findElements(MobileBy.AccessibilityId("taolao"));
//
//        if (!elements.isEmpty())  //nếu mong muốn của mình là ko tìm ra elements
//            Assert.fail("....reason....");


//        Exception e = null;
//
//        try {
//            MobileElement element = appiumDriver.findElement(MobileBy.AccessibilityId("taolao"));
//        } catch (NoSuchElementException noSuchElementException) {
//            e = noSuchElementException;
//        }

//        if (e == null) {
//            Assert.fail("reason...");
//        }

        try {
            // Navigate to Login Screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            // Find all matching elements
            List<MobileElement> credFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.TextView"));

            final int USER_INDEX = 0;
            final int PASSWORD_INDEX = 1;

            credFieldsElem.get(USER_INDEX).sendKeys("teo@sth.com");
            credFieldsElem.get(PASSWORD_INDEX).sendKeys("12345678");

            // Find login into text by UISelector
            MobileElement loginInstructionElem = appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionElem.getText());

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}

//android.widget.EditText[@content-desc="input-email"]
//android.widget.TextView[@text, "When the device"]