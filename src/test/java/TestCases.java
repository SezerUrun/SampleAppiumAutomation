import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {
    /*  These variables should be exported before running tests
        echo export ANDROID_HOME=~/Android/Sdk >> ~/.bashrc
        echo export ANDROID_SDK_ROOT=~/Android/Sdk/ >> ~/.bashrc
        echo export JAVA_HOME=/usr/lib/jvm/default-java/ >> ~/.bashrc
        echo export PATH=$PATH:$JAVA_HOME/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/ >> ~/.bashrc
        source ~/.bashrc
    */
    private static AndroidDriver driver = null;
    private static AppiumDriverLocalService service = null;
    private static AppiumServiceBuilder builder = new AppiumServiceBuilder();
    LoginPage loginPage = null;
    MainPage mainPage = null;
    CartPage cartPage = null;
    String validUserName = "standard_user";
    String invalidUserName = "invalid_user";
    String lockedOutUserName = "locked_out_user";
    String password = "secret_sauce";

    @BeforeAll
    static void setUp() throws MalformedURLException {
        //Start server
        try {
            builder.usingAnyFreePort();
            service = AppiumDriverLocalService.buildDefaultService();
            service.stop();
            service.start();
        }
        catch (Exception e) {
                e.printStackTrace();
                service.stop();
        }
        //Connection to driver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }
    @BeforeEach
    void restartApp() throws InterruptedException {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
        Thread.sleep(4000);
    }

    @Test
    @Order(1)
    void loginWithInvalidCredentials() {
        loginPage = new LoginPage(driver);
        try {
            loginPage.loginWithInvalidCredentials(invalidUserName,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(2)
    void loginWithValidCredentials() {
        loginPage = new LoginPage(driver);
        try {
            loginPage.loginWithValidCredentials(validUserName,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    void loginWithLockedOutUserCredentials() {
        loginPage = new LoginPage(driver);
        try {
            loginPage.loginWithLockedOutUser(lockedOutUserName,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(4)
    void logout() throws Exception {
        loginWithValidCredentials();
        mainPage = new MainPage(driver);
        mainPage.logout();
    }

    @Test
    @Order(5)
    void addToCartAndCheckout() throws Exception {
        loginWithValidCredentials();
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        mainPage.addAnItemToCart();
        cartPage.checkIfItemAddedToCart();
    }

    @AfterAll
    static void tearDown() {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.quit();
    }

}
