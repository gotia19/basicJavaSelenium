import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormBasicTest {
    private static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private String browserName = "chrome";
    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

    @Test
    void shouldFillFormWithSuccess() throws InterruptedException {
        //kod selenium
        // 1. Open url http://www.automationpractice.pl/index.php
        driver = getDriver(browserName);
        driver.get(APP_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2 navigate to contact us
        WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
        contactUs_link.click();

        //3. Fill subject - droplist
        // WebElement subjectHandling_select = driver.findElement(By.cssSelector("#id_contact"));
        // WebElement subjectHandling_select = driver.findElement(By.xpath("#id_contact")); <- z prawdziwym xpathem ofc
        WebElement subjectHandling_select = driver.findElement(By.id("id_contact"));
        Select select = new Select(subjectHandling_select);
        //select.selectByIndex(1);
        select.selectByVisibleText("Customer service");
        // System.out.println("ok");
        log.info("Subject selected");

        //4. Fill email - text
        WebElement email_input = driver.findElement(By.cssSelector("#email"));
        email_input.clear();
        email_input.sendKeys("jakis.mail@mail.com");
        log.info("email submitted");

        //5. order reference - text
        WebElement id_order_input = driver.findElement(By.cssSelector("#id_order"));
        id_order_input.clear();
        id_order_input.sendKeys("1234");
        log.info("order submitted");

        //6. Attach
        WebElement attach_file_input = driver.findElement(By.cssSelector("#fileUpload"));
        attach_file_input.clear();
        attach_file_input.sendKeys("C:\\Users\\pyziom\\Projekty\\sklep3");
        System.out.println("OK");
        //7. Message
        WebElement message = driver.findElement(By.xpath("//*[@id='message']"));
        message.clear();
        message.sendKeys("I have a problem with my order.");
        //8.Submit button
        WebElement send = driver.findElement(By.id("submitMessage"));
        send.click();
        System.out.println("OK");//Asercja
        String expected_msg = "Hi, Your message has been successfully sent to our team.";
        WebElement success_msg = driver.findElement(By.cssSelector(".alert-success"));
        String actual_msg = success_msg.getText();
        assertThat(expected_msg).contains(actual_msg); //lużna asercja//
        assertThat(expected_msg).isEqualTo(actual_msg); //precyzyjna asercja
        assertThat(expected_msg).withFailMessage("Expected i actual message są różne").isEqualTo(actual_msg);
        // 9. driver quite
        driver.quit();
    }

    private WebDriver getDriver(String browserName) {
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
