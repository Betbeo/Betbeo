package pageobject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageobject.model.Contact;
import pageui.ContactPageUi;

public class ContactPage extends BasePage {
	private WebDriver driver;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputData(Contact contact) {
		attachTextContent("Nhập thông tin");
		sendkeyToElement(driver, ContactPageUi.NAME_INPUT, contact.name);
		sendkeyToElement(driver, ContactPageUi.EMAIL_INPUT, contact.email);
		sendkeyToElement(driver, ContactPageUi.COMMENT_INPUT, contact.comment);
	}
	
	public void clickSendBtn() {
		attachTextContent("Click Gửi");
		scrollToElement(driver, ContactPageUi.SEND_BTN);
		clickToElement(driver, ContactPageUi.SEND_BTN);
	}
	
	

}
