package com.jetbrains.youtrack.pageObjects;

import com.jetbrains.youtrack.base.Base;
import com.jetbrains.youtrack.utils.Constant;
import com.jetbrains.youtrack.utils.ExcelUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class CreateUser extends Base {

    //locators to create a user

    @FindBy(id = "id_l.U.createNewUser")
    private WebElement createNewUser;

    @FindBy(id = "id_l.U.cr.login")
    private WebElement login;

    @FindBy(id = "id_l.U.cr.password")
    private WebElement password;

    @FindBy(id = "id_l.U.cr.confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "id_l.U.cr.forcePasswordChange")
    private WebElement forcePasswordChangeOnFirstLogin;

    @FindBy(id = "id_l.U.cr.fullName")
    private WebElement fullName;

    @FindBy(id = "id_l.U.cr.email")
    private WebElement email;

    @FindBy(id = "id_l.U.cr.jabber")
    private WebElement jabber;

    @FindBy(id = "id_l.U.cr.createUserOk")
    private WebElement ok;

    @FindBy(id = "id_l.U.cr.createUserCancel")
    private WebElement cancel;

    @FindBy(xpath = "//div[@class=\"error-bulb2\"]")
    private WebElement alert;

    @FindBy(xpath = "//*[contains(text(), 'Removing null is prohibited')]")
    private WebElement errorForGuestDuplicate;

    public CreateUser(WebDriver driver) throws Exception {
        super(driver);
    }


    public boolean findErrorMessage() {
        try {
            driver.findElement((By) alert);
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private int iTestCaseRow;
    //actions to create a user
    String sUserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_UserName);
    String sPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Password);
    String sConfirm = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Confirmation);


    @Step("create user")
    public void createUser(String username, String pass, String confirm) {
        driver.get(usersPageUrl);
        createNewUser.click();
        login.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirm);
        ok.click();
//        click(createNewUser);
//        type(login, username);
//        type(password, pass);
//        type(confirmPassword, confirm);
//        click(ok);
    }


    @Step("create user with extended info")
    public void createUserExtendedInfo(String username, String pass, String confirm, String fullNa, String mail, String jabb) {
        click(createNewUser);
        type(login, username);
        type(password, pass);
        type(confirmPassword, confirm);
        type(fullName, fullNa);
        type(email, mail);
        type(jabber, jabb);
        click(ok);
    }

    @Step("create user with reset password checkbox enabled")
    public void createUserResetPasswordCheckboxOn(String username, String pass, String confirm) {
        click(createNewUser);
        type(login, username);
        type(password, pass);
        type(confirmPassword, confirm);
        click(forcePasswordChangeOnFirstLogin);
        click(ok);
    }

    @Step("create user with extended info and reset password checkbox enabled")
    public void createUserExtendedInfoResetPasswordCheckboxOn(String username, String pass, String confirm, String fullNa, String mail, String jabb) {
        click(createNewUser);
        type(login, username);
        type(password, pass);
        type(confirmPassword, confirm);
        type(fullName, fullNa);
        type(email, mail);
        type(jabber, jabb);
        click(forcePasswordChangeOnFirstLogin);
        click(ok);
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
