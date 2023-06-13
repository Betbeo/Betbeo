package com;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageobject.SearchPage;
import pageui.SearchPageUi;

public class SearchTest extends BaseTest {

	private WebDriver driver;
	private SearchPage searchPage;
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		searchPage = PageGeneratorManager.getSearchPage(driver);
	}
	
	@BeforeMethod
	public void openPage() {
		searchPage.openPageUrl(driver, domain);
	}
	
	@Test
	public void test_01_search() {
		searchPage.inputSearchBox("");
		searchPage.clickToSearchBtn();
		searchPage.sleepInSecond(5);
		assertEquals(searchPage.getElementText(driver, SearchPageUi.SEARCH_TITLE_KEY), String.format("Kết quả tìm kiếm cho \"%s\"", ""));
		assertTrue(searchPage.isElementDisplayed(driver, SearchPageUi.NO_ITEM));
		searchPage.takeScreenshot(driver, "Kết quả tìm kiếm khi không nhập gì");
	}
	@Test
	public void test_02_search() {
		searchPage.inputSearchBox(" ");
		searchPage.clickToSearchBtn();
		searchPage.sleepInSecond(5);
		assertEquals(searchPage.getElementText(driver, SearchPageUi.SEARCH_TITLE_KEY), String.format("Kết quả tìm kiếm cho \"%s\"", " "));
		assertTrue(searchPage.isElementDisplayed(driver, SearchPageUi.NO_ITEM));
		searchPage.takeScreenshot(driver, "Kết quả tìm kiếm khi nhập ký tự khoảng cách");
	}
	
	@Test
	public void test_03_search() {
		searchPage.inputSearchBox("@!$");
		searchPage.clickToSearchBtn();
		searchPage.sleepInSecond(5);
		assertEquals(searchPage.getElementText(driver, SearchPageUi.SEARCH_TITLE_KEY), String.format("Kết quả tìm kiếm cho \"%s\"", "@!$"));
		assertTrue(searchPage.isElementDisplayed(driver, SearchPageUi.NO_ITEM));
		searchPage.takeScreenshot(driver, "Kết quả tìm kiếm khi nhập ký tự '@!$'");
	}
	
	@Test
	public void test_04_search() {
		searchPage.inputSearchBox("bóng");
		searchPage.clickToSearchBtn();
		searchPage.sleepInSecond(5);
		assertEquals(searchPage.getElementText(driver, SearchPageUi.SEARCH_TITLE_KEY), String.format("Kết quả tìm kiếm cho \"%s\"", "bóng"));
		assertTrue(searchPage.getElementSize(driver, SearchPageUi.SEARCH_RESULT) > 0);
		searchPage.takeScreenshot(driver, "Kết quả tìm kiếm khi nhập ký tự 'bóng'");
	}
	
	@Test
	public void test_05_fastSearch() {
		searchPage.clickToSearchBtn();
		searchPage.inputFastSearchBox("@!$");
		searchPage.sleepInSecond(5);
		searchPage.takeScreenshot(driver, "Kết quả tìm kiếm nhanh khi nhập ký tự '@!$'");
		assertTrue(searchPage.getElementSize(driver, SearchPageUi.WIDGET_SEARCH_RESULT) == 0);
	}
	
	@Test
	public void test_06_fastSearch() {
		searchPage.clickToSearchBtn();
		searchPage.inputFastSearchBox("bóng");
		searchPage.sleepInSecond(5);
		searchPage.takeScreenshot(driver, "Kết quả tìm kiếm nhanh khi nhập ký tự 'bóng'");
		assertTrue(searchPage.getElementSize(driver, SearchPageUi.WIDGET_SEARCH_RESULT) > 0);
	}
}
