package com.jetbrains.youtrack.pageObjects;

import com.jetbrains.youtrack.base.Base;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;


public class Users extends Base {

    //locators tor search area

    @FindBy(id = "id_l.U.queryText")
    public WebElement find;

    @FindBy(id = "id_l.U.onlineOnly")
    public WebElement showOnlineUsersOnly;

    @FindBy(name = "l.U.groupFilter")
    public WebElement group;

    @FindBy(xpath = "//div[2]/ul/li[2]")
    public WebElement newUsersGroup;

    @FindBy(name = "l.U.roleFilter")
    public WebElement role;

    @FindBy(name = "l.U.projectFilter")
    public WebElement project;

    @FindBy(name = "l.U.permissionFilter")
    public WebElement permission;

    @FindBy(id = "id_l.U.searchButton")
    public WebElement search;

    @FindBy(id = "id_l.U.resetButton")
    private WebElement reset;

    @FindBy(id = "id_l.U.createNewUser")
    private WebElement createUser;

    @FindBy(id = "id_l.U.usersList.usersList")
    private WebElement userList;

    public List<WebElement> users = driver.findElements(By.cssSelector("a[href*=\"id_l.U.usersList.UserLogin.editUser\"]"));

    //locators to merge users

    @FindBy(id = "id_l.U.usersList.mergeUser_26_79")
    private WebElement mergeUsers;

    @FindBy(id = "id_l.U.usersList.SelectUserDialog.selectUserCombo")
    private WebElement usersSelectionDropDown;

    @FindBy(xpath = "//div[@id='id_l.U.usersList.SelectUserDialog.selectUserDlg']/div[5]/ul/li[5]")
    private WebElement selectUserFromDropDown;

    @FindBy(id = "id_l.U.usersList.SelectUserDialog.selectUserOk")
    private WebElement MergeUsersOkButton;

    //locators to delete users

    @FindBy(id = "id_l.U.usersList.deleteUser_26_99_3")
    private  WebElement deleteUser;

    //locators to ban user
    @FindBy(css = "id_l.U.usersList.banUser_26_97")
    private WebElement banUser;

    //to unban
    @FindBy(css = "id_l.U.usersList.banUser_26_98")
    private WebElement unbanUser;


    @FindBy(id = "id_l.U.usersList.banUser_26_98")
    private WebElement secondUserToBeBanned;

    //to log out
    @FindBy(linkText = "logout")
    private WebElement logOut;

    public Users(WebDriver driver) {
        super(driver);
    }
//to reset password
@FindBy(id = "id_l.U.ChangePasswordDialog.oldPassword")
private WebElement oldPwInputField;

    @FindBy(id = "id_l.U.ChangePasswordDialog.newPassword1")
    private WebElement newPwInputField;

    @FindBy(id = "id_l.U.ChangePasswordDialog.newPassword2")
    private WebElement confirmNewPwInputField;

    @FindBy(id = "id_l.U.ChangePasswordDialog.passOk")
    private WebElement buttonOk;

    //to verify all the fields are correct
    //to verify the actions are ok
    //to verify the userlist
//actions
    @Step("search for online users")
    public void selectOnline() {
        driver.get(usersPageUrl);
        click(showOnlineUsersOnly);
        click(search);
    }

    @Step("search by mask")
    public void searchByString(String searchValue) {
        driver.get(usersPageUrl);
        type(find, searchValue);
        click(search);
    }

    @Step("search by group")
    public void searchByGroupNewUsers() {
        driver.get(usersPageUrl);
        click(group);
        click(newUsersGroup);
        click(search);
    }
    //assert if all are clickable > all are present

    @Step("verify all search filters")
    public void verifyAllFilters(WebElement element) {
        driver.get(usersPageUrl);
        click(element);
    }

    //actions on a userlist

    //actions to merge users
    @Step("merge users")
    public void mergeUsers() {
        driver.get(usersPageUrl);
        click(mergeUsers);
        click(usersSelectionDropDown);
        click(selectUserFromDropDown);
        click(MergeUsersOkButton);
    }

    //action to ban user
    @Step("ban user")
    public void ban() {
        driver.get(usersPageUrl);
        click(banUser);
    }

    //action to unban user
    @Step("unban user")
    public void unban() {
        driver.get(usersPageUrl);
        click(unbanUser);
    }

    //action to delete
    @Step("delete user")
    public void delete() {
        driver.get(usersPageUrl);
        click(deleteUser);
    }
//handling alerts
private boolean acceptNextAlert = true;
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



    public String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
    //action to logout
    @Step("log out")
    public void logOut(){
        driver.get(usersPageUrl);
        click(logOut);
    }

    //change password when forced
    @Step("log out")
    public void resetPw(String old, String newPw, String conf){
        driver.get(usersPageUrl);
        type(oldPwInputField, old);
        type(newPwInputField, newPw);
        type(confirmNewPwInputField, conf);
        click(buttonOk);
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
