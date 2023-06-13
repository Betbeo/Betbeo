package commons;

import org.openqa.selenium.WebDriver;

import pageobject.CartPage;
import pageobject.CollectionPage;
import pageobject.ContactPage;
import pageobject.LoginPage;
import pageobject.NewsPage;
import pageobject.RegisterAgentPage;
import pageobject.RegisterPage;
import pageobject.SearchPage;

public class PageGeneratorManager {
	
	public static RegisterPage getRegisterPage(WebDriver driver) {
		return new RegisterPage(driver);
	}
	
	public static LoginPage getLoginPage(WebDriver driver) {
		return new LoginPage(driver);
	}
	
	public static CartPage getCartPage(WebDriver driver) {
		return new CartPage(driver);
	}
	
	public static CollectionPage getCollectionPage(WebDriver driver) {
		return new CollectionPage(driver);
	}
	
	public static SearchPage getSearchPage(WebDriver driver) {
		return new SearchPage(driver);
	}
	
	public static NewsPage getNewsPage(WebDriver driver) {
		return new NewsPage(driver);
	}
	
	public static RegisterAgentPage getRegisterAgentPage(WebDriver driver) {
		return new RegisterAgentPage(driver);
	}
	
	public static ContactPage getContactPage(WebDriver driver) {
		return new ContactPage(driver);
	}
}
