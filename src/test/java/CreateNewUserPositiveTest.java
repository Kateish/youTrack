
import com.jetbrains.youtrack.pageObjects.CreateUser;
import com.jetbrains.youtrack.pageObjects.Login;
import com.jetbrains.youtrack.pageObjects.Users;



import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import static com.jetbrains.youtrack.base.Base.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateNewUserPositiveTest {
    public CreateUser page;
    public Users usersPage;
    public Login loginPage;
    public WebDriver driver;

    @FindBy(css = ".login-button")
    private static WebElement submitLoginButton;

    @FindBy(css = "#username")
    private static WebElement usernameInputField;

    @FindBy(css = "#password")
    private static WebElement passwordInputField;
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

    //test data to create users
    String[] user1RequiredFields = {"user1", "1", "1"};
    String[] user2RequiredFields = {"user2", "1234444", "1234444"};
    String[] user3ToBeDeleted = {"user3", "USER123", "USER123"};
    String[] user4OptionalFields = {"user4", "1", "1", "a@b.c", "a.jabber.org"};
    String[] user5toBeMerged = {"user5", "1", "1", "a@b.c", "a.jabber.org"};
    String[] user11ForcedPwChange = {"user11", "1", "1", "new", "new"};
    String user1000chars = "m00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    String[] userNonAlfaNum = {"&%-", "1", "1"};

    @Test
    public void createNewUserCase1() throws Exception {

        page.createUser(user1RequiredFields[0], user1RequiredFields[1], user1RequiredFields[2]);
        usersPage.searchByString(user1RequiredFields[0]);
        Assert.assertThat(usersPage.users.size(), is(3)); //1 new user only + guest + admin
    }

    @Test
    public void createNewUserCase2() {

        page.createUser(user2RequiredFields[0], user2RequiredFields[1], user2RequiredFields[2]);
        assertEquals("2", driver.findElement(By.id("id_l.U.usersList.UserLogin.editUser_26_98")).getText());
        assertEquals("banned", driver.findElement(By.xpath("//div[@id='id_l.U.usersList.usersList']/table/tbody/tr[2]/td/span[2]")).getText());
    }

    @Test
    public void createNewUserDeleteUserCase3() {
        page.createUser(user3ToBeDeleted[0], user3ToBeDeleted[1], user3ToBeDeleted[2]);
        usersPage.delete();
        assertTrue(usersPage.closeAlertAndGetItsText().matches("^Are you sure you want to delete user account for a[\\s\\S] This action cannot be undone\\.$"));
        usersPage.searchByString(user3ToBeDeleted[0]);
        assertEquals("No users found.", driver.findElement(By.xpath("//div[@id='id_l.U.usersList.usersList']/p")).getText());
    }

    @Test
    public void createUser4() {
        page.createUserExtendedInfo(user4OptionalFields[0], user4OptionalFields[1], user4OptionalFields[2], user4OptionalFields[3], user4OptionalFields[4], user4OptionalFields[5]);
        String email = driver.findElement(By.xpath("//div[@id='id_l.U.usersList.usersList']/table/tbody/tr[6]/td[3]/div[2]")).getText();
        String jabber = driver.findElement(By.xpath("//div[@id='id_l.U.usersList.usersList']/table/tbody/tr[6]/td[3]")).getText();
        assertEquals(email, jabber);
        assertEquals("j", email);
    }

    @Test
    public void createNewUserCase5Merge() {
        page.createUserExtendedInfo(user5toBeMerged[0], user5toBeMerged[1], user5toBeMerged[2], user5toBeMerged[3], user5toBeMerged[4], user5toBeMerged[5]);
        usersPage.mergeUsers();
        assertTrue(usersPage.closeAlertAndGetItsText().matches("^'4 \\(4\\)' will be merged with '5 \\(5\\)' and removed\\. Are you sure[\\s\\S]$"));

    }

    @Test
    public void createNewUserCase11ForcedPwChange() {
        page.createUserResetPasswordCheckboxOn(user11ForcedPwChange[0], user11ForcedPwChange[1], user11ForcedPwChange[2]);
        usersPage.logOut();
        loginPage.login(user11ForcedPwChange[0], user11ForcedPwChange[1]);
        usersPage.resetPw(user11ForcedPwChange[1], user11ForcedPwChange[3], user11ForcedPwChange[4]);
        usersPage.logOut();
        loginPage.login(user11ForcedPwChange[0], user11ForcedPwChange[3]);
        Assert.assertTrue(!usersPage.isAlertPresent());

    }

    @Test
    public void createNewUser3InsteadOfDeleted() {
        page.createUser(user3ToBeDeleted[0], user3ToBeDeleted[1], user3ToBeDeleted[2]);
        assertEquals("3", driver.findElement(By.id("id_l.U.usersList.UserLogin.editUser_26_110")).getText());
    }

    @Test
    public void createNewUserUserName1000chars() {
        page.createUser(user1000chars, "1", "1");
        Assert.assertTrue(!usersPage.isAlertPresent());
    }

    @Test
    public void createNewUserNonAlfaNum() throws Exception {

        page.createUser(userNonAlfaNum[0],userNonAlfaNum[1], userNonAlfaNum[2]);
        usersPage.searchByString(userNonAlfaNum[0]);
        Assert.assertThat(usersPage.users.size(), is(3)); //1 new user only + guest + admin
    }

}
