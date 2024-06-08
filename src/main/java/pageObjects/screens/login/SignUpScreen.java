package pageObjects.screens.login;

import Utils.gestures.SwipeVertically;
import constants.WaitConstants;
import driverFactory.Platform;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.screens.alert.AlertScreen;
import pageObjects.screens.alert.SignUpAlertScreen;

import java.util.Map;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

public class SignUpScreen extends LoginScreen {
//    private AppiumDriver driver;

    private final By repeatPasswordLocator = accessibilityId("input-repeat-password");
    private final By signUpButtonLocator = accessibilityId("button-SIGN UP");
    private final By androidInvalidRepeatPasswordLocator = xpath("//android.widget.TextView[@text=\"Please enter the same password\"]");
    private final By iosInvalidRepeatPasswordLocator = accessibilityId("Please enter the same password");

    public SignUpScreen(AppiumDriver driver) {
        super(driver);
    }

    private final Map<Platform, By> invalidRepeatPasswordLocatorMap = Map.of(
            Platform.ANDROID, androidInvalidRepeatPasswordLocator,
            Platform.IOS, iosInvalidRepeatPasswordLocator
    );

    private WebElement repeatPasswordElement() {

        return driver.findElement(repeatPasswordLocator);
    }

    private WebElement signUpButtonElement() {

        return driver.findElement(signUpButtonLocator);
    }

    private WebElement invalidRepeatPasswordElement() {

        By locator = mobileInteractionHelper.getLocatorIsMappedCurrentPlatform(invalidRepeatPasswordLocatorMap);

        return mobileInteractionHelper.waitElementLocatedAndFindElement(locator);
    }

    public SignUpScreen verifySignUpFormDisplayed() {

//        mobileActions.waitVisibilityOfElementLocated(signUpButtonLocator); // small screen doesn't visibility sign-up button
        mobileInteractionHelper.waitVisibilityOfElementLocated(repeatPasswordLocator);

        return this;
    }

    public SignUpScreen inputEmail(String email) {

        mobileInteractionHelper.setText(emailFieldElement(), email);

        return this;
    }

    public SignUpScreen inputPassword(String password) {

        mobileInteractionHelper.setText(passwordFieldElement(), password);

        return this;
    }

    public SignUpScreen inputRepeatPassword(String repeatPassword) {

        mobileInteractionHelper.setText(repeatPasswordElement(), repeatPassword);

        return this;
    }

    public SignUpScreen clickOnSignUpButton() {

        // TODO: On smaller screenTextConstants there could be a possibility that the button is not shown
        if (!mobileInteractionHelper.isElementDisplayed(signUpButtonLocator)) {

            SwipeVertically swipeVertically = new SwipeVertically(driver, 0.5, 0.2, 0.8, WaitConstants.FAST_MOVE);

            swipeVertically.swipeUp();
        }
        mobileInteractionHelper.waitVisibilityOfElementLocated(signUpButtonLocator);
        signUpButtonElement().click();

        return this;
    }

    public SignUpScreen verifyInvalidEmailMessage(String expectMessage) {

        String actualMessage = invalidEmailLabelElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

        return this;
    }

    public SignUpScreen verifyInvalidPasswordMessage(String expectMessage) {

        String actualMessage = invalidPasswordLabelElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

        return this;
    }

    public void verifyInvalidRepeatPasswordMessage(String expectMessage) {

        String actualMessage = invalidRepeatPasswordElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

    }

    public AlertScreen SwitchToAlert() {
        return new SignUpAlertScreen(driver);
    }

}
