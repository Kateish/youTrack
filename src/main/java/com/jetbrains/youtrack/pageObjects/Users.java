package com.jetbrains.youtrack.pageObjects;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;


import java.util.List;


public class Users {
    public static WebDriver driver;

    public static String usersPageUrl = "http://localhost:8080/users";
    public static String loginPageUrl = "http://localhost:8080/login";
    public static String signUpPageUrl = "http://localhost:8080/registerUserForm";

    public static void setUp(String url) {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/executables/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }
    //locators tor search area

    public WebElement find = driver.findElement(By.id("id_l.U.queryText"));

    public WebElement showOnlineUsersOnly = driver.findElement(By.id("id_l.U.onlineOnly"));

    public WebElement group = driver.findElement(By.name("l.U.groupFilter"));

    public WebElement newUsersGroup = driver.findElement(By.xpath("//div[2]/ul/li[2]"));

    public WebElement role = driver.findElement(By.name("l.U.roleFilter"));

    public WebElement project = driver.findElement(By.name("l.U.projectFilter"));

    public WebElement permission = driver.findElement(By.name("l.U.permissionFilter"));

    public WebElement search = driver.findElement(By.id("id_l.U.searchButton"));

    private WebElement reset = driver.findElement(By.id("id_l.U.resetButton"));

    private WebElement createUser = driver.findElement(By.id("id_l.U.createNewUser"));

    private WebElement userList = driver.findElement(By.id("id_l.U.usersList.usersList"));

    public List<WebElement> users = driver.findElements(By.cssSelector("a[href*=\"id_l.U.usersList.UserLogin.editUser\"]"));

    //locators to merge users

    private WebElement mergeUsers = driver.findElement(By.id("id_l.U.usersList.mergeUser_26_79"));

    private WebElement usersSelectionDropDown = driver.findElement(By.id("id_l.U.usersList.SelectUserDialog.selectUserCombo"));

    private WebElement selectUserFromDropDown = driver.findElement(By.xpath("//div[@id='id_l.U.usersList.SelectUserDialog.selectUserDlg']/div[5]/ul/li[5]"));

    private WebElement MergeUsersOkButton = driver.findElement(By.id("id_l.U.usersList.SelectUserDialog.selectUserOk"));

    //locators to delete users

    private WebElement deleteUser = driver.findElement(By.id("id_l.U.usersList.deleteUser_26_99_3"));

    //locators to ban user
    private WebElement banUser = driver.findElement(By.cssSelector("id_l.U.usersList.banUser_26_97"));

    //to unban
    private WebElement unbanUser = driver.findElement(By.cssSelector("id_l.U.usersList.banUser_26_98"));


    private WebElement secondUserToBeBanned = driver.findElement(By.id("id_l.U.usersList.banUser_26_98"));

    //to log out
    private WebElement logOut = driver.findElement(By.linkText("logout"));

    //to reset password
    private WebElement oldPwInputField = driver.findElement(By.id("id_l.U.ChangePasswordDialog.oldPassword"));

    private WebElement newPwInputField = driver.findElement(By.id("id_l.U.ChangePasswordDialog.newPassword1"));

    private WebElement confirmNewPwInputField = driver.findElement(By.id("id_l.U.ChangePasswordDialog.newPassword2"));


    private WebElement buttonOk = driver.findElement(By.id("id_l.U.ChangePasswordDialog.passOk"));

    //to verify all the fields are correct
    //to verify the actions are ok
    //to verify the userlist
//actions

    public void selectOnline() {
        driver.get(usersPageUrl);
        showOnlineUsersOnly.click();
        search.click();
    }


    public void searchByString(String searchValue) {
        driver.get(usersPageUrl);
        find.sendKeys(searchValue);
        search.click();
    }

    public void searchByGroupNewUsers() {
        driver.get(usersPageUrl);
        group.click();
        newUsersGroup.click();
        search.click();
    }
    //assert if all are clickable > all are present


    public void verifyAllFilters(WebElement element) {
        driver.get(usersPageUrl);
        element.click();
    }

    //actions on a userlist

    //actions to merge users

    public void mergeUsers() {
        driver.get(usersPageUrl);
        mergeUsers.click();
        usersSelectionDropDown.click();
        selectUserFromDropDown.click();
        MergeUsersOkButton.click();
    }

    //action to ban user

    public void ban() {
        driver.get(usersPageUrl);
        banUser.click();
    }

    //action to unban user

    public void unban() {
        driver.get(usersPageUrl);
        unbanUser.click();
    }

    //action to delete

    public void delete() {
        driver.get(usersPageUrl);
        deleteUser.click();
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

    public void logOut() {
        driver.get(usersPageUrl);
        logOut.click();
    }

    //change password when forced

    public void resetPw(String old, String newPw, String conf) {
        driver.get(usersPageUrl);
        oldPwInputField.sendKeys(old);
        newPwInputField.sendKeys(newPw);
        confirmNewPwInputField.sendKeys(conf);
        buttonOk.click();
    }

    @AfterEach
    public static void quitBrowser() {

        driver.quit();

    }
}
