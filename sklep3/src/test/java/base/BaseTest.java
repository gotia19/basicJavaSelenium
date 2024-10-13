package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private final String browserName = "chrome";
    protected Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setup() {
        driver = getDriver();
        driver.get(APP_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Driver initialized properly");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        log.info("Driver closed");
    }

    private WebDriver getDriver() {
        switch (browserName) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                log.info("Chrome Driver setup");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start--maximized");
//                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--ignore-certificate-errors");
//                chromeOptions.addArguments("--disable-gpu");
//                chromeOptions.addArguments("--disable-browser-side-navigation");
//                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                chromeOptions.addArguments("user-data-dir=C:/Users/pyziom/Documents/User Data");
                chromeOptions.addArguments("--profile-directory=Default");
//                chromeOptions.addArguments("--headless");
                log.info("Chrome Driver arguments");
                driver = new ChromeDriver(chromeOptions);
                log.info("Chrome Driver initialized properly");
                return driver;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                return driver;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
                return driver;
            }
            default:
                throw new UnsupportedOperationException("Unsupported browser selected");
        }
    }
}
