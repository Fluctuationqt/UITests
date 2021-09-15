import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTestTwo extends IntegrationTests {

//    @Test
//    public void testChromeTwo() throws Exception {
//        chrome.navigate().to(baseURL);
//        WebDriverWait waitChrome = new WebDriverWait(chrome, 5);
//        waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
//        takeScreenshot(chrome, "testChromeTwo_1.jpg");
//        chrome.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
//        takeScreenshot(chrome, "testChromeTwo_2.jpg");
//        waitChrome.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
//        takeScreenshot(chrome, "testChromeTwo_3.jpg");
//        waitChrome.until(ExpectedConditions.presenceOfElementLocated(By.id("subaccount")));
//        chrome.findElement(By.id("subaccount")).sendKeys("Doing integration testing!");
//        takeScreenshot(chrome, "testChromeTwo_4.jpg");
//    }

//    @Test
//    public void testFirefoxTwo() throws IOException {
//        firefox.navigate().to(baseURL);
//        WebDriverWait waitFirefox = new WebDriverWait(firefox, 5);
//        waitFirefox.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title=\"XS Migration\"]")));
//        takeScreenshot(firefox, "testFirefoxTwo_1.jpg");
//        firefox.findElement(By.xpath("//*[@title=\"XS Migration\"]")).click();
//        takeScreenshot(firefox, "testFirefoxTwo_2.jpg");
//        waitFirefox.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@src='../ide-migration/migration-launch.html']")));
//        takeScreenshot(firefox, "testFirefoxTwo_3.jpg");
//        waitFirefox.until(ExpectedConditions.presenceOfElementLocated(By.id("subaccount")));
//        firefox.findElement(By.id("subaccount")).sendKeys("Doing integration testing!");
//        takeScreenshot(firefox, "testFirefoxTwo_4.jpg");
//    }

}
