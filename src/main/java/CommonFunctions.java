import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

public class CommonFunctions {
    static AppiumDriver driver;

    CommonFunctions(AppiumDriver driver) {
        this.driver = driver;
    };

    void logout() throws Exception {
        WebElement element_menu = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView"));
        element_menu.click();
        Thread.sleep(1000);
        WebElement element_logout = driver.findElement(AppiumBy.xpath(" //android.widget.TextView[@text=\"LOGOUT\"]"));
        element_logout.click();
        WebElement element_login = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement result= wait.until(ExpectedConditions.visibilityOf(element_login));
        if (result==null){
            throw new Exception("Logout failed");
        }
        else{
            System.out.println("Logout successful");
        }
    }

    boolean waitForElementToBeVisible(String xPath) {
        WebElement element = driver.findElement(By.xpath(xPath));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result= wait.until(ExpectedConditions.visibilityOf(element));
        if (result==null){
            return false;
        }
        else{
            return true;
        }
    }
}
