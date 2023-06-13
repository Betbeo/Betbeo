package pageobject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageui.NewsPageUi;

public class NewsPage extends BasePage {

	private WebDriver driver;

	public NewsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String clickToFirstNews() {
		attachTextContent("Click vào tin tức");
		scrollToElement(driver, NewsPageUi.FIRST_NEWS);
		String newsName = getElementText(driver, NewsPageUi.FIRST_NEWS);
		clickToElement(driver, NewsPageUi.FIRST_NEWS);
		sleepInSecond(5);
		return newsName;
	}
}
