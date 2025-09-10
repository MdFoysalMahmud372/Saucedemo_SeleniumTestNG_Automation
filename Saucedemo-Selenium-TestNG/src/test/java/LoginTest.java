package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import static org.testng.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login should navigate to inventory page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
    }
}