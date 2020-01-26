package com.jetbrains.youtrack.pageObjects;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class CreateUser {
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

    //locators to create a user

    private WebElement createNewUser = driver.findElement(By.id("id_l.U.createNewUser"));

    private WebElement login = driver.findElement(By.id("id_l.U.cr.login"));

    private WebElement password = driver.findElement(By.id("id_l.U.cr.password"));

    private WebElement confirmPassword = driver.findElement(By.id("id_l.U.cr.confirmPassword"));

    private WebElement forcePasswordChangeOnFirstLogin = driver.findElement(By.id("id_l.U.cr.forcePasswordChange"));

    private WebElement fullName = driver.findElement(By.id("id_l.U.cr.fullName"));

    private WebElement email = driver.findElement(By.id("id_l.U.cr.email"));


    private WebElement jabber = driver.findElement(By.id("id_l.U.cr.jabber"));


    private WebElement ok = driver.findElement(By.id("id_l.U.cr.createUserOk"));


    private WebElement cancel = driver.findElement(By.id("id_l.U.cr.createUserCancel"));


    private WebElement alert = driver.findElement(By.xpath("//div[@class=\"error-bulb2\"]"));

    private WebElement errorForGuestDuplicate = driver.findElement(By.xpath("//*[contains(text(), 'Removing null is prohibited')]"));


    public boolean findErrorMessage() {
        try {
            driver.findElement((By) alert);
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public CreateUser createUser(String username, String pass, String confirm) {
        setUp(usersPageUrl);
        createNewUser.click();
        login.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirm);
        ok.click();
        return this;

    }

    public CreateUser cancelCreatingUser(String username, String pass, String confirm) {
        setUp(usersPageUrl);
        createNewUser.click();
        login.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirm);
        cancel.click();
        return this;

    }

    public CreateUser createUserExtendedInfo(String username, String pass, String confirm, String fullNa, String mail, String jabb) {
        setUp(usersPageUrl);
        createNewUser.click();
        login.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirm);
        fullName.sendKeys(fullNa);
        email.sendKeys(mail);
        jabber.sendKeys(jabb);
        ok.click();
        return this;
    }


    public CreateUser createUserResetPasswordCheckboxOn(String username, String pass, String confirm) {
        driver.get(usersPageUrl);
        createNewUser.click();
        login.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirm);
        forcePasswordChangeOnFirstLogin.click();
        ok.click();
        return this;
    }


    public CreateUser createUserExtendedInfoResetPasswordCheckboxOn(String username, String pass, String confirm, String fullNa, String mail, String jabb) {
        driver.get(usersPageUrl);
        createNewUser.click();
        login.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(confirm);
        fullName.sendKeys(fullNa);
        email.sendKeys(mail);
        jabber.sendKeys(jabb);
        forcePasswordChangeOnFirstLogin.click();
        ok.click();
        return this;
    }

@AfterEach
    public static void quitBrowser() {

        driver.quit();

    }
}
