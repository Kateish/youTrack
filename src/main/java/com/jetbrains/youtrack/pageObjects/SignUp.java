package com.jetbrains.youtrack.pageObjects;

import com.jetbrains.youtrack.base.Base;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;


public class SignUp{
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
    //locators

    private WebElement fullName = driver.findElement(By.id("id_l.R.user_fullName"));

    private WebElement email = driver.findElement(By.id("id_l.R.user_email"));

    private WebElement pwd = driver.findElement(By.id("id_l.R.password"));

    private WebElement pwdConfirm = driver.findElement(By.id("id_l.R.confirmPassword"));

    private WebElement registerButton = driver.findElement(By.id("id_l.R.confirmPasswordid_l.R.register"));

    //actions

    public void registerNewUser(String name, String mail, String pass) {
        setUp(signUpPageUrl);
        fullName.sendKeys(name);
        email.sendKeys(mail);
        pwd.sendKeys(pass);
        pwdConfirm.sendKeys(pass);
       registerButton. click();
    }

    @AfterEach
    public static void quitBrowser() {

        driver.quit();

    }
}
