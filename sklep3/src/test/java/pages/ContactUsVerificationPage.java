package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsVerificationPage {
    private Logger log = LoggerFactory.getLogger(ContactUsVerificationPage.class);

    @FindBy(css=".alert-success")
    WebElement success_msg;

    public ContactUsVerificationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getMessageText() {
        String actual_msg = success_msg.getText();
        log.info("Alert success is: " + actual_msg);
        return actual_msg;
    }
}
