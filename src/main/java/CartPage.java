import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends CommonFunctions {
    static AppiumDriver driver;

    CartPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    void checkIfItemAddedToCart() throws Exception {
        if (waitForElementToBeVisible("//android.widget.TextView[@text=\"YOUR CART\"]")) {
            System.out.println("Cart opened");
        }
        else {
            throw new Exception("Could not open the cart");
        }
        if (waitForElementToBeVisible("//android.view.ViewGroup[@content-desc=\"test-Item\"]")) {
            System.out.println("Item added to the cart");
        }
        else {
            throw new Exception("No item found in cart");
        }
    }
}
