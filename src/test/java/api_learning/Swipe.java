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
    static AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

    public static void main(String[] args) {
        // Forms Screen:
//        swipeUpVertically(25, 1);
//        swipeUpVerticallyWithSteps(10, 5);

        // Swipe Screen:
//        SwipeRightToLeft();
        SwipeLeftToRight();

        appiumDriver.quit();
    }

    // Swipe up from 90 -> 10
    public static void swipeUpVertically(int step, int times) {

        PointOption startPoint = new PointOption<>();
        PointOption endPoint = new PointOption<>();

        TouchAction touchAction = touchActionInFormsScreen(startPoint, endPoint, step, times);
    }

    // Swipe up | step 10%, 5 times
    public static void swipeUpVerticallyWithSteps(int step, int times) {
        PointOption startPoint = new PointOption<>();
        PointOption endPoint = new PointOption<>();

        touchActionInFormsScreen(startPoint, endPoint, step, times);
    }

    public static void SwipeRightToLeft() {

        PointOption startPoint = new PointOption<>();
        PointOption endPoint = new PointOption<>();

        touchActionInSwipeScreen(startPoint, endPoint);
    }

    public static void SwipeLeftToRight() {

        PointOption startPoint = new PointOption<>();
        PointOption endPoint = new PointOption<>();

        touchActionInSwipeScreen(startPoint, endPoint)
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(startPoint)
                .release()
                .perform();
    }

    public static TouchAction touchActionInSwipeScreen(PointOption startPoint, PointOption endPoint) {

        try {
            // Navigate to Swipe Screen
            MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtn.click();

            // Wait until user is on Form screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();

            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;

            int yStartPoint = 70 * screenHeight / 100;
            int yEndPoint = 70 * screenHeight / 100;

            // Convert coordinates -> PointOption
            startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            for (int i = 0; i < 3; i++) {
                TouchAction touchAction = new TouchAction(appiumDriver);
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TouchAction<>(appiumDriver);
    }

    public static TouchAction touchActionInFormsScreen(PointOption startPoint, PointOption endPoint, int step, int times) {

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
            startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            for (int i = 0; i < times; i++) {
                TouchAction touchAction = new TouchAction(appiumDriver);
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                        .moveTo(endPoint)
                        .release()
                        .perform();

                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TouchAction<>(appiumDriver);
    }
}

