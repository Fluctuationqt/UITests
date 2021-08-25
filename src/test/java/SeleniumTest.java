import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Category(IntegrationTests.class)
public class SeleniumTest {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.navigate().to("http://dirigible:dirigible@127.0.0.1:8080/services/v4/web/ide/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
    }

    @Test
    public void test() throws Exception {
        this.takeSnapShot(driver, "test.jpg");
    }

    @After
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception {
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        System.out.println("Screenshot dir:" + DestFile.getAbsolutePath());
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
