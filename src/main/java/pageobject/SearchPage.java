package pageobject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageui.SearchPageUi;

public class SearchPage extends BasePage {

	private WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputSearchBox(String text) {
		attachTextContent("Tìm kiếm với từ khóa: " + text);
		sendkeyToElement(driver, SearchPageUi.SEARCH_BOX, text);
	}
	
	public void clickToSearchBtn() {
		attachTextContent("Click biểu tượng tìm kiếm");
		clickToElement(driver, SearchPageUi.SEARCH_BTN);
	}
	
	public void inputFastSearchBox(String text) {
		attachTextContent("Tìm kiếm nhanh với từ khóa: "+ text);
		sendkeyToElement(driver, SearchPageUi.FAST_SEARCH_BOX, text);
	}
	
	public boolean verifySearchResult(String key) {
		return false;
	}
	
	
}
