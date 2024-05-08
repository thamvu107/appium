package models.screens.login;

import io.appium.java_client.AppiumDriver;
import models.screens.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.xpath;

// MAIN INTERACTION METHODS
public class LoginScreen extends BaseScreen {

    // Scope 01: Keep the selector
    protected final By loginScreenLoc = accessibilityId("Login-screen"); // android + ios
    protected final By loginTabLoc = accessibilityId("button-login-container"); // android + ios
    protected final By signupTabLoc = accessibilityId("button-sign-up-container"); // android + ios
    protected final By emailInputLoc = accessibilityId("input-email");
    protected final By passwordInputLoc = accessibilityId("input-password");
    protected final By invalidEmailLabelLoc = xpath("//android.widget.TextView[@text='Please enter a valid email address']");
    protected final By invalidPasswordLabelLoc = xpath("//android.widget.TextView[@text=\"Please enter at least 8 characters\"]");

    //    protected final By  driver.findElementByAndroidUIAutomator("text(\"Some Text\")");
    // Scope 02: Constructor to POM_AdvancedConcept.md the appiumDriver
    public LoginScreen(final AppiumDriver driver) {

        super(driver);
    }

    protected WebElement loginScreenElement() {

        return driver.findElement(loginScreenLoc);
    }

    protected WebElement loginTabElement() {

        return driver.findElement(loginTabLoc);
    }

    protected WebElement signupTabElement() {
        return driver.findElement(signupTabLoc);
    }

    protected WebElement emailFieldElement() {
        return driver.findElement(emailInputLoc);
    }

    protected WebElement passwordFieldElement() {

        return driver.findElement(passwordInputLoc);
    }

    protected WebElement invalidEmailLabelElement() {

        return driver.findElement(invalidEmailLabelLoc);
    }

    protected WebElement invalidPasswordLabelElement() {

        return driver.findElement(invalidPasswordLabelLoc);
    }

    public LoginScreen clickOnLoginNav() {

        bottomNavComponent().loginNav().click();

        return this;
    }

    public void displayLoginScreen() {

        Assert.assertTrue(loginScreenElement().isDisplayed());

    }

    protected SignInScreen clickOnSingInTab() {
        loginTabElement().click();

        return new SignInScreen(driver);
    }


    protected LoginScreen displaySignInForm() {

        return this;
    }


    protected LoginScreen clickOnSingUpTab() {
        signupTabElement().click();

        return this;
    }

}