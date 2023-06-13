package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageui.LoginPageUi;

public class LoginPage extends BasePage{

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputTextToEmail(String stepDes, String value) {
		attachTextContent(stepDes);
		sendkeyToElement(driver, LoginPageUi.EMAIL, value);
	}
	
	public void inputTextToPassword(String stepDes, String value) {
		attachTextContent(stepDes);
		sendkeyToElement(driver, LoginPageUi.PASSWORD, value);
	}
	
	public void clickToLoginBtn() {
		attachTextContent("Click nút Đăng nhập");
		clickToElement(driver, LoginPageUi.LOGIN_BTN);
	}
	
	public void doubleClickToLogoutButton() {
		attachTextContent("Click nút Đăng xuất");
		doubleClickToElement(driver, LoginPageUi.LOGOUT_BUTTON);
	}
	
	public List<String> getMsg() {
		List<String> actualMsgList = new ArrayList<>();
		getListWebElement(driver, LoginPageUi.MSG_ERR_LIST).forEach(element -> {
			String msg = element.getText();
			if (!msg.trim().isEmpty()) {
				actualMsgList.add(msg);
			}
		});
		return actualMsgList;
	}
	
}
