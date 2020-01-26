package com.jetbrains.youtrack.pageObjects;


import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Login{
    public static WebDriver driver;

    public static String usersPageUrl = "http://localhost:8080/users";
    public static String loginPageUrl = "http://localhost:8080/login";
    public static String signUpPageUrl = "http://localhost:8080/registerUserForm";

    //locators

    private WebElement submitLoginButton = driver.findElement(By.cssSelector(".login-button"));

    private WebElement usernameInputField = driver.findElement(By.cssSelector("#username"));

    private WebElement passwordInputField = driver.findElement(By.cssSelector("#password"));

    private WebElement loginErrorMessage = driver.findElement(By.xpath(".//div[@class=\"header__text\"]/span[@ng-show=\"message\"]"));

    public static void setUp(String url) {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/executables/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    //actions

    public Login login(String name, String pw){
        setUp(loginPageUrl);
        usernameInputField.sendKeys(name);
        passwordInputField.sendKeys(pw);
        submitLoginButton.click();
        return this;
    }

    @AfterEach
    public static void quitBrowser() {

        driver.quit();

    }
}
