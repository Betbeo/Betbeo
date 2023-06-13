package com;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageobject.RegisterPage;
import pageobject.model.AccountRegister;

public class RegisterTest extends BaseTest{
	
	private WebDriver driver;
	private RegisterPage registerPage;
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}
	
	@BeforeMethod
	public void openPage() {
		registerPage.openPageUrl(driver, domain + "account/register");
	}
	
	@Test
	public void test_Register_01() {
		AccountRegister accountRegister = new AccountRegister("Abc", "", "", "");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu", "Vui lòng nhập Tên");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName , accountRegister.lastName);
		registerPage.inputTextToFirstName("Để trống trường Tên", accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Để trống trường Mật khẩu", accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}

	@Test
	public void test_Register_02() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "", "");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Để trống trường Mật khẩu", accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_03() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "abc@gmail.com", "12345");
		List<String> expectedMessage = Arrays.asList("Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_04() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "abc@gmail.com", "mat_khau_dai_tren_50_ky_tu_mat_khau_dai_tren_50_ky_tu");
		List<String> expectedMessage = Arrays.asList("Mật khẩu dài từ 6 đến 50 ký tự");
registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_05() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "", "12345");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_06() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "", "mat_khau_dai_tren_50_ky_tu_mat_khau_dai_tren_50_ky_tu");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_07() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "", "123456");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_08() {
		AccountRegister accountRegister = new AccountRegister("Abc", "", "abc@gmail.com", "123456");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Tên");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Để trống trường Tên", accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_09() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", "abc@gmail.com", "123456");
		List<String> expectedMessage = Arrays.asList("Email đã tồn tại.");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	

	@Test
	public void test_Register_10() {
		AccountRegister accountRegister = new AccountRegister("Abc", "Xyz", faker.generateFakeAlphaNumeric(20) + "@gmail.com", "123456");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		registerPage.sleepInSecond(5);
		assertEquals(registerPage.getCurrentUrl(driver), "https://samtech.vn/");
		registerPage.takeScreenshot(driver, "Thông tin đăng ký thành công");
	}
}
