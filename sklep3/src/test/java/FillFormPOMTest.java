import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ContactUsPage;
import pages.ContactUsVerificationPage;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormPOMTest extends BaseTest {

    private String filePath = "C:\\Users\\pyziom\\Projekty\\sklep3";
    private String subjectHeading = "Customer service";
    private String email = "jan@wp.pl";
    private String orderReference = "5243";
    private String message = "Lorem ipsum...";
    private String expected_msg = "Your message has been successfully sent to our team.";

    @Test
    public void shouldSendFromWithPOM_test() {
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        ContactUsVerificationPage contactUsVerificationPage = new ContactUsVerificationPage(driver); //dodać Page factory żeby nie było NullPointerException
        homePage.clickContactUsLink();
        contactUsPage.selectObjectFromSubjectHeading(subjectHeading);
        contactUsPage.fillEmail(email);
        contactUsPage.fillOrderReference(orderReference);
        contactUsPage.uploadFile(filePath);
        contactUsPage.fillMessage(message);
        contactUsPage.clickSendButton();
//        contactUsPage.selectObjectFromSubjectHeading("Customer service").fillEmail("jan@wp.pl").fillOrderReference("5243").uploadFile(filePath).fillMessage(message).clickSendButton();
        String messageText = contactUsVerificationPage.getMessageText(); //.var tab
        assertThat(expected_msg).withFailMessage("Expected i actual message są różne").isEqualTo(messageText);
    }
}
