package testCases;

import Utils.WaitUtil;
import Utils.gestures.MobileInteractions;
import constants.WaitConstants;
import driverFactory.CapabilityFactory;
import driverFactory.DriverProvider;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static devices.MobileFactory.getEmulator;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected static WebDriverWait wait;
    protected static FluentWait<AppiumDriver> fluentWait;
    protected MobileInteractions mobileInteractionHelper;

    DriverProvider driverProvider;


    @BeforeClass
    public void setUpAppium() {

//        this.driver = DriverFactory.getLocalServerDriver(MobileFactory.getEmulator());
        driverProvider = new DriverProvider();
        Capabilities caps = CapabilityFactory.getCaps(getEmulator());
        driver = driverProvider.getLocalServerDriver(caps);

        WaitUtil waitHelper = new WaitUtil(driver);
        wait = waitHelper.explicitWait();
        fluentWait = waitHelper.fluentWait(WaitConstants.SHORT_FLUENT_WAIT, WaitConstants.POLLING_EVERY);
        mobileInteractionHelper = new MobileInteractions(this.driver);
    }

    @AfterClass
    public void tearDown() {
        driverProvider.quitDriver(driver);
    }

}