package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class EndToEndTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setup() {
        ChromeOptions ops =new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @Test
    public void testEndToEndCheckoutFlow() {
        // Step 1: Login
        System.out.println("Starting login...");
        loginPage.login("standard_user", "secret_sauce");
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed");

        // Step 2: Add product to cart
        System.out.println("Adding product to cart...");
        inventoryPage.addProductToCart();
        inventoryPage.goToCart();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/cart.html"));
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html", "Failed to navigate to cart");

        // Step 3: Click checkout
        System.out.println("Clicking checkout...");
        cartPage.clickCheckout();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-one.html"));
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", "Failed to navigate to checkout step one");

        // Step 4: Enter checkout information and continue
        System.out.println("Entering checkout information...");
        checkoutStepOnePage.enterCheckoutInfo("John", "Doe", "12345");
        checkoutStepOnePage.clickContinue();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-step-two.html"));
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html", "Failed to navigate to checkout step two");

        // Step 5: Click finish
        System.out.println("Clicking finish...");
        checkoutStepTwoPage.clickFinish();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/checkout-complete.html"));
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html", "Failed to navigate to checkout complete");

        // Step 6: Click back home
        System.out.println("Clicking back home...");
        checkoutCompletePage.clickBackHome();
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Failed to navigate back to inventory");
    }

//    @WebDriverWait
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }
}