package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsPage {
    private Logger log = LoggerFactory.getLogger(ContactUsPage.class);

    @FindBy(id = "id_contact")
    WebElement subjectHandling_select;

    @FindBy(css = "#email")
    WebElement email_input;

    @FindBy(css = "#id_order")
    WebElement id_order_input;

    @FindBy(css = "#fileUpload")
    WebElement attach_file_input;

    @FindBy(xpath = "//*[@id='message']")
    WebElement messageInput;

    @FindBy(id = "submitMessage")
    WebElement send;

    public ContactUsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage selectObjectFromSubjectHeading(String subject) {
        Select select = new Select(subjectHandling_select);
        //select.selectByIndex(1);
        select.selectByVisibleText(subject);
        log.info("Subject selected");
        return this;
    }

    public ContactUsPage fillEmail(String email) {
        email_input.clear();
        email_input.sendKeys(email);
        log.info("email submitted" + email);
        return this;
    }

    public ContactUsPage fillOrderReference(String order) {
        id_order_input.clear();
        id_order_input.sendKeys(order);
        log.info("order submitted");
        return this;
    }

    public ContactUsPage uploadFile(String filePath) {
        attach_file_input.clear();
        attach_file_input.sendKeys(filePath);
        System.out.println("OK");
        return this;
    }

    public ContactUsPage fillMessage(String messageText) {
        messageInput.clear();
        messageInput.sendKeys(messageText);
        return this;
    }

    public ContactUsPage clickSendButton() {
        send.click();
        System.out.println("OK");
        return this;
    }
}
