package com.jetbrains.youtrack.pageObjects;

import com.jetbrains.youtrack.base.Base;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;


public class SignUp extends Base {
    public SignUp(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(id = "id_l.R.user_fullName")
    private static WebElement fullName;

    @FindBy(id = "id_l.R.user_email")
    private static WebElement email;

    @FindBy(id = "id_l.R.password")
    private static WebElement pwd;

    @FindBy(id = "id_l.R.confirmPassword")
    private static WebElement pwdConfirm;

    @FindBy(id = "id_l.R.confirmPasswordid_l.R.register")
    private static WebElement registerButton;

    //actions
    @Step("new user register")
    public void registerNewUser(String name, String mail, String pass) {
        driver.get(signUpPageUrl);
        type(fullName, name);
        type(email, mail);
        type(pwd, pass);
        type(pwdConfirm, pass);
        click(registerButton);
    }
    public static void click(WebElement element) {
        element.click();
        test.log(LogStatus.INFO, "Clicking on : " + element);
    }

    public static void type(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
        test.log(LogStatus.INFO, "Typing in : " + element + " the value as " + value);

    }
}
