
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest extends IntegrationTests {

    @Test
    public void testChrome() throws Exception {
        System.out.println("TEST_CREDENTIALS Value:- " + System.getenv("TEST_CREDENTIALS"));
        chrome.navigate().to(baseURL);
        WebDriverWait waitChrome = new WebDriverWait(chrome, 5);
        waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
        takeScreenshot(chrome, "testChrome_1.jpg");
        chrome.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
        takeScreenshot(chrome, "testChrome_2.jpg");
        waitChrome.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
        takeScreenshot(chrome, "testChrome_3.jpg");
        waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.id("subaccount")));
        chrome.findElement(By.id("subaccount")).sendKeys("Doing integration testing!");
        takeScreenshot(chrome, "testChrome_4.jpg");
    }

    @Test
    public void testFirefox() throws IOException {
        firefox.navigate().to(baseURL);
        WebDriverWait waitFirefox = new WebDriverWait(firefox, 5);
        waitFirefox.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
        takeScreenshot(firefox, "testFirefox_1.jpg");
        firefox.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
        takeScreenshot(firefox, "testFirefox_2.jpg");
        waitFirefox.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
        takeScreenshot(firefox, "testFirefox_3.jpg");
        waitFirefox.until(ExpectedConditions.presenceOfElementLocated(By.id("subaccount")));
        firefox.findElement(By.id("subaccount")).sendKeys("Doing integration testing!");
        takeScreenshot(firefox, "testFirefox_4.jpg");
    }

}
