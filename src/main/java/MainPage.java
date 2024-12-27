import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends CommonFunctions {
    static AppiumDriver driver;

    MainPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    void addAnItemToCart() {
        WebElement element_add_button = driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]"));
        element_add_button.click();
        WebElement element_cart_button = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView"));
        element_cart_button.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
