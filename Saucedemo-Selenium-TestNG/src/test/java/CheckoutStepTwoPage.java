package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutStepTwoPage {
    private WebDriver driver;
    private By finishButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFinish() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }
}