package com;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageobject.LoginPage;
import pageobject.model.AccountLogin;

public class LoginTest extends BaseTest {
	private WebDriver driver;
	private LoginPage loginPage;
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@BeforeMethod
	public void openPage() {
		loginPage.openPageUrl(driver, domain + "account/login");
	}
	
	@AfterMethod
	public void closePage() {
		if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
			loginPage.doubleClickToLogoutButton();}
	}
	
	@Test
	public void test_Login_01() {
		AccountLogin accountLogin = new AccountLogin("", "");
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu");
		loginPage.inputTextToEmail("Để trống trường Email", accountLogin.email);
		loginPage.inputTextToPassword("Để trống trường Mật khẩu", accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_02() {
		AccountLogin accountLogin = new AccountLogin(" ", " ");
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu");
		loginPage.inputTextToEmail("Nhập ký tự khoảng trắng vào trường Email", accountLogin.email);
		loginPage.inputTextToPassword("Nhập ký tự khoảng trắng vào trường Mật khẩu", accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_03() {
		AccountLogin accountLogin = new AccountLogin("abc@gmail.com", "");
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Mật khẩu");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Để trống trường Mật khẩu", accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_04() {
		AccountLogin accountLogin = new AccountLogin("", "123456");
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Email");
		loginPage.inputTextToEmail("Để trống trường Email", accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_05() {
		AccountLogin accountLogin = new AccountLogin("abc@gmail.com", "123");
		List<String> expectedMsg = Arrays.asList("Thông tin đăng nhập không chính xác.");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_06() {
		AccountLogin accountLogin = new AccountLogin("abc@gmail.com", "123456");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		//loginPage.sleepInSecond(5);
		loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
		assertEquals(loginPage.getCurrentUrl(driver), "https://samtech.vn/account");
		if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
			loginPage.doubleClickToLogoutButton();}
	}
	
	@Test
	public void test_Login_07() {
		AccountLogin accountLogin = new AccountLogin(" abc@gmail.com", "123456");
		List<String> expectedMsg = Arrays.asList("Thông tin đăng nhập không chính xác.");
		loginPage.inputTextToEmail("Nhập ký tự trắng trước trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		//loginPage.sleepInSecond(5);
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_08() {
		AccountLogin accountLogin = new AccountLogin("abc@gmail.com ", "123456");
		loginPage.inputTextToEmail("Nhập ký tự trắng sau trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		//loginPage.sleepInSecond(5);
		loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
		assertEquals(loginPage.getCurrentUrl(driver), "https://samtech.vn/account");
		if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
			loginPage.doubleClickToLogoutButton();}
	}
	
	@Test
	public void test_Login_09() {
		AccountLogin accountLogin = new AccountLogin("abc@gmail.com", " 123456");
		List<String> expectedMsg = Arrays.asList("Thông tin đăng nhập không chính xác.");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập ký tự trắng trước trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		//loginPage.sleepInSecond(5);
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			if (loginPage.getCurrentUrl(driver).equals("https://samtech.vn/account")) {
				loginPage.doubleClickToLogoutButton();}
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
	
	@Test
	public void test_Login_10() {
		AccountLogin accountLogin = new AccountLogin("abc@gmail.com", "123456 ");
		List<String> expectedMsg = Arrays.asList("Thông tin đăng nhập không chính xác.");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập ký tự trắng sau trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		//loginPage.sleepInSecond(5);
		if (loginPage.getMsg().containsAll(expectedMsg)) {
			assert2List(loginPage.getMsg(), expectedMsg);
	        loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
	    } else {
			loginPage.takeScreenshot(driver, "Kết quả đăng nhập");
			Assert.fail("Không tìm thấy thông báo lỗi trên trang.");
	    }
	}
}
