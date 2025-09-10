import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testProductSelection() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        System.out.println("Cart count: " + cartCount);
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }
}