package com;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageobject.CartPage;
import pageobject.CollectionPage;
import pageobject.ContactPage;
import pageobject.NewsPage;
import pageobject.RegisterAgentPage;
import pageobject.model.Contact;
import pageobject.model.RegisterAgent;
import pageui.CartPageUi;
import pageui.CollectionPageUi;
import pageui.NewsPageUi;

public class ThreadTest extends BaseTest {
	private WebDriver driver;
	private CollectionPage collectionPage;
	private CartPage cartPage;
	private NewsPage newsPage;
	private RegisterAgentPage registerAgentPage;
	private ContactPage contactPage;
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		collectionPage = PageGeneratorManager.getCollectionPage(driver);
		cartPage = PageGeneratorManager.getCartPage(driver);
		newsPage = PageGeneratorManager.getNewsPage(driver);
		registerAgentPage = PageGeneratorManager.getRegisterAgentPage(driver);
		contactPage = PageGeneratorManager.getContactPage(driver);
	}
	
	@Test
	public void test_01_viewDetailProduct() {
		collectionPage.openPageUrl(driver, domain + "collections/all");
		String itemName = collectionPage.viewDetailProduct(1);
		assertEquals(collectionPage.getElementText(driver, CollectionPageUi.DETAIL_PRODUCT_NAME), itemName);
		collectionPage.takeScreenshot(driver, "Chi tiết sản phẩm");
	}
	@Test
	public void test_02_addToCartFromDetailProductView() {
		collectionPage.inputQuantity(3);
		collectionPage.takeScreenshot(driver, "Nhập số lượng");
		collectionPage.clickAddBtn();
		collectionPage.openPageUrl(driver, domain + "cart");
		assertEquals(cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE), 1);
		cartPage.takeScreenshot(driver, "Thêm mặt hàng vào giỏ hàng thành công");;
	}
	
	@Test
	public void test_03_viewDetailNews() {
		newsPage.openPageUrl(driver, domain + "blogs/all");
		String newsTitle = newsPage.clickToFirstNews();
		newsPage.takeScreenshot(driver, "Chi tiết tin tức");
		assertEquals(newsPage.getElementText(driver, NewsPageUi.NEWS_TITLE), newsTitle);
	}
	
	@Test
	public void test_04_registerAgent() {
		RegisterAgent agent = new RegisterAgent("Abc", "Store", "Ha Noi");
		registerAgentPage.openPageUrl(driver, domain + "dang-ki-dai-ly");
		registerAgentPage.inputData(agent);
		registerAgentPage.takeScreenshot(driver, "Thông tin đã nhập");
		registerAgentPage.clickSendBtn();
		registerAgentPage.takeScreenshot(driver, "Đăng ký thành công");
	}
	
	@Test
	public void test_05_contact() {
		Contact contact = new Contact("Abc", "abc@gmail.com", "memo");
		contactPage.openPageUrl(driver, domain + "lien-he");
		contactPage.inputData(contact);
		contactPage.takeScreenshot(driver, "Thông tin đã nhập");
		contactPage.clickSendBtn();
		contactPage.takeScreenshot(driver, "Gửi thành công");
	}
}
