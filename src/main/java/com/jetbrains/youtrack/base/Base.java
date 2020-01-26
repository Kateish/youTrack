package com.jetbrains.youtrack.base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
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

//    public static void click(WebElement element) {
//        element.click();
//        test.log(LogStatus.INFO, "Clicking on : " + element);
//    }
//
//    public static void type(WebElement element, String value) {
//        element.clear();
//        element.sendKeys(value);
//        test.log(LogStatus.INFO, "Typing in : " + element + " the value as " + value);
//
//    }

    public static void quitBrowser() {

        driver.quit();

    }

}
