import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Category(IntegrationTests.class)
public class IntegrationTests {

    protected WebDriver chrome;
    protected WebDriver firefox;
    protected String baseURL = "http://dirigible:dirigible@127.0.0.1:8080/services/v4/web/ide/";

    protected void setupChrome(String url) {
        WebDriverManager.chromedriver().browserInDocker().enableRecording();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--incognito");
        chrome = new ChromeDriver(options);
        chrome.navigate().to(url);
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
    }

    protected void setupFirefox(String url) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        // TODO: check for the same params in firefox.
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--window-size=1920,1080");
        //options.addArguments("--incognito");
        firefox = new FirefoxDriver(options);
        firefox.navigate().to(url);
        firefox.manage().window().maximize();
        firefox.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
    }

    @Before
    public void setupDrivers() {
        setupChrome(baseURL);
        setupFirefox(baseURL);
    }

    @After
    public void cleanupDrivers() {
        if(chrome != null) chrome.quit();
        if(firefox != null) firefox.quit();
    }

    static void takeScreenshot(WebDriver browser, String fileWithPath) throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)browser);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        System.out.println("[Screenshot saved]: " + DestFile.getAbsolutePath());
        FileUtils.copyFile(SrcFile, DestFile);
    }

    static void printPageHtml(WebDriver browser) {
        var root = browser.findElement(By.xpath("//*"));
        var page = root.getAttribute("outerHTML");
        System.out.print(page);
    }

}
