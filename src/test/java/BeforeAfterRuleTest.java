import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburgers.ui.data.User;
import ru.stellarburgers.ui.data.UserGenerator;
import ru.stellarburgers.ui.user.UserClient;

import java.time.Duration;

public abstract class BeforeAfterRuleTest {
    User user;
    String accessToken;
    WebDriver driver;
    UserClient userClient;

    @Before
    @Step("Precondition step for tests")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = UserGenerator.getNewRandomUser();
        userClient = new UserClient();
        ValidatableResponse responseCreateUser = userClient.createUser(user);
        accessToken = responseCreateUser.extract().path("accessToken").toString().substring(7);
    }

    @After
    @Step("Delete test user and close browser")
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        driver.quit();
    }

}
