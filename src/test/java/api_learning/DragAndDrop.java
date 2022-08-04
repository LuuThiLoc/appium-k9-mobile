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
import java.util.Arrays;
import java.util.List;

public class DragAndDrop {
    static AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

    public static void main(String[] args) {

        dragAndDrop();
//        dragAndDrop2();

        // Verify drag successfully

    }

    public static void dragAndDrop(){
        PointOption startPoint = new PointOption<>();
        PointOption endPoint = new PointOption<>();

        // yStart: 93, 81
        // xStart:
        // row 1: , 25 , 50, , 75, 87
        // row 2: 25, , ,

        int xStart = 0;
        int xEnd = 0;
        int yStart = 0;
        int yEnd = 0;

        int[] pointArr01 = {62, 25, 93, 38};
        int[] pointArr02 = {13, 37, 81, 25};
        int[] pointArr03 = {75, 37, 93, 25};
        int[] pointArr04 = {62, 25, 81, 37};
        int[] pointArr05 = {37, 50, 93, 50};
        int[] pointArr06 = {87, 50, 81, 50};
        int[] pointArr07 = {50, 62, 81, 75};
        int[] pointArr08 = {25, 62, 93, 75};
        int[] pointArr09 = {37, 62, 81, 75};

        List<int[]> pointList = Arrays.asList(pointArr01, pointArr02, pointArr03, pointArr04, pointArr05, pointArr06, pointArr07,pointArr08, pointArr09);

        for (int[] points : pointList) {
            xStart= points[0];
            xEnd= points[1];
            yStart= points[2];
            yEnd= points[3];

            touchActionInDragScreen(startPoint, endPoint, xStart, xEnd, yStart, yEnd);
        }
    }

    public static void dragAndDrop2(){
        PointOption startPoint = new PointOption<>();
        PointOption endPoint = new PointOption<>();

        //686, 1398

//        "width":720,"height":1344,

//        touchActionInDragScreen2(startPoint, endPoint, (402/686)*100, (1123/1398)*100, (165/686)*100, (390/1398)*100);
//        touchActionInDragScreen(startPoint, endPoint, 91/686, 995/1398, 162/686, 572/1398);
//        touchActionInDragScreen(startPoint, endPoint, 538/686, 1122/1398, 167/686, 741/1398);
//        touchActionInDragScreen(startPoint, endPoint, 472/686, 998/1398, 328/686, 405/1398);
//        touchActionInDragScreen(startPoint, endPoint, 272/686, 1122/1398, 339/686, 563/1398);
//        touchActionInDragScreen(startPoint, endPoint, 598/686, 995/1398, 337/686, 732/1398);
//        touchActionInDragScreen(startPoint, endPoint, 342/686, 999/1398, 498/686, 401/1398);
//        touchActionInDragScreen(startPoint, endPoint, 150/686, 1122/1398, 506/686, 566/1398);
//        touchActionInDragScreen(startPoint, endPoint, 217/686, 997/1398, 507/686, 732/1398);

    }

    public static TouchAction touchActionInDragScreen(PointOption startPoint, PointOption endPoint, int xStart, int xEnd, int yStart, int yEnd) {

        try {
            // Navigate to Drag Screen
            MobileElement navDragScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Drag"));
            navDragScreenBtnElem.click();

            // Wait until user is on Drag screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 20L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Drag and Drop\")")));

            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();

            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = xStart * screenWidth / 100;
            int xEndPoint = xEnd * screenWidth / 100;

            int yStartPoint = yStart * screenHeight / 100;
            int yEndPoint = yEnd * screenHeight / 100;

            // Convert coordinates -> PointOption
            startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

                TouchAction touchAction = new TouchAction(appiumDriver);
                touchAction
                        .press(startPoint)
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(15000)))
                        .moveTo(endPoint)
                        .release()
                        .perform();

                Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TouchAction<>(appiumDriver);
    }

    public static TouchAction touchActionInDragScreen2(PointOption startPoint, PointOption endPoint, int xStart, int yStart, int xEnd, int yEnd) {

        try {
            // Navigate to Drag Screen
            MobileElement navDragScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Drag"));
            navDragScreenBtnElem.click();

            // Wait until user is on Drag screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 20L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Drag and Drop\")")));

            // Convert coordinates -> PointOption
            startPoint = new PointOption<>().withCoordinates(xStart, yStart);
            endPoint = new PointOption<>().withCoordinates(xEnd, yEnd);

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TouchAction<>(appiumDriver);
    }
}
