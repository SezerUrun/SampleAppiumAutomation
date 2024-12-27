import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends CommonFunctions {
    static AppiumDriver driver;
    WebElement element_username;
    WebElement element_password;
    WebElement element_login;

    LoginPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    void findElementsForLoginAndInteract(String mailAddress, String password) {
        element_username = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"));
        element_password = driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"));
        element_login = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]"));
        element_username.sendKeys(mailAddress);
        element_password.sendKeys(password);
        element_login.click();
    }

    void loginWithInvalidCredentials(String mailAddress, String password) throws Exception {
        findElementsForLoginAndInteract(mailAddress, password);
        Thread.sleep(1000);
        if (waitForElementToBeVisible("//android.view.ViewGroup[@content-desc=\"test-Error message\"]")) {
            System.out.println("Login Failed(EXPECTED RESULT)");
        } else {
            throw new Exception("Unexpected result while login attempt with invalid credentials");
        }
    }

    void loginWithValidCredentials(String mailAddress, String password) throws Exception {
        findElementsForLoginAndInteract(mailAddress, password);
        Thread.sleep(1000);
        if (waitForElementToBeVisible("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")) {
            System.out.println("Login Succeed(EXPECTED RESULT)");
        } else {
            throw new Exception("Unexpected result while login attempt with valid credentials");
        }
    }

    void loginWithLockedOutUser(String mailAddress, String password) throws Exception {
        findElementsForLoginAndInteract(mailAddress, password);
        Thread.sleep(1000);
        if (waitForElementToBeVisible("//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]")) {
            System.out.println("Login failed for locked out user(EXPECTED RESULT)");
        } else {
            throw new Exception("Unexpected result while login attempt with valid credentials");
        }
    }
}
