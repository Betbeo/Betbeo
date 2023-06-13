package pageobject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageobject.model.RegisterAgent;
import pageui.RegisterAgentPageUi;

public class RegisterAgentPage extends BasePage {
	private WebDriver driver;

	public RegisterAgentPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputData(RegisterAgent agent) {
		attachTextContent("Nhập thông tin");
		sendkeyToElement(driver, RegisterAgentPageUi.NAME_INPUT, agent.name);
		sendkeyToElement(driver, RegisterAgentPageUi.STORE_NAME_INPUT, agent.storeName);
		sendkeyToElement(driver, RegisterAgentPageUi.ADDRESS_INPUT, agent.address);
	}
	
	public void clickSendBtn() {
		attachTextContent("Nhấp chọn Gửi");
		scrollToElement(driver, RegisterAgentPageUi.SEND_BTN);
		clickToElement(driver, RegisterAgentPageUi.SEND_BTN);
	}

}
