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
import java.util.List;

public class SwipeHorizontalToFindObject {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Swipe Screen
            MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtn.click();

            // Wait until user is on Form screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 20L);
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
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe
            // Swipe from right to left to find the object: SUPPORT-VIDEO

            TouchAction touchAction = new TouchAction(appiumDriver);
            List<MobileElement> cardViewGroupElems = appiumDriver.findElements(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"Carousel\"]/android.view.ViewGroup)"));

            // Support Video
            String textSupportVideo = "SUPPORT VIDEOS";

            for (int i = 0; i < cardViewGroupElems.size(); i++) {

                if (!cardViewGroupElems.get(i).getText().equals(textSupportVideo))
                    touchAction
                            .press(startPoint)
                            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                            .moveTo(endPoint)
                            .release()
                            .perform();
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}

