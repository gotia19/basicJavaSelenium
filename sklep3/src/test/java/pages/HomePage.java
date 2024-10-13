package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private Logger log = LoggerFactory.getLogger(HomePage.class);

//    WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
    @FindBy(css = "#contact-link a")
    WebElement contactUs_link;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public HomePage clickContactUsLink() {
        contactUs_link.click();
        log.info("Contact us link was clicked");
        return this;
    }
}
