package com;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageobject.CartPage;
import pageobject.CollectionPage;
import pageui.CartPageUi;

public class CartTest extends BaseTest{

	private WebDriver driver;
	private CartPage cartPage;
	private CollectionPage collectionPage;
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		cartPage = PageGeneratorManager.getCartPage(driver);
		collectionPage = PageGeneratorManager.getCollectionPage(driver);
	}
	
	@BeforeMethod
	public void openPage() {
		cartPage.openPageUrl(driver, domain + "cart");
	}
	
	@Test
	public void test_01_viewCart() {
		assertEquals(cartPage.getElementText(driver, CartPageUi.CART_BREADCRUMB), "Giỏ hàng");
		cartPage.takeScreenshot(driver, "Hiển thị giỏ hàng");
	}
	
	@Test
	public void test_02_addOneItemToCart() {
		cartPage.openPageUrl(driver, domain + "collections/all");
		collectionPage.addProductToCart(1);
		cartPage.openPageUrl(driver, domain + "cart");
		assertEquals(cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE), 1);
		cartPage.takeScreenshot(driver, "Giỏ hàng sau khi thêm 1 sản phẩm");
	}
	
	@Test
	public void test_03_changeQuantity() {
		cartPage.takeScreenshot(driver, "Trước khi thay đổi số lượng");
		cartPage.inputCartQuantity(1, 5);
		cartPage.clickUpdateBtn();
		cartPage.takeScreenshot(driver, "Sau khi thay đổi số lượng");
	}
	
	@Test
	public void test_04_changeQuantityTo0() {
		cartPage.takeScreenshot(driver, "Trước khi thay đổi số lượng về 0");
		cartPage.inputCartQuantity(1, 0);
		cartPage.clickUpdateBtn();
		assertTrue(cartPage.isElementDisplayed(driver, CartPageUi.NO_ITEM));
		cartPage.takeScreenshot(driver, "Sau khi thay đổi số lượng về 0");
	}
	
	@Test
	public void test_05_addTwoItemToCart() {
		cartPage.openPageUrl(driver, domain + "collections/all");
		collectionPage.addProductToCart(2);
		cartPage.openPageUrl(driver, domain + "cart");
		assertEquals(cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE), 2);
		cartPage.takeScreenshot(driver, "Giỏ hàng sau khi thêm 2 sản phẩm");
	}
	
	@Test
	public void test_06_changeQuantity() {
		cartPage.takeScreenshot(driver, "Trước khi thay đổi số lượng");
		cartPage.inputCartQuantity(1, 5);
		cartPage.inputCartQuantity(2, 2);
		cartPage.clickUpdateBtn();
		cartPage.takeScreenshot(driver, "Sau khi thay đổi số lượng");
	}
	
	@Test
	public void test_07_changeQuantityTo0() {
		cartPage.takeScreenshot(driver, "Trước khi thay đổi số lượng về 0");
		cartPage.inputCartQuantity(1, 0);
		cartPage.inputCartQuantity(2, 0);
		cartPage.clickUpdateBtn();
		assertTrue(cartPage.isElementDisplayed(driver, CartPageUi.NO_ITEM));
		cartPage.takeScreenshot(driver, "Sau khi thay đổi số lượng về 0");
	}
	
	@Test
	public void test_08_removeOneItemOut() {
		cartPage.openPageUrl(driver, domain + "collections/all");
		collectionPage.addProductToCart(4);
		cartPage.openPageUrl(driver, domain + "cart");
		cartPage.takeScreenshot(driver, "Trước khi xóa");
		int preRemove = cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE);
		cartPage.removeItem(2);
		int postRemove = cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE);
		assertTrue(preRemove - postRemove == 1);
		cartPage.takeScreenshot(driver, "Sau khi xóa");
	}
	
	@Test
	public void test_09_removeAllItem() {
		cartPage.takeScreenshot(driver, "Trước khi xóa");
		int preRemove = cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE);
		for (int i = 1; i <= preRemove; i++) {
			cartPage.removeItem(1);
		}	
		assertTrue(cartPage.isElementDisplayed(driver, CartPageUi.NO_ITEM));
		cartPage.takeScreenshot(driver, "Sau khi xóa");
	}
	
	@Test
	public void test_10_viewDetailItemFromCart() {
		cartPage.openPageUrl(driver, domain + "collections/all");
		collectionPage.addProductToCart(1);
		cartPage.openPageUrl(driver, domain + "cart");
		cartPage.takeScreenshot(driver, "Hiển thị giỏ hàng");
		cartPage.viewDetailItem(1);
		cartPage.sleepInSecond(5);
		cartPage.takeScreenshot(driver, "Chi tiết sản phẩm");
	}
}
