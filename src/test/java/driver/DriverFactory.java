package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx {
    private AppiumDriver<MobileElement> appiumDriver;
    public static AppiumDriver<MobileElement> getDriver(Platform platform) {
        AppiumDriver<MobileElement> appiumDriver = null;

        // Send a request to Appium server to launch the app
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "c8adce237d27");
        desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
//        desiredCapabilities.setCapability(TIME_OUT, 240000);
//        desiredCapabilities.setCapability(NEW_COMMAND_TIMEOUT, 60);

        URL appiumServer = null;

        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server @http://localhost:4723/wd/hub");
        }

        switch (platform) {
            case ANDROID:
                appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                break;
        }

        // implicit wait | Interval time = 500ms
        appiumDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

        return appiumDriver;
    }
    public AppiumDriver<MobileElement> getDriver(Platform platform, String udid, String systemPort) {

        if (appiumDriver == null) {
            // Send a request to Appium server to launch the app
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(UDID, udid);
            desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(SYSTEM_PORT, systemPort);

            URL appiumServer = null;
            String targetServer = "http://192.168.1.24:4444/wd/hub";

            try {
                appiumServer = new URL(targetServer);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (appiumServer == null) {
                throw new RuntimeException("Can't construct the appium server.");
            }

            switch (platform) {
                case ANDROID:
                    appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                    break;
                case IOS:
                    appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                    break;
            }

            // implicit wait | Interval time = 500ms
            appiumDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        }
        return appiumDriver;
    }

    public void quitAppiumDriver(){
        if (appiumDriver != null){
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
