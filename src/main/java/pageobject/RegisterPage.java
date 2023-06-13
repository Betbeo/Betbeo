package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageui.RegisterPageUi;

public class RegisterPage extends BasePage {
	private WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputTextToFirstName(String stepDes, String value) {
		attachTextContent(stepDes);
		sendkeyToElement(driver, RegisterPageUi.FIRSTNAME, value);
	}
	
	public void inputTextToLastName(String stepDes, String value) {
		attachTextContent(stepDes);
		sendkeyToElement(driver, RegisterPageUi.LASTNAME, value);
	}
	
	public void inputTextToEmail(String stepDes, String value) {
		attachTextContent(stepDes);
		sendkeyToElement(driver, RegisterPageUi.EMAIL, value);
	}
	
	public void inputTextToPassword(String stepDes, String value) {
		attachTextContent(stepDes);
		sendkeyToElement(driver, RegisterPageUi.PASSWORD, value);
	}
	
	public void clickToResgisterBtn() {
		attachTextContent("Click nút Đăng ký");
		clickToElement(driver, RegisterPageUi.REGISTER_BTN);
	}
	
	public List<String> getMsg() {
		List<String> actualMsgList = new ArrayList<>();
		getListWebElement(driver, RegisterPageUi.MSG_ERR_LIST).forEach(element -> {
			String msg = element.getText();
			if (!msg.trim().isEmpty()) {
				actualMsgList.add(msg);
			}
		});
		return actualMsgList;
	}

}
