package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import platform.Platform;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NarrowDownSearchingScope {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();

            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);


            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            List<MobileElement> notificationElems = appiumDriver.findElements(MobileBy.id("android:id/notification_main_column"));

            Map<String, String> notificationContents = new HashMap<>();
            for (MobileElement notificationElem : notificationElems) {
                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/title"));
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/big-text"));

//                System.out.println("Title Notification is: " + titleElem.getText());
//                System.out.println("Content Notification is" + contentElem.getText());

                notificationContents.put(titleElem.getText().trim(), contentElem.getText().trim());

                // Verification:
                // App bug > always green | False negative
                if (notificationContents.keySet().isEmpty()) {
                    throw new RuntimeException("No Notification!");
                }

                for (String title : notificationContents.keySet()) { //: a key set
                    System.out.println("Title is: " + title); //key
                    System.out.println("Title is: " + notificationContents.get(title));//value
                }
            }

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}

