package models.screens.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import models.commponents.dialog.DialogComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SignInScreen extends LoginScreen {

    private final By signInButtonLocator = AppiumBy.accessibilityId("button-LOGIN");
    private DialogComponent successSignInDialog;

    public SignInScreen(AppiumDriver driver) {
        super(driver);
    }

    private WebElement signInButtonElement() {

        return this.driver.findElement(signInButtonLocator);
    }

    public SignInScreen inputEmail(String email) {

        emailFieldElement(driver).clear();
        emailFieldElement(driver).sendKeys(email);

        return this;
    }

    public SignInScreen inputPassword(String password) {

        passwordFieldElement().clear();
        passwordFieldElement().sendKeys(password);

        return this;
    }

    public void clickOnLoginButton() {

        // TODO: On smaller screens there could be a possibility that the button is not shown

        signInButtonElement().click();
    }

    public SignInScreen seeInvalidEmailMessage(String expectMessage) {

        String actualMessage = invalidEmailLabelElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

        return this;
    }

    public SignInScreen seeInvalidPasswordMessage(String expectMessage) {

        String actualMessage = invalidPasswordLabelElement().getText();
        Assert.assertEquals(actualMessage, expectMessage);

        return this;
    }


}
