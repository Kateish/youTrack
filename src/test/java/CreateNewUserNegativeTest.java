import com.jetbrains.youtrack.pageObjects.CreateUser;
import com.jetbrains.youtrack.pageObjects.Login;
import com.jetbrains.youtrack.pageObjects.Users;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.jetbrains.youtrack.base.Base.loginPageUrl;
import static org.hamcrest.CoreMatchers.is;

public class CreateNewUserNegativeTest {
    public CreateUser page;
    public Users usersPage;
    public Login loginPage;
    public WebDriver driver;
    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\test\\src\\test\\resources\\executables\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(loginPageUrl);
        loginPage.login("root", "root");
        // driver.get(usersPageUrl);
        driver.manage().window().maximize();

    }

    @After
    public void quitBrowser() {

        driver.quit();

    }
    //users
    String[] user11EmptyUserName = {"", "1", "1"};
    String[] user11EmptyUserNamePwResetForce = {"", "1", "1"};
    String[] user11EmptyPwd = {"1", "", "1"};
    String[] user11EmptyConfirmation = {"1", "1", ""};

//    String[] invalidEmail = {"1", "1", "1", "a"};
    String[] onlyFullName = {"", "", "", "fullName"};
    String[] scriptName = {"<script>alert(Hola);</script>", "1", "1"};
    String[] duplicate = {"guest", "1", "1"};
    String alertMismatch = "Password doesn't match!";
    String nologin = "login is required!";

    @Test
    public void invalidParamsTestRunner() {
        createUserInvalidParams(user11EmptyUserName[0], user11EmptyUserName[1], user11EmptyUserName[2], nologin);
        createUserInvalidParams(user11EmptyPwd[0], user11EmptyPwd[1], user11EmptyPwd[2], alertMismatch);
        createUserInvalidParams(user11EmptyConfirmation[0], user11EmptyConfirmation[1], user11EmptyConfirmation[2], alertMismatch);
//        createUserInvalidParams(invalidEmail[0], invalidEmail[1], invalidEmail[2], nologin); //Нет такой валидации
    }

    public void createUserInvalidParams(String name, String pw, String conf, String alert) {
        page.createUser(name, pw, conf);
        Assert.assertThat(usersPage.closeAlertAndGetItsText(), is(alert));

    }

}
