package api_learning;

import driver.Platforms;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import driverFactory.Platform;
import helpers.LocatorMapperHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static devices.MobileFactory.getEmulator;

public class HandleVariantLocators {
    private static final Map<Platforms, By> navloginBtnLocMap = Map.of(Platforms.ANDROID, AppiumBy.accessibilityId("Login"), Platforms.IOS, AppiumBy.accessibilityId("try-to-have-difference-here"));

    private static final Map<Platform, By> navloginButtonLocatorMap = Map.of(Platform.ANDROID, AppiumBy.accessibilityId("Login"), Platform.IOS, AppiumBy.accessibilityId("Login"));
    private static final By emailFieldLoc = AppiumBy.accessibilityId("input-email");
    private static final By passwordLoc = AppiumBy.accessibilityId("input-password");
    private static final By loginBtnLoc = AppiumBy.accessibilityId("button-LOGIN");

    public static DriverProvider driverProvider;
    public static AppiumDriver driver;

    public static void main(String[] args) {

//        AppiumDriver driver = DriverFactory.getLocalServerDriver(MobileFactory.getIOSsMobile());
//        AppiumDriver driver = DriverFactory.getLocalServerDriver(CapabilityFactory.getCaps(getEmulator()));

        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);

        try {
            LocatorMapperHelper elementHandler = new LocatorMapperHelper(driver);
            WebElement navLoginBtnEle = elementHandler.findElement(navloginButtonLocatorMap);
            navLoginBtnEle.click();

            WebElement emailFieldEle = driver.findElement(emailFieldLoc);
            emailFieldEle.clear();
            emailFieldEle.sendKeys("teo@sth.com");

            // Input password
            WebElement passwordEle = driver.findElement(passwordLoc);
            passwordEle.sendKeys("12345678");

            // Click on Login Btn
            WebElement loginBtnEle = driver.findElement(loginBtnLoc);
            loginBtnEle.click();

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
