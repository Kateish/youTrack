package com.jetbrains.youtrack.pageObjects;


import com.jetbrains.youtrack.base.Base;
import com.jetbrains.youtrack.utils.Constant;
import com.jetbrains.youtrack.utils.ExcelUtils;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;


public class Login extends Base {
    //locators
    @FindBy(css = ".login-button")
    private static WebElement submitLoginButton;

    @FindBy(css = "#username")
    private static WebElement usernameInputField;

    @FindBy(css = "#password")
    private static WebElement passwordInputField;

      @FindBy(xpath = ".//div[@class=\"header__text\"]/span[@ng-show=\"message\"]")
    private static WebElement loginErrorMessage;


    public Login(WebDriver driver) {
        super(driver);
    }

    //action
    @Step("Fill login form, submit: Excel version")
    public static void loginEx(int iTestCaseRow) throws Exception {

        // Clicking on the login link on the Home Page
        driver.get(loginPageUrl);
        // Storing the UserName in to a String variable and Getting the UserName from Test Data Excel sheet
        // iTestcaseRow is the row number of our Testcase name in the Test Data sheet
        // Constant.Col_UserName is the column number for UserName column in the Test Data sheet
        // Please see the Constant class in the Utility Package

        String sUserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_UserName);
        type(usernameInputField, sUserName);

        String sPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Password);
        type(passwordInputField, sPassword);

        submitLoginButton.click();
    }

    @Step("log in")
    public static void login(String name, String pw){
       // driver.get(loginPageUrl);
        type(usernameInputField, name);
        type(passwordInputField, pw);
        click(submitLoginButton);
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
