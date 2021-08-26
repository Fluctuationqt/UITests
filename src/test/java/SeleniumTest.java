
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;


public class SeleniumTest extends IntegrationTests {

    @Test
    public void testChrome() throws Exception {
        WebDriverWait waitChrome = new WebDriverWait(chrome, 5);
        waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
        takeScreenshot(chrome, "testChrome_1.jpg");
        chrome.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
        takeScreenshot(chrome, "testChrome_2.jpg");
        waitChrome.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
        takeScreenshot(chrome, "testChrome_3.jpg");
        waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.id("host-name")));
        chrome.findElement(By.id("host-name")).sendKeys("Doing integration testing!");
        takeScreenshot(chrome, "testChrome_4.jpg");
    }

    @Test
    public void testFirefox() throws IOException {
        WebDriverWait waitFirefox = new WebDriverWait(firefox, 5);
        waitFirefox.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
        takeScreenshot(firefox, "testFirefox_1.jpg");
        firefox.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
        takeScreenshot(firefox, "testFirefox_2.jpg");
        waitFirefox.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
        takeScreenshot(firefox, "testFirefox_3.jpg");
        waitFirefox.until(ExpectedConditions.presenceOfElementLocated(By.id("host-name")));
        firefox.findElement(By.id("host-name")).sendKeys("Doing integration testing!");
        takeScreenshot(firefox, "testFirefox_4.jpg");
    }

}
