package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class Swipe {

    public static void main(String[] args) {

        swipeVertically();

        swipeVertically(10, 5);

    }

    // Swipe up from 90 -> 10
    public static void swipeVertically() {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms Screen
            MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtn.click();

            // Wait until user is on Form screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();

            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 90 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }

    // Swipe up | step 10%, 5 times
    public static void swipeVertically(int step, int times) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms Screen
            MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtn.click();

            // Wait until user is on Form screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();

            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 65 * screenHeight / 100;
            int nextStep = step * yStartPoint / 100;
            int yEndPoint = yStartPoint - nextStep;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // Move 5 times
            for (int i = 0; i < times; i++) {
                TouchAction touchAction = new TouchAction(appiumDriver);
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}

